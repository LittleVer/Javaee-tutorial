package com.system.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.entity.Role;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.system.service.ResourceBiz;
import com.system.service.RoleBiz;

/**
 * Created by c0de8ug on 16-2-21.
 */
@Controller

@RequestMapping("/role.do")
public class RoleController {


	@Autowired
    private RoleBiz roleBiz;

    @Autowired
    private ResourceBiz resourceBiz;
    
    @RequiresPermissions("role:query")
    @RequestMapping("role.view")
    public String roleView(Model model) {
        return "forward:/role.do/role.view/0/10";
    }

    @RequiresPermissions("role:query")
    @RequestMapping("role.view/{pageNum}/{pageSize}")
    public String roleView(Model model,@PathVariable("pageNum") int pageNum,
    		@PathVariable("pageSize") int pageSize) {
    	PageHelper.startPage(pageNum, pageSize);
		List<Role> list = roleBiz.findAll();
		PageInfo<Role> page = new PageInfo<Role>(list);
    	model.addAttribute("roleList",list);
    	model.addAttribute("page",page);
        return "/admin/system/role/role";
    }

    @RequiresPermissions("role:add")
    @RequestMapping("role_add.view")
    public String roleAddView(Model model) {
    	model.addAttribute("resourceList", resourceBiz.findAll());
        return "/admin/system/role/role_add";
    }
    
    @RequiresPermissions("role:update")
    @RequestMapping("role_update.view")
    public String roleUpdateView(Long id,Model model) {
    	model.addAttribute("role", roleBiz.findOne(id));
    	model.addAttribute("resourceList", resourceBiz.findAll());
    	return "/admin/system/role/role_update";
    }

    @RequiresPermissions("role:add")
    @RequestMapping("add")
    public String add(Role role, RedirectAttributes redirectAttributes) {
        roleBiz.createRole(role);
        return "redirect:/role.do/role.view";
    }
    
    @RequiresPermissions("role:update")
    @RequestMapping("update")
    public String update(Role role, RedirectAttributes redirectAttributes) {
    	roleBiz.updateRole(role);
    	return "redirect:/role.do/role.view";
    }

    @RequiresPermissions("role:delete")
    @RequestMapping("delete")
    public String delete(Long id, RedirectAttributes redirectAttributes) {
        roleBiz.deleteRole(id);
        return "redirect:/role.do/role.view";
    }
}

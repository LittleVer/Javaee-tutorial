package com.system.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.system.service.RoleBiz;
import com.system.service.UserBiz;

@Controller
@RequestMapping("user.do")
public class UserController {

	@Autowired
    private UserBiz userBiz;

	@Autowired
    private RoleBiz roleBiz;
	
	@RequiresPermissions("user:query")
    @RequestMapping("user.view")
    public String userView(Model model) {
        return "forward:/user.do/user.view/0/10";
    }

	@RequiresPermissions("user:query")
    @RequestMapping("user.view/{pageNum}/{pageSize}")
    public String userView(Model m,@PathVariable("pageNum") int pageNum,
    		@PathVariable("pageSize") int pageSize) throws InvocationTargetException, IllegalAccessException {
		PageHelper.startPage(pageNum, pageSize);
		List<User> list = userBiz.findAll();
		PageInfo<User> page = new PageInfo<User>(list);
    	m.addAttribute("userList",list);
    	m.addAttribute("page",page);
        return "/admin/system/user/user";
    }

	@RequiresPermissions("user:add")
    @RequestMapping("user_add.view")
    public String userAddView(Model m) {
        m.addAttribute("roleList", roleBiz.findAll());
        return "/admin/system/user/user_add";
    }

	@RequiresPermissions("user:add")
    @RequestMapping("findById")
    public String findById(String id, Model m) {
        m.addAttribute("user", userBiz.findById(id));
        m.addAttribute("roleList", roleBiz.findAll());
        return "/admin/system/user/user_update";
    }

	@RequiresPermissions("user:update")
    @RequestMapping("update")
    public String update(User user) {
        userBiz.update(user);
        return "redirect:/user.do/user.view";
    }

	@RequiresPermissions("user:add")
    @RequestMapping("add")
    public String add(User user) {

        userBiz.add(user);
        return "redirect:/user.do/user.view";
    }

	@RequiresPermissions("user:delete")
    @RequestMapping("delete")
    public String delete(String id) {
        userBiz.delete(id);
        return "redirect:/user.do/user.view";
    }

}

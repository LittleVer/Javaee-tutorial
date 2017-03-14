package com.system.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.entity.Resource;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.system.service.ResourceBiz;

@Controller
@RequestMapping("/resource.do")
public class ResourceController {
	Logger log = Logger.getLogger(ResourceController.class);

	 @Autowired
    private ResourceBiz resourceBiz;
	 
	@RequiresPermissions("resource:query")
    @RequestMapping("resource.view")
    public String resourceView(Model model) {
        return "forward:/resource.do/resource.view/0/10";
    }

	@RequiresPermissions("resource:query")
    @RequestMapping("resource.view/{pageNum}/{pageSize}")
    public String resourceView(Model model,@PathVariable("pageNum") int pageNum,
    		@PathVariable("pageSize") int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Resource> resourceList = resourceBiz.findAll();
		PageInfo<Resource> page = new PageInfo<Resource>(resourceList);
    	model.addAttribute("resourceList",resourceList);
    	model.addAttribute("page",page);
        return "/admin/system/resource/resource";
    }

	@RequiresPermissions("resource:add")
    @RequestMapping("resource_add.view")
    public String resourceAddView(Model model) {
        return "/admin/system/resource/resource_add";
    }
	
	@RequiresPermissions("resource:add")
    @RequestMapping("add")
    public String add(com.entity.Resource resource, RedirectAttributes redirectAttributes) {
        resourceBiz.createResource(resource);
        return "redirect:/resource.do/resource.view";
    }
	
	@RequiresPermissions("resource:update")
	@RequestMapping("resource_update.view")
	public String resourceUpdateView(Long id,Model model) {
		model.addAttribute("resource", resourceBiz.findOne(id));
		return "/admin/system/resource/resource_update";
	}
	
	@RequiresPermissions("resource:update")
    @RequestMapping("update")
    public String update(com.entity.Resource resource, RedirectAttributes redirectAttributes) {
        resourceBiz.updateResource(resource);
        return "redirect:/resource.do/resource.view";
    }

	@RequiresPermissions("resource:delete")
    @RequestMapping("delete")
    public String delete(Long id, RedirectAttributes redirectAttributes) {
		resourceBiz.deleteResource(id);
        return "redirect:/resource.do/resource.view";
    }
}

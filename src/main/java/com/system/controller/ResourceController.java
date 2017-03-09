package com.system.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.system.service.ResourceBiz;

/**
 * Created by c0de8ug on 16-2-21.
 */
@Controller

@RequestMapping("/resource.do")
public class ResourceController {
	Logger log = Logger.getLogger(ResourceController.class);

    @Resource(name = "resourceBizImpl")
    private ResourceBiz resourceBiz;

    @RequiresRoles("admin")
    @RequestMapping("resource.view")
    public String resourceView(Model model) {
    	model.addAttribute("resourceList",resourceBiz.findAll());
        return "/admin/system/resource/resource";
    }

    @RequiresRoles("admin")
    @RequestMapping("resource_add.view")
    public String resourceAddView(Model model) {
        model.addAttribute("resourceList", resourceBiz.findAll());
        return "/admin/system/resource/resource_add";
    }

    @RequiresRoles("admin")
    @RequestMapping("add")
    public String add(com.entity.Resource resource, RedirectAttributes redirectAttributes) {
        resourceBiz.createResource(resource);
        return "redirect:/resource.do/resource.view";
    }

    @RequiresRoles("admin")
    @RequestMapping("delete")
    public String delete(Long id, RedirectAttributes redirectAttributes) {
		resourceBiz.deleteResource(id);
        return "redirect:/resource.do/resource.view";
    }
}

package com.vm.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.Standard;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vm.service.StandardBiz;

@Controller
@RequestMapping("standard.do")
public class StandardController {

    @Autowired
    private StandardBiz standardBiz;

    @RequiresPermissions("standard:query")
    @RequestMapping("standard.view/{pageNum}/{pageSize}")
    public String standardView(Standard standard,Model m,@PathVariable("pageNum") int pageNum,
    		@PathVariable("pageSize") int pageSize) {
    	PageHelper.startPage(pageNum, pageSize);
    	List<Standard> list =  standardBiz.find(standard);
    	PageInfo<Standard> page = new PageInfo<Standard>(list);
        m.addAttribute("standardList", list);
        m.addAttribute("page", page);
        return "/vm/standard/standard";
    }

    @RequiresPermissions("standard:add")
    @RequestMapping("standard_add.view")
    public String standardAddView(Model m) {
        return "/vm/standard/standard_add";
    }

    @RequiresPermissions("standard:update")
    @RequestMapping("standard_update.view")
    public String standardUpdateView(Long id,Model m) {
    	m.addAttribute("standard",standardBiz.findById(id));
        return "/vm/standard/standard_update";
    }

    @RequiresPermissions("standard:add")
    @RequestMapping("add")
    public String add(Standard standard) {
        standardBiz.add(standard);
        return "redirect:/standard.do/standard.view";
    }

    @RequiresPermissions("standard:update")
    @RequestMapping("update")
    public String update(Standard standard) {
        standardBiz.update(standard);
        return "redirect:/standard.do/standard.view";
    }

    @RequiresPermissions("standard:delete")
    @RequestMapping("delete")
    public String delete(Long standardId) {
        standardBiz.delete(standardId);
        return "redirect:/standard.do/standard.view";
    }

}

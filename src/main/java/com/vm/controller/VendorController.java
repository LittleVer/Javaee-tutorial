package com.vm.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.Vendor;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vm.service.VendorBiz;

@Controller
@RequestMapping("vendor.do")
public class VendorController {

    @Autowired
    private VendorBiz vendorBiz;
    
    @RequiresPermissions("vendor:query")
    @RequestMapping("vendor.view")
    public String vendorView(Model model) {
        return "forward:/vendor.do/vendor.view/0/10";
    }

    @RequiresPermissions("vendor:query")
    @RequestMapping("vendor.view/{pageNum}/{pageSize}")
    public String vendorView(Vendor vendor,Model m,@PathVariable("pageNum") int pageNum,
    		@PathVariable("pageSize") int pageSize) {
    	PageHelper.startPage(pageNum, pageSize);
    	List<Vendor> list =  vendorBiz.find(vendor);
    	PageInfo<Vendor> page = new PageInfo<Vendor>(list);
        m.addAttribute("vendorList", list);
        m.addAttribute("page", page);
        return "/vm/vendor/vendor";
    }

    @RequiresPermissions("vendor:add")
    @RequestMapping("vendor_add.view")
    public String vendorAddView(Model m) {
    	m.addAttribute("vendor", new Vendor());
        return "/vm/vendor/vendor_add";
    }

    @RequiresPermissions("vendor:update")
    @RequestMapping("vendor_update.view")
    public String vendorUpdateView(Long id,Model m) {
    	m.addAttribute("vendor",vendorBiz.findById(id));
        return "/vm/vendor/vendor_update";
    }

    @RequiresPermissions("vendor:add")
    @RequestMapping("add")
    public String add(Vendor vendor) {
        vendorBiz.add(vendor);
        return "redirect:/vendor.do/vendor.view";
    }

    @RequiresPermissions("vendor:update")
    @RequestMapping("update")
    public String update(Vendor vendor) {
        vendorBiz.update(vendor);
        return "redirect:/vendor.do/vendor.view";
    }

    @RequiresPermissions("vendor:delete")
    @RequestMapping("delete")
    public String delete(Long id) {
        vendorBiz.delete(id);
        return "redirect:/vendor.do/vendor.view";
    }
}

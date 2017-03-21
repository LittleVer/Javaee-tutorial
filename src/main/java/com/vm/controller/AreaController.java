package com.vm.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.Area;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vm.service.AreaBiz;

@Controller
@RequestMapping("area.do")
public class AreaController {

    @Autowired
    private AreaBiz areaBiz;
    
    @RequiresPermissions("area:query")
    @RequestMapping("area.view")
    public String areaView(Model model) {
        return "forward:/area.do/area.view/0/10";
    }

    @RequiresPermissions("area:query")
    @RequestMapping("area.view/{pageNum}/{pageSize}")
    public String areaView(Area area,Model m,@PathVariable("pageNum") int pageNum,
    		@PathVariable("pageSize") int pageSize) {
    	PageHelper.startPage(pageNum, pageSize);
    	List<Area> list =  areaBiz.find(area);
    	PageInfo<Area> page = new PageInfo<Area>(list);
        m.addAttribute("areaList", list);
        m.addAttribute("page", page);
        return "/vm/area/area";
    }

    @RequiresPermissions("area:add")
    @RequestMapping("area_add.view")
    public String areaAddView(Model m) {
    	m.addAttribute("area", new Area());
        return "/vm/area/area_add";
    }

    @RequiresPermissions("area:update")
    @RequestMapping("area_update.view")
    public String areaUpdateView(Long id,Model m) {
    	m.addAttribute("area",areaBiz.findById(id));
        return "/vm/area/area_update";
    }

    @RequiresPermissions("area:add")
    @RequestMapping("add")
    public String add(Area area) {
        areaBiz.add(area);
        return "redirect:/area.do/area.view";
    }

    @RequiresPermissions("area:update")
    @RequestMapping("update")
    public String update(Area area) {
        areaBiz.update(area);
        return "redirect:/area.do/area.view";
    }

    @RequiresPermissions("area:delete")
    @RequestMapping("delete")
    public String delete(Long id) {
        areaBiz.delete(id);
        return "redirect:/area.do/area.view";
    }
}

package com.vm.controller;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.Car;
import com.entity.Standard;
import com.vm.service.CarBiz;
import com.vm.service.StandardBiz;

@Controller
@RequestMapping("car.do")
public class CarController {
	private Logger log = Logger.getLogger(CarController.class);

    @Autowired
    private CarBiz carBiz;
    @Autowired
    private StandardBiz standardBiz;

    @RequiresPermissions("car:query")
    @RequestMapping("car.view")
    public String CarView(Car car,Model m) {
        m.addAttribute("carList", carBiz.find(car));
        return "/vm/car/car";
    }

    @RequiresPermissions("car:add")
    @RequestMapping("car_add.view")
    public String carAddView(Model m) {
    	m.addAttribute("standardList", standardBiz.find(new Standard()));
        return "/vm/car/car_add";
    }

    @RequiresPermissions("car:update")
    @RequestMapping("car_update.view")
    public String carUpdateView(Long id,Model m) {
    	m.addAttribute("standardList", standardBiz.find(new Standard()));
    	m.addAttribute("car",carBiz.findById(id));
        return "/vm/car/car_update";
    }

    @RequiresPermissions("car:add")
    @RequestMapping("add")
    public String add(Car car) {
        carBiz.add(car);
        return "redirect:/car.do/car.view";
    }

    @RequiresPermissions("car:update")
    @RequestMapping("update")
    public String update(Car car) {
        carBiz.update(car);
        return "redirect:/car.do/car.view";
    }

    @RequiresPermissions("car:delete")
    @RequestMapping("delete")
    public String delete(Long id) {
        carBiz.delete(id);
        return "redirect:/car.do/car.view";
    }

}

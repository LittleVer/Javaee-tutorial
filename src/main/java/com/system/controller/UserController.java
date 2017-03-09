package com.system.controller;

import com.entity.User;
import com.system.service.RoleBiz;
import com.system.service.UserBiz;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by c0de8ug on 16-2-9.
 */

@Controller
@RequiresRoles("admin")
@RequestMapping("user.do")
public class UserController {

    @Resource(name = "userBizImpl")
    private UserBiz userBiz;

    @Resource(name = "roleBizImpl")
    private RoleBiz roleBiz;

    @RequiresRoles("admin")
    @RequestMapping("user.view")
    public String userView(Model m) throws InvocationTargetException, IllegalAccessException {
        m.addAttribute("userList", userBiz.findAll());
        return "/admin/system/user/user";
    }

    @RequiresRoles("admin")
    @RequestMapping("user_add.view")
    public String userAddView(Model m) {
        m.addAttribute("roleList", roleBiz.findAll());
        return "/admin/system/user/user_add";
    }

    @RequiresRoles("admin")
    @RequestMapping("findById")
    public String findById(String id, Model m) {
        m.addAttribute("user", userBiz.findById(id));
        m.addAttribute("roleList", roleBiz.findAll());
        return "/admin/system/user/user_update";
    }

    @RequiresRoles("admin")
    @RequestMapping("update")
    public String update(User user) {
        userBiz.update(user);
        return "redirect:/user.do/user.view";
    }

    @RequiresRoles("admin")
    @RequestMapping("add")
    public String add(User user) {

        userBiz.add(user);
        return "redirect:/user.do/user.view";
    }

    @RequiresRoles("admin")
    @RequestMapping("delete")
    public String delete(String id) {
        userBiz.delete(id);
        return "redirect:/user.do/user.view";
    }

}

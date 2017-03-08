package com.system.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.system.service.UserBiz;

/**
 * Created by c0de8ug on 16-2-14.
 */
@Controller
public class LoginController {

    @Resource(name = "userBizImpl")
    UserBiz userBiz;

    @RequestMapping("login")
    public String login(HttpServletRequest req, ModelMap modelMap, HttpSession session) {
        String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
        String error = null;
        if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if (exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        modelMap.put("msg", error);

        Subject subject = SecurityUtils.getSubject();
        boolean isAuthenticated = subject.isAuthenticated();

        if (isAuthenticated) {
            String principal = (String) subject.getPrincipal();
            session.setAttribute("username", principal);
            return "/admin/main";
           /* switch (principal) {
                case "admin":
                    return "/admin/main";
                case "teacher":
                    return "/teacher/main";
                case "student":
                    return "/student/main";
                case "supplier":
                    return "redirect:supplier.do/supplier.view";
            }*/
        }

        return "forward:login.jsp";
    }


}

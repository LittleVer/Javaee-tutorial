package com.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;

public class FormLoginFilter extends PathMatchingFilter {

    private String loginFormUrl = "/login";
    private String loginUrl = "/login.jsp";

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return true;//已经登录过
        }
        boolean flag = false;
        HttpServletRequest req = (HttpServletRequest) request;
        if (isLoginRequest(loginUrl,req)) {
        	flag = true;
        } else if (isLoginRequest(loginFormUrl,req)) {
            if ("post".equalsIgnoreCase(req.getMethod())) {//form表单提交
                login(req); //登录
                flag = true;
            }
        }
        if(!flag) {
        	WebUtils.issueRedirect(request, response, loginUrl, request.getParameterMap());
        }
        return flag;
    }

    /*private boolean redirectToSuccessUrl(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebUtils.redirectToSavedRequest(req, resp, successUrl);
        return false;
    }

    private void saveRequestAndRedirectToLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        WebUtils.saveRequest(req);
        WebUtils.issueRedirect(req, resp, loginUrl);
    }*/

    private boolean login(HttpServletRequest req) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            SecurityUtils.getSubject().login(new UsernamePasswordToken(username, password));
        } catch (Exception e) {
            req.setAttribute("shiroLoginFailure", e.getClass().getName());
            return false;
        }
        return true;
    }

    private boolean isLoginRequest(String url,HttpServletRequest req) {
        return pathsMatch(url, WebUtils.getPathWithinApplication(req));
    }
}

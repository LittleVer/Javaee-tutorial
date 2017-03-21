package com.system.interceptor;


import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;
import com.system.model.Breadcrumb;

public class BreadcrumbIntercepter implements HandlerInterceptor {
	private Logger log = Logger.getLogger(BreadcrumbIntercepter.class);
	private Properties properties;
	//private Pattern p1 = Pattern.compile("[//\\a-zA-Z]+?(?<!add|update|delete)\\.view");
	private Pattern p2 = Pattern.compile(".*?(add|update|delete)\\.view.*");
	private Pattern pc = Pattern.compile("(?:/.*?/)(.*?)(?:\\.|/)");
	public BreadcrumbIntercepter() {
		properties = new Properties();
		try {
			properties.load(BreadcrumbIntercepter.class.getResourceAsStream("/breadcrumb-e-c.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    @SuppressWarnings("unchecked")
	@Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String url = httpServletRequest.getRequestURI();
        HttpSession session = httpServletRequest.getSession();
        
        if (url.indexOf("login") > 0) {
        	Subject subject = SecurityUtils.getSubject();
        	if(subject.isAuthenticated()) {
        		List<Breadcrumb> breadcrumbs = Lists.newArrayList(new Breadcrumb(properties.getProperty("login"), httpServletRequest.getContextPath()+"/main.do/admin"));
                session.setAttribute("breadcrumbs", breadcrumbs);
                return true;
        	}
        }
        
        if(url.indexOf("view") == -1 || url.indexOf("external") != -1) return true;

        List<Breadcrumb> breadcrumbs = (List<Breadcrumb>)session.getAttribute("breadcrumbs");
        Matcher matcher = pc.matcher(url);
        matcher.find();
        String cato = matcher.group(1);
        
        if(properties.getProperty(cato)==null) {
        	log.warn("没有配置的路径类型:"+cato+"("+url+")");
        	return true;
        }
        
        if(url.matches("[//\\a-zA-Z]+?(?<!add|update|delete)\\.view.*")) {
        	if(breadcrumbs.size()==3)
        		breadcrumbs.remove(2);
        	if(breadcrumbs.size()==2)
        		breadcrumbs.set(1,new Breadcrumb(properties.getProperty(cato), url));
        	else 
        		breadcrumbs.add(1,new Breadcrumb(properties.getProperty(cato), url));
        } else {
        	Matcher matcher2 = p2.matcher(url);
        	if(matcher2.find()) {
        		String opra = matcher2.group(1);
        		if(breadcrumbs.size()==3)
        			breadcrumbs.set(2,new Breadcrumb(properties.getProperty(opra), url));
        		else
        			breadcrumbs.add(2,new Breadcrumb(properties.getProperty(opra), url));
        	}
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    	/*String view = modelAndView.getViewName();
    	String url = httpServletRequest.getRequestURI();
    	log.info(url+":"+view);*/
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

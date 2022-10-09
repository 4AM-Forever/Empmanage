package com.zheng.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginHtmlFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest Request = (HttpServletRequest) servletRequest;
        HttpServletResponse Response = (HttpServletResponse) servletResponse;
        if (Request.getSession().getAttribute("Empmanager")!=null){
            System.out.println("从定向");
            Response.sendRedirect("/man/safe/index");
//            //告诉ajax我要重定向
//            Response.setHeader("REDIRECT", "REDIRECT");
//            //告诉ajax我重定向的路径
//            Response.setHeader("CONTENTPATH", "/man/safe/index");
        }else {
            System.out.println("放行");
            filterChain.doFilter(Request,Response);
        }
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
    @Override
    public void destroy() {}
}

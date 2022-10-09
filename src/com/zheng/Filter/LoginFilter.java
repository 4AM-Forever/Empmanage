package com.zheng.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/man/safe/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest Request = (HttpServletRequest) req;
        HttpServletResponse Response = (HttpServletResponse) resp;
        Object o = Request.getSession().getAttribute("Empmanager");
        System.out.println(Request.getRequestURI());
        System.out.println("进入登录过滤");

        if (o==null){
            System.out.println("未访问登录的servlet，重定向到登录页面");
            if (Request.getRequestURI().endsWith("index")){//判断是否为index结尾，是的话说名用户是直接url访问登录servlet，重定向到登陆页面
                Response.sendRedirect("/login.html");
                return;
            }
            //不是index结尾，说名有参数，有可能是用户退出登陆后，通过浏览器回退操作数据，此时，直接告知ajax重定向到登陆页面
            //告诉ajax我要重定向
            Response.setHeader("REDIRECT", "REDIRECT");
            //告诉ajax我重定向的路径
            Response.setHeader("CONTENTPATH", "/man/safe/index");
//            Request.getRequestDispatcher("/login.html").forward(req,resp);
        } else {
            filterChain.doFilter(req,resp);
        }
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
    @Override
    public void destroy() {}
}

package com.zheng.controller;

import com.zheng.pojo.Empmanager;
import com.zheng.service.EmpManagerLoginservice;
import com.zheng.service.impl.EmpManagerLoginserviceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/man/login")
public class EmpManagerLoginServlet extends HttpServlet {
    private EmpManagerLoginservice emlogin = new EmpManagerLoginserviceimpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("0".equals(req.getParameter("tag"))){
            System.out.println("login");
            req.getSession().setAttribute("Empmanager",null);//将原先的登录者信息session删除
            //告诉ajax我要重定向
            resp.setHeader("REDIRECT", "REDIRECT");
            //告诉ajax我重定向到登录页面
            resp.setHeader("CONTENTPATH", "/login.html");
            return;
        }

        //判断是否登陆过
        if (req.getSession().getAttribute("Empmanager")!=null){
            Empmanager empmanager = (Empmanager) req.getSession().getAttribute("Empmanager");
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            if (username!=null&&password!=null){
                if (empmanager.getUsername().equals(username)&&empmanager.getPassword().equals(password)){
                    //如果已经登录过，判断再次登录信息是否为正确，正确则直接跳转到主页面，这里是防止浏览器回退的bug
//                    resp.sendRedirect("/man/safe/index");
                    resp.setHeader("REDIRECT", "REDIRECT");
                    //告诉ajax我重定向的路径
                    resp.setHeader("CONTENTPATH", "/man/safe/index");
                    return;
                }
                //错误，则提示先退出其他账号
                resp.getWriter().println("已有其他账户登录，请先退出，后再登录");
                return;
            }

       }
        //没有登陆过，就获取密码
        try{
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            if (username==null||password==null){
                resp.sendRedirect("/login.html");
                return;
            }
            System.out.println(username+"---"+password);
            Empmanager emg = new Empmanager(username,password);
            Empmanager newemg = emlogin.login(emg);
            if (newemg == null) {
                resp.getWriter().println("登陆失败，请检查账号或者密码是否正确");
                return;
            }
            System.out.println("登录验证访问成功");
            //向session存入对象
            req.getSession().setAttribute("Empmanager",newemg);
//            resp.sendRedirect("/man/safe/index");
            //告诉ajax我要重定向
            resp.setHeader("REDIRECT", "REDIRECT");
            //告诉ajax我重定向的路径
            resp.setHeader("CONTENTPATH", "/man/safe/index");
//        resp.getWriter().println("登录成功");
        }catch (Exception e){
            System.out.println("后台数据传送可疑");
        }
    }
}

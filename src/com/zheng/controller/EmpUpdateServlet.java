package com.zheng.controller;

import com.alibaba.fastjson.JSON;
import com.zheng.Utils.utils;
import com.zheng.pojo.Emp;
import com.zheng.service.impl.EmpDataServiceimpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/man/safe/update")
public class EmpUpdateServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmpDataServiceimpl edsi = new EmpDataServiceimpl();
        System.out.println("进入后台");
        String tab = req.getParameter("tab");
        Integer id =  null;
        String name = null;
        Double salary = null;
        Integer age = null;

        if (tab.equals("1-._`")){//添加
            //将获取的数据转为对应的数据类型
            System.out.println("进入添加");
            name = req.getParameter("name");
            salary = Double.parseDouble(req.getParameter("salary"));
            age = Integer.parseInt(req.getParameter("age"));
            Emp e = new Emp(name,salary,age);
            if (name!=""&&salary>0&&age>0&&edsi.insert(e)){
                System.out.println("添加成功重定向");
                resp.getWriter().println("添加成功");
                resp.setHeader("REDIRECT", "REDIRECT");
                resp.setHeader("CONTENTPATH", "/man/safe/index");
//                resp.sendRedirect("/man/safe/index");
                return;
            }
            System.out.println("添加失败");
            resp.getWriter().println("添加失败，请检查是否填写正确");

        } else if (tab.equals("2-.__`")){//修改
            id =  Integer.parseInt(req.getParameter("id"));
            name = req.getParameter("name");
            salary = Double.parseDouble(req.getParameter("salary"));
            age = Integer.parseInt(req.getParameter("age"));
            Emp e = new Emp(id,name,salary,age);
            if (edsi.uptdate(e)){
                resp.getWriter().println("修改成功");
            }else {
                resp.getWriter().println("修改失败");
            }
            resp.setHeader("REDIRECT", "REDIRECT");
            resp.setHeader("CONTENTPATH", "/man/safe/index");
        } else if (tab.equals("3-.__。`")){//删除
            id =  Integer.parseInt(req.getParameter("id"));
            edsi.delete(id);//删除该id的数据
        } else {
            //如果发现有未知标记，说明有人从非法篡改网页尝试攻击，
            //措施：立即销毁该session，并封禁账号，提示警告，需要上传人脸和身份证件才可解禁
        }

    }
}

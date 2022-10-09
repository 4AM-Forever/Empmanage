package com.zheng.controller;

import com.zheng.dao.EmpIndexDao;
import com.zheng.dao.impl.EmpIndexDaoimpl;
import com.zheng.pojo.Emp;
import com.zheng.service.impl.showServiceimpl;
import com.zheng.service.showService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/man/safe/index")
public class EmpShowServlet extends HttpServlet {
    private showService show = new showServiceimpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Emp> list = show.showAll();
        req.setAttribute("list",list);
        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }
}

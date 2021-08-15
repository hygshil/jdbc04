package com.hp.controller;

import com.hp.bean.User;
import com.hp.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserUpdateServlet",urlPatterns = "/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、修正编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=UTF-8");
        //2、接收前端传过来的参数
        String id=req.getParameter("id");
        System.out.println("id = " + id);
        String username =req.getParameter("username");
        String real_name=req.getParameter("real_name");
        String password =req.getParameter("password");
        String type =req.getParameter("type");
        String img=req.getParameter("img");
        String is_del=req.getParameter("is_del");
        String create_time=req.getParameter("create_time");
        String modify_time=req.getParameter("modify_time");
        int typeis=(is_del=="是"?1:2);
        int iid = Integer.parseInt(id);
        int itype = Integer.parseInt(type);
        System.out.println("iid = " + iid);
        //3、调用dao
        User user = new User();
        UserDao userDao = new UserDao();
        user.setId(iid);
        user.setUsername(username);
        user.setReal_name(real_name);
        user.setPassword(password);
        user.setType(itype);
        user.setImg(img);
        user.setIs_del(typeis);
        user.setCreate_time(create_time);
        user.setModify_time(modify_time);
        System.out.println("user = " + user);
        int i = userDao.update(user);
        System.out.println("修改成功 " + i);

    }
}

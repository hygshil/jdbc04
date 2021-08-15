package com.hp.controller;

import com.hp.bean.User;
import com.hp.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserAddServlet",urlPatterns = "/UserAddServlet")
public class UserAddServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接受登陆传过来的3个参数
        //1、修正编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=UTF-8");

        //2、接收前端传过来的参数
        String username =req.getParameter("username");
        String real_name=req.getParameter("real_name");
        String password =req.getParameter("password");
        String type =req.getParameter("type");
        String img =req.getParameter("img");
        String is_del=req.getParameter("is_del");
        String create_time=req.getParameter("create_time");
        String modify_time=req.getParameter("modify_time");
        System.out.println("type = " + type);
        System.out.println("is_del = " + is_del);


        //3、调佣dao
        User user = new User();
        UserDao userDao = new UserDao();
        user.setUsername(username);
        user.setReal_name(real_name);
        user.setPassword(password);
        user.setType(Integer.parseInt(type));
        user.setImg(img);
        user.setIs_del(Integer.parseInt(is_del));
        user.setCreate_time(create_time);
        user.setModify_time(modify_time);
        System.out.println("user = " + user);
        int i = userDao.addUser(user);
        System.out.println("提交成功 " + i);

       /* //3、调佣service
        UserService userService=new UserService();
        Map map = userService.insertByUser(user);

        //4、把map变成json
        String s= JSON.toJSONString(map);

        //5、使用流输出
        PrintWriter printWriter=resp.getWriter();
        printWriter.println(s);
        printWriter.close();*/
    }
}

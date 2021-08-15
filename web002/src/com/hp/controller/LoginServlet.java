package com.hp.controller;

import com.alibaba.fastjson.JSONObject;
import com.hp.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "LoginServlet",urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //要接收登录传过来的三个参数
        //1.修正编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        //2.接手前端传过来的三个参数
        String username = req.getParameter("username");
        String userpassword = req.getParameter("userpassword");
        String code = req.getParameter("code");
        //3.登陆的时候验证验证码是否正确
        //3.1获取后台的验证码
        HttpSession session = req.getSession();
        String codeFromSession = (String)session.getAttribute("code");
        System.out.println("codeFromSession = " + codeFromSession);
        if(!codeFromSession.equals(code)){
            //验证码输入错误，注意前面有！
            //向前端输入一段json，告知前段，验证码错误了
            PrintWriter writer = resp.getWriter();
            Map map = new HashMap();
            map.put("code",400);
            map.put("msg","验证码错误");
            //把map变为json
            String jsonString = JSONObject.toJSONString(map);
            writer.print(jsonString);
            writer.close();
        }else{
            //验证码正确，继续判断账号和密码
            //就需要service、dao层判断，如果咱们业务不是特别多，那么可以不用service
            UserService service = new UserService();
            Map map = service.login(username, userpassword, req);
            String jsonString = JSONObject.toJSONString(map);
            PrintWriter writer = resp.getWriter();
            writer.print(jsonString);
            writer.close();
        }
    }
}

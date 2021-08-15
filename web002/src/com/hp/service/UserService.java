package com.hp.service;

import com.hp.bean.User;
import com.hp.dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    //登录
    public Map login(String username, String password, HttpServletRequest request) {
        Map map = new HashMap();
        //service层要调用dao层
        UserDao dao = new UserDao();
        User userFromDB = dao.login(username, password);
        if (null == userFromDB) {
            //没查出，就是账户密码不正确
            map.put("code", 4001);
            map.put("msg", "账户或者密码不正确");
            return map;
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("user", userFromDB);
            map.put("code", 0);
            map.put("msg", "登录成功");
            return map;
        }
    }
    //带参数的分页查询
    public Map selectAllByParam(Map map1){
        UserDao userDao = new UserDao();
        List<User> users = userDao.selectAllByParam(map1);
        int i = userDao.selectCount(map1);
        Map map = new HashMap();
        map.put("code",0);  //必须和layui的返回的json格式一样
        map.put("msg","查询成功");
        map.put("count",i);
        map.put("data",users);
        //根据layui的返回的json格式去封装给你的数据，如果不一样，需要layui去解析
        //layui的格式
//        { code:0
//        msg:""
//        count:0
//        data,[每条数据]}
    //错误示例
        Map map2 = new HashMap();
        map2.put("number",2001);  //必须和layui的返回的json格式一样
        map2.put("message","数据查询成功");
        map2.put("object",map);
        return map2;
    }
    //修改是否可用
    public Map updateUserById(Integer sfDel,Integer userId){
        UserDao dao = new UserDao();
        int i = dao.updateUserById(sfDel, userId);
       Map map =  new HashMap();
       if(i==1){
           map.put("code",0);
           map.put("msg","修改成功");
       }else{
           map.put("code",400);
           map.put("msg","修改不成功");
       }
       return map;
    }
}

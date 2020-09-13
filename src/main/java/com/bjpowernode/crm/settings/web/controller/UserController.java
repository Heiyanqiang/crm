package com.bjpowernode.crm.settings.web.controller;

import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.settings.service.impl.UserServiceImpl;
import com.bjpowernode.crm.utils.MD5Util;
import com.bjpowernode.crm.utils.PrintJson;
import com.bjpowernode.crm.utils.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //进入到用户控制器
        System.out.println("进入到用户控制器");
        String path = request.getServletPath();
        if("/settings/user/login.do".equals(path)){
            login(request,response);
        }else if("/settings/user/login.do".equals(path)){
            //xxx(request,response);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到验证登录操作");

        String loginAct = request.getParameter("loginAct");
        String loginPwd = request.getParameter("loginPwd");

        //将明文形式转换为MD5的密文形式
        loginPwd = MD5Util.getMD5(loginPwd);

        //接收浏览器端的IP地址
        String ip = request.getRemoteAddr();
        System.out.println("--------------------ip地址:" + ip);
        System.out.println(loginAct);
        System.out.println(loginPwd);


        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());



        try{
            User user = us.login(loginAct,loginPwd,ip);

            request.getSession().setAttribute("user",user);

            PrintJson.printJsonFlag(response,true);
        }catch (Exception e){
            e.printStackTrace();


            String msg = e.getMessage();
            System.out.println("执行完毕");

            Map<String,Object> map = new HashMap<String,Object>();

            map.put("success",false);
            map.put("msg",msg);
            PrintJson.printJsonObj(response,map);

        }

    }
}

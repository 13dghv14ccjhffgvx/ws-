package com.txg.www.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class DispatcherController extends HttpServlet {


    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String methodName=uri.substring(uri.lastIndexOf("/")+1);
        Class<? extends DispatcherController> cls = this.getClass();
        try {
            //反射获取方法名
            Method method = cls.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            //爆破
            method.setAccessible(true);
            //执行方法
            method.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

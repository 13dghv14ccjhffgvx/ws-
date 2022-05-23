package com.txg.www.controller;

import com.txg.www.model.entity.User;
import com.txg.www.service.impl.UserServiceImpl;
import com.txg.www.service.intf.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/CheckLogin")
public class CheckLogin extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取用户名
        String userName = req.getParameter("username");
        //获取用户密码
        String userPwd = req.getParameter("userpwd");
        //获取用户输入的验证码
        String code = req.getParameter("code");
        HttpSession session = req.getSession();
        //获取正确的验证码
        String checkCode = (String) session.getAttribute("checkCode");
        //比较验证码是否正确
        if (!code.equals(checkCode)) {
            //验证码不正确,返回3
            resp.getWriter().write("3");
            return;
        }
        User user = null;
        try {
            UserService userService = new UserServiceImpl();
            user = userService.getUser(userName, userPwd);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (user != null) {
            session.setAttribute("user", user);
            //添加cookie,用于记住用户
            Cookie cookie = new Cookie("JSESSIONID", session.getId());
            //设置cookie时间为30分钟
            cookie.setMaxAge(60 * 30);
            resp.addCookie(cookie);
            //如果roleId=1,表示为普通用户,返回1
            if (user.getRoleId() == 1) {
                resp.getWriter().write("1");
            } else if (user.getRoleId() == 2) {
                //roleId=2,用户角色为管理员,返回2
                resp.getWriter().write("2");
            }
        } else {
            //不存在该用户,返回0
            resp.getWriter().write("0");
        }
    }
}

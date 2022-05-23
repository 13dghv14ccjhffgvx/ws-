package com.txg.www.controller;

import com.google.gson.Gson;
import com.txg.www.model.entity.At;
import com.txg.www.model.entity.User;
import com.txg.www.service.impl.AtServiceImpl;
import com.txg.www.service.impl.UserServiceImpl;
import com.txg.www.service.intf.AtService;
import com.txg.www.service.intf.UserService;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@MultipartConfig
@WebServlet("/at/*")
public class AtController extends DispatcherController {
    Gson gson = new Gson();


    /**
     * at别人
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void at(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        HttpSession session = req.getSession();
        AtService atService = new AtServiceImpl();
        User user = (User) session.getAttribute("user");
        int senderId = user.getUserId();
        String receiverName = req.getParameter("receiverName");
        UserService userService = new UserServiceImpl();
        User receiver = userService.getUserByNickName(receiverName);
        if (receiver == null) {
            resp.getWriter().write("0");
            return;
        }
        int receiverId = receiver.getUserId();
        String message = req.getParameter("message");
        atService.addAt(senderId, receiverId, message);
        resp.getWriter().write("1");
    }

    /**
     * 获取别人发给自己的at
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void getAt(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        HttpSession session = req.getSession();
        AtService atService = new AtServiceImpl();
        User user = (User) session.getAttribute("user");
        int receiverId = user.getUserId();
        //通过接收者的id获取at
        List<At> ats = atService.getAt(receiverId);
        //sends是发送给该用户的人
        List senders = new ArrayList();
        for (At at : ats) {
            //获取发送at的人的id
            int senderId = at.getSenderId();
            //通过id获取usesr对象
            UserService userService = new UserServiceImpl();
            User sender = userService.getUserByUserId(senderId);
            senders.add(sender);
        }
        HashMap<Object, Object> map = new HashMap<>();
        map.put("senders", senders);
        map.put("ats", ats);
        String json = gson.toJson(map);
        resp.getWriter().write(json);
    }

    /**
     * 获取未读的at
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void getUnreadAt(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return;
        }
        int receiverId = user.getUserId();
        //获取未读的at
        AtService atService = new AtServiceImpl();
        List<At> unreadAts = atService.getUnreadAt(receiverId);
        if (unreadAts == null) {
            return;
        }
        String json = gson.toJson(unreadAts);
        resp.getWriter().write(json);
    }

    /**
     * 是at变为已读状态
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void readAt(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int receiverId = user.getUserId();
        AtService atService = new AtServiceImpl();
        atService.modifyStatus(receiverId);
    }
}

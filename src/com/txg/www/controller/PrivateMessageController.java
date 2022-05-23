package com.txg.www.controller;

import com.google.gson.Gson;
import com.txg.www.model.entity.PrivateMessage;
import com.txg.www.model.entity.Relation;
import com.txg.www.model.entity.User;
import com.txg.www.service.impl.PrivateMessageServiceImpl;
import com.txg.www.service.impl.RelationServiceImpl;
import com.txg.www.service.impl.UserServiceImpl;
import com.txg.www.service.intf.PrivateMessageService;
import com.txg.www.service.intf.RelationService;
import com.txg.www.service.intf.UserService;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@MultipartConfig
@WebServlet("/privateMessage/*")
public class PrivateMessageController extends DispatcherController {
    Gson gson = new Gson();


    /**
     * 查找聊天对象
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void getTalkingUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        HashMap<Object, Object> map = new HashMap<>();
        //获取关注的人,只有关注了对方才能给对方方消息
        RelationService relationService = new RelationServiceImpl();
        List<Relation> concerns = relationService.concernList(user.getUserId());
        ArrayList<User> talkers = new ArrayList<>();
        UserService userService = new UserServiceImpl();
        for (Relation concern : concerns) {
            talkers.add(userService.getUserByUserId(concern.getConcernId()));
        }
        PrivateMessageService privateMessageService=new PrivateMessageServiceImpl();
        List<PrivateMessage> unreadMessage = privateMessageService.getUnreadMessage(user.getUserId());
        map.put("unReadMessage", unreadMessage);
        map.put("user", user);
        map.put("talkers", talkers);
        String json = gson.toJson(map);
        resp.getWriter().write(json);
    }

    /**
     * 发送私聊信息
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void sendMessage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        //发送者的id
        int senderId = user.getUserId();
        //接收消息的用户id
        int talkerId = Integer.parseInt(req.getParameter("talkerId"));
        //belong用于判断消息属于哪两个用户之间
        String belong = "";
        //如果发送者id小于接收者的id,
        if (senderId < talkerId) {
            belong = String.valueOf(senderId) + '-' + String.valueOf(talkerId);
        } else {
            //如果发送者id大于接收者的id,
            belong = String.valueOf(talkerId) + '-' + String.valueOf(senderId);
        }
        //belong值为发送者和接收者中较小id的人的id加上-再加上id较大者的id,这样不管是二者谁发的消息belong字段都是一样的
        String message = "";
        try {
            //消息
            message = URLDecoder.decode(req.getParameter("message"), "utf-8");
        } catch (Exception e) {
            message = "";
        }
        //图片路径
        String path = "";
        try {
            Part img = req.getPart("img");
            String dir = getServletContext().getRealPath("/img");
            String header = img.getHeader("Content-Disposition");
            int begin = header.lastIndexOf(".");
            int end = header.lastIndexOf("\"");
            String ext = header.substring(begin, end);
            String imgName = UUID.randomUUID().toString() + ext;
            img.write(dir + "/" + imgName);
            path = "img/" + imgName;
        } catch (Exception e) {
            path = "";
        }
        PrivateMessageService privateMessageService = new PrivateMessageServiceImpl();
        privateMessageService.addMessage(senderId, talkerId, message, path, belong);
    }

    /**
     * 获取私聊消息
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void getMessage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        HttpSession session = req.getSession();
        //接收消息的用户id
        int talkerId = Integer.parseInt(req.getParameter("talkerId"));
        User user = (User) session.getAttribute("user");
        UserService userService = new UserServiceImpl();
        User talker = userService.getUserByUserId(talkerId);
        int senderId = user.getUserId();
        String belong = "";
        if (senderId < talkerId) {
            belong = String.valueOf(senderId) + '-' + String.valueOf(talkerId);
        } else {
            belong = String.valueOf(talkerId) + '-' + String.valueOf(senderId);
        }
        //获取两个人之间的聊天信息
        PrivateMessageService privateMessageService = new PrivateMessageServiceImpl();
        List<PrivateMessage> message = privateMessageService.getMessage(belong);
        RelationService relationService = new RelationServiceImpl();
        List<Relation> concerns = relationService.concernList(user.getUserId());
        ArrayList<User> talkers = new ArrayList<>();
        for (Relation concern : concerns) {
            talkers.add(userService.getUserByUserId(concern.getConcernId()));
        }
        List<PrivateMessage> unreadMessage = privateMessageService.getUnreadMessage(user.getUserId());
        HashMap<Object, Object> map = new HashMap<>();
        map.put("unReadMessage", unreadMessage);
        map.put("talker", talker);
        map.put("talkers", talkers);
        map.put("message", message);
        String json = gson.toJson(map);
        resp.getWriter().write(json);
    }

    /**
     * 撤回消息
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void deleteMessage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        int messageId = Integer.parseInt(req.getParameter("messageId"));
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int senderId = user.getUserId();
        PrivateMessageService privateMessageService = new PrivateMessageServiceImpl();
        boolean loop = privateMessageService.deleteMessage(user.getUserId(), messageId);
        if (loop) {
            resp.getWriter().write("1");
        } else {
            resp.getWriter().write("0");
        }
    }

    /**
     * 修改消息状态
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void modifyStatus(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        System.out.println("请求来了");
        resp.setContentType("application/json;charset=utf-8");
        HttpSession session = req.getSession();
        //发送者的id
        int senderId = Integer.parseInt(req.getParameter("talkerId"));
        User user = (User) session.getAttribute("user");
        //接收消息的用户id
        int receiverId = user.getUserId();
        PrivateMessageService privateMessageService = new PrivateMessageServiceImpl();
        privateMessageService.modifyStatus(senderId, receiverId);

    }


}

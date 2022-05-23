package com.txg.www.controller;

import com.google.gson.Gson;
import com.txg.www.model.entity.Blog;
import com.txg.www.model.entity.Relation;
import com.txg.www.model.entity.User;
import com.txg.www.service.impl.BlogServiceImpl;
import com.txg.www.service.impl.LikeServiceImpl;
import com.txg.www.service.impl.RelationServiceImpl;
import com.txg.www.service.impl.UserServiceImpl;
import com.txg.www.service.intf.BlogService;
import com.txg.www.service.intf.LikeService;
import com.txg.www.service.intf.RelationService;
import com.txg.www.service.intf.UserService;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@MultipartConfig
@WebServlet("/user/*")
public class UserController extends DispatcherController {


    Gson gson = new Gson();

    /**
     * 注册功能
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    public void judgUserName(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        //获取用户名
        String userName = req.getParameter("userName");
        UserService userService = new UserServiceImpl();
        //通过用户名获取用户
        User user = userService.getUserByUserName(userName);
        //user为null，说明该用户名不存在
        if (user == null) {
            //返回0，代表用户名不存在
            resp.getWriter().write("0");
        } else {
            //user不为null，说明该用户名存在
            //返回1，代表用户名已存在
            resp.getWriter().write("1");
        }

    }

    /**
     * 判断昵称是否存在
     * @param req
     * @param resp
     * @throws Exception
     */
    public void judgNickName(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        //获取昵称
        String nickName = req.getParameter("nickName");
        UserService userService = new UserServiceImpl();
        //通过昵称获取用户
        User user = userService.getUserByNickName(nickName);
        //user为null，说明该昵称不存在
        if (user == null) {
            //返回0，代表昵称不存在
            resp.getWriter().write("0");
        } else {
            //user不为null，说明该昵称存在
            //返回1，代表昵称已存在
            resp.getWriter().write("1");
        }

    }

    /**
     * 注册功能
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void register(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        //获取用户名
        String userName = req.getParameter("userName");
        //获取昵称
        String nickName = req.getParameter("nickName");
        //获取密码
        String pwd = req.getParameter("pwd");
        UserService userService = new UserServiceImpl();
        //注册
        userService.register(userName, nickName, pwd);
    }

    /**
     * 退出登录
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        //销毁session
        session.invalidate();
    }

    /**
     * 获取用户信息
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    public void getUserMessage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        HttpSession session = req.getSession();
        //通过session获取用户
        User user = (User) session.getAttribute("user");
        //获取用户id
        int userId = user.getUserId();
        LikeService likeService = new LikeServiceImpl();
        //通过用户id获取点赞数
        int likeNum = likeService.getLikeNum(userId);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("user", user);
        //时间格式转化
        map.put("time", user.getCreateTime().toString());
        map.put("likeNum", likeNum);
        String json = gson.toJson(map);
        resp.getWriter().write(json);
    }

    /**
     * 修改用户信息
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    public void modifyMessage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        //获取昵称
        String name = URLDecoder.decode(req.getParameter("name"), "utf-8");
        //性别
        String sex = URLDecoder.decode(req.getParameter("sex"), "utf-8");
        //个人简介
        String introduction = URLDecoder.decode(req.getParameter("introduction"), "utf-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        String path = "";
        //获取图片文件并转为路径
        try {
            Part img = req.getPart("headImg");
            String dir = getServletContext().getRealPath("/img");
            String header = img.getHeader("Content-Disposition");
            int begin = header.lastIndexOf(".");
            int end = header.lastIndexOf("\"");
            String ext = header.substring(begin, end);
            String imgName = UUID.randomUUID().toString() + ext;
            img.write(dir + "/" + imgName);
            path = "img/" + imgName;
        } catch (Exception e) {
            path = user.getHeadImg();
        }
        UserService userService = new UserServiceImpl();
        //修改用户信息
        userService.modifyUser(name, sex, introduction, path, userId);
        try {
            user = userService.getUserByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        //重新在session中添加user
        session.setAttribute("user", user);
        String json = gson.toJson(user);
        resp.getWriter().write(json);
    }

    /**
     * 获取关注的用户
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void getConcerns(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        //获取用户id
        int userId = Integer.parseInt(req.getParameter("userId"));
        RelationService relationService = new RelationServiceImpl();
        //通过用户id获取关注的用户
        List<Relation> concerns = relationService.concernList(userId);
        List concernUsers = new ArrayList<>();
        UserService userService = new UserServiceImpl();
        // 通过关注的用户id查询用户
        for (Relation concern : concerns) {
            User concernUser = userService.getUserByUserId(concern.getConcernId());
            concernUsers.add(concernUser);
        }
        String json = gson.toJson(concernUsers);
        resp.getWriter().write(json);
    }

    /**
     * 获取粉丝
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void getFans(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        //获取用户id
        int userId = Integer.parseInt(req.getParameter("userId"));
        RelationService relationService = new RelationServiceImpl();
        //通过用户id获取用户的粉丝
        List<Relation> fans = relationService.fanList(userId);
        List fanUsers = new ArrayList<>();
        UserService userService = new UserServiceImpl();
        //通过粉丝id查询粉丝
        for (Relation fan : fans) {
            User fanUser = userService.getUserByUserId(fan.getUserId());
            fanUsers.add(fanUser);
        }
        String json = gson.toJson(fanUsers);
        resp.getWriter().write(json);
    }

    /**
     * 获取自己写的文章
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void getMyBlog(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        BlogService blogService = new BlogServiceImpl();
        //通过用户id查询用户发布的文章
        List<Blog> myBlog = blogService.searchBlogByUserId(userId);
        String json = gson.toJson(myBlog);
        resp.getWriter().write(json);
    }

    /**
     * 修改自己的文章
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void modifyBlog(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
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

        }
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        //获取文章内容
        String content = URLDecoder.decode(req.getParameter("content"), "utf-8");
        //获取文章标题
        String title = URLDecoder.decode(req.getParameter("title"), "utf-8");
        //获取文章类型
        String type = URLDecoder.decode(req.getParameter("type"), "utf-8");
        //获取文章id
        int blogId = Integer.parseInt(req.getParameter("blogId"));
        BlogService blogService = new BlogServiceImpl();
        //修改文章
        blogService.modifyBlog(user.getUserId(), blogId, type, title, content, path);

    }

    public void getAllUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        UserService userService = new UserServiceImpl();
        //获取除管理员外的所有用户
        List<User> users = userService.getAllUser();
        String json = gson.toJson(users);
        resp.getWriter().write(json);
    }

    /**
     * 删除文章
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void deleteMyBlog(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        //获取文章id
        int blogId = Integer.parseInt(req.getParameter("blogId"));
        //删除文章
        BlogService blogService = new BlogServiceImpl();
        blogService.removeMyBlog(user.getUserId(), blogId);
        resp.getWriter().write("1");
    }

    /**
     * 批量删除文章
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void deleteMyBlogs(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        //获取文章id
        String blogIds = req.getParameter("blogIds");
        //根据逗号隔开
        String[] strs = blogIds.split(",");
        BlogService blogService = new BlogServiceImpl();
        for (int i = 0; i < strs.length; i++) {
            int blogId = Integer.parseInt(strs[i]);
            blogService.removeMyBlog(user.getUserId(), blogId);
            //删除文章
        }
        resp.getWriter().write("1");
    }

    /**
     * 得到他人空间的信息
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void getOthersMessage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        LikeService likeService = new LikeServiceImpl();
        //获取用户id
        int userId = Integer.parseInt(req.getParameter("userId"));
        //根据用户id获取用户的点赞数
        int likeNum = likeService.getLikeNum(userId);
        UserService userService = new UserServiceImpl();
        User user = userService.getUserByUserId(userId);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("likeNum", likeNum);
        String jsonStr = gson.toJson(map);
        resp.getWriter().write(jsonStr);
    }

    public void judgPwd(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        String pwd = req.getParameter("pwd");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        UserService userService=new UserServiceImpl();
        User checkUser = userService.getUser(user.getUserName(), pwd);
        if (checkUser!=null){
            resp.getWriter().write("1");
        }else {
            resp.getWriter().write("0");
        }

    }

    /**
     * 获取他人发布的文章
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void getOthersBlogs(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        //用户id
        int userId = Integer.parseInt(req.getParameter("userId"));
        BlogService blogService = new BlogServiceImpl();
        //通过用户id查询用户文章
        List<Blog> blogs = blogService.searchBlogByUserId(userId);
        String json = gson.toJson(blogs);
        resp.getWriter().write(json);
    }

    /**
     * 修改密码
     * @param req
     * @param resp
     * @throws Exception
     */
    public void modifyPwd(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        String pwd = req.getParameter("pwd");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        UserService userService=new UserServiceImpl();
        userService.modifyPwd(pwd,user.getUserId());
        resp.getWriter().write("1");
    }

}

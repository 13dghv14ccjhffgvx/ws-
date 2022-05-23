package com.txg.www.controller;

import com.google.gson.Gson;
import com.txg.www.model.entity.Blog;
import com.txg.www.model.entity.Comment;
import com.txg.www.model.entity.User;
import com.txg.www.service.impl.AdminServiceImpl;
import com.txg.www.service.impl.BlogServiceImpl;
import com.txg.www.service.impl.CommentServiceImpl;
import com.txg.www.service.intf.AdminService;
import com.txg.www.service.intf.BlogService;
import com.txg.www.service.intf.CommentService;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@MultipartConfig
@WebServlet("/comment/*")
public class CommentController extends DispatcherController {

    Gson gson = new Gson();

    /**
     * 评论
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    public void comment(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        //用户已经登录
        if (user != null) {
            //判断用户是否给封禁
            AdminService adminService = new AdminServiceImpl();
            User banUser = adminService.getBanUser(user.getUserId());
            if (banUser != null) {
                //用户给封禁了,返回2
                resp.getWriter().write("2");
                return;
            }
            //获取评论内容
            String comment = req.getParameter("comment");
            //获取文章id
            int blogId = Integer.parseInt(req.getParameter("blogId"));
            CommentService commentService = new CommentServiceImpl();
            commentService.addComment(user.getUserId(), blogId, comment, user.getNickName());
            resp.getWriter().write("1");
        } else {
            //用户未登录,返回0
            resp.getWriter().write("0");
        }
    }

    public void reply(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        //用户已经登录
        if (user != null) {
            //判断用户是否给封禁
            AdminService adminService = new AdminServiceImpl();
            User banUser = adminService.getBanUser(user.getUserId());
            if (banUser != null) {
                //用户给封禁了,返回2
                resp.getWriter().write("2");
                return;
            }
            //获取评论内容
            String comment = req.getParameter("comment");
            //获取文章id
            int blogId = Integer.parseInt(req.getParameter("blogId"));
            int parentCommentId = Integer.parseInt(req.getParameter("parentCommentId"));
            CommentService commentService = new CommentServiceImpl();
            commentService.addReply(user.getUserId(), blogId, comment, user.getNickName(), parentCommentId);
            resp.getWriter().write("1");
        } else {
            //用户未登录,返回0
            resp.getWriter().write("0");
        }
    }

    /**
     * 获取自己的评论
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void getMyComments(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        BlogService blogService = new BlogServiceImpl();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        //用过用户id获取用户发表的评论
        CommentService commentService = new CommentServiceImpl();
        List<Comment> myComments = commentService.getUserComments(userId);
        ArrayList<Blog> blogs = new ArrayList<>();
        //根据评论的文章id获取文章
        for (Comment myComment : myComments) {
            Blog blog = blogService.getBlogById(myComment.getBlogId());
            blogs.add(blog);
        }
        HashMap<Object, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("myComments", myComments);
        map.put("blogs", blogs);
        String json = gson.toJson(map);
        resp.getWriter().write(json);
    }

    /**
     * 删除评论
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void deleteMyComment(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        //得到评论id
        int commentId = Integer.parseInt(req.getParameter("commentId"));
        //删除评论
        CommentService commentService = new CommentServiceImpl();
        commentService.removeComment(userId, commentId);

    }

    /**
     * 批量删除评论
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void deleteMyComments(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        CommentService commentService = new CommentServiceImpl();
        //获取文章id
        String commentIds = req.getParameter("commentIds");
        //根据逗号隔开
        String[] strs = commentIds.split(",");
        for (int i = 0; i < strs.length; i++) {
            int commentId = Integer.parseInt(strs[i]);
            //删除
            commentService.removeComment(user.getUserId(), commentId);
        }
        resp.getWriter().write("1");
    }

    public void getOthersComments(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        //获取他人的用户id
        int userId = Integer.parseInt(req.getParameter("userId"));
        //获取他人发的评论
        CommentService commentService = new CommentServiceImpl();
        List<Comment> comments = commentService.getUserComments(userId);
        String json = gson.toJson(comments);
        resp.getWriter().write(json);
    }
}

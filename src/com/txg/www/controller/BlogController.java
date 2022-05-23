package com.txg.www.controller;

import com.google.gson.Gson;
import com.txg.www.model.entity.*;
import com.txg.www.service.impl.*;
import com.txg.www.service.intf.*;
import com.txg.www.until.CommentSetChildNode;
import com.txg.www.until.PageHelper;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.*;

@MultipartConfig
@WebServlet("/blog/*")
public class BlogController extends DispatcherController {
    Gson gson = new Gson();

    /**
     * 获取当前博客文章
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    public void getBlog(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        //获取文章id
        String BlogId = req.getParameter("BlogId");
        int blogId = Integer.parseInt(BlogId);
        Blog blog;
        List<Comment> comments = null;
        List<User> users = new ArrayList<>();
        //通过文章id获取文章
        BlogService blogService = new BlogServiceImpl();
        blog = blogService.getBlogById(blogId);
        //通过文章id获取改文章的评论
        CommentService commentService = new CommentServiceImpl();
        comments = commentService.getAllComment(blogId);
        CommentSetChildNode.setChildNode(comments);
        UserService userService = new UserServiceImpl();
        for (Comment comment : comments) {
            //通过评论的用户id获取用户
            User commentSender = userService.getUserByUserId(comment.getUserId());
            users.add(commentSender);
        }
        User author = userService.getUserByUserId(blog.getUserId());
        HashMap<String, Object> map = new HashMap<>();
        User user = (User)req.getSession().getAttribute("user");
        //文章
        map.put("Blog", blog);
        //作者
        map.put("author", author);
        //评论
        map.put("commentList", comments);
        //发表评论的用户
        map.put("users", users);
        map.put("user",user);
        String josn = gson.toJson(map);
        resp.getWriter().write(josn);
    }

    /**
     * 获取当前分类下的所有的文章
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    public void listBlog(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        //每页的文章数
        int pageSize = 10;
        //获取网页当前页数
        String num = req.getParameter("pageNum");
        int pageNum = num == null ? 1 : Integer.parseInt(req.getParameter("pageNum"));
        //获取文章类型
        String type = req.getParameter("type");
        PageHelper<Blog> blogPageHelper = null;
        //获取该页下的所有文章
        BlogService blogService = new BlogServiceImpl();
        blogPageHelper = blogService.listBlogsByPage(type, pageNum, pageSize);
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        HashMap<Object, Object> map = new HashMap<>();
        map.put("blogs", blogPageHelper);
        List<Blog> blogOredrByLikeNum = blogService.getBlogOrderByLikeNum();
        List<Blog> blogOrderByCollectNum = blogService.getBlogOrderByCollectNum();
        map.put("likeBlogList", blogOredrByLikeNum);
        map.put("collectBlogList", blogOrderByCollectNum);
        if (user != null) {
            map.put("user", user);
        }
        String json = gson.toJson(map);
        resp.getWriter().write(json);
    }

    /**
     * 获取该用户的所有的文章
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    public void userBlog(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        //获取用户id
        int userId = Integer.parseInt(req.getParameter("userId"));
        //通过用户id获取该用户写的文章
        BlogService blogService = new BlogServiceImpl();
        List<Blog> myBlog = blogService.searchBlogByUserId(userId);
        String json = gson.toJson(myBlog);
        resp.getWriter().write(json);
    }

    /**
     * 写文章
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    public void write(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        resp.setContentType("application/json;charset=utf-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        AdminService adminService=new AdminServiceImpl();
        //文章的图片路径
        String path = "";
        //获取图片文件并转为路径
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

        //获取文章内容
        String content = URLDecoder.decode(req.getParameter("content"), "utf-8");
        //获取文章标题
        String title = URLDecoder.decode(req.getParameter("title"), "utf-8");
        //获取文章类型
        String type = URLDecoder.decode(req.getParameter("type"), "utf-8");
        //发表文章
        BlogService blogService = new BlogServiceImpl();
        blogService.addBlog(type, content, path, title, user.getUserId());
        //删除草稿
        DraftService draftService = new DraftServiceImpl();
        draftService.removeDraft(user.getUserId());
    }

    /**
     * 保存草稿
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    public void save(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        //文章内容
        String content = URLDecoder.decode(req.getParameter("content"), "utf-8");
        //文章标题
        String title = URLDecoder.decode(req.getParameter("title"), "utf-8");
        //文章类型
        String type = URLDecoder.decode(req.getParameter("type"), "utf-8");
        Draft draft = null;
        //判断草稿是否为空
        DraftService draftService = new DraftServiceImpl();
        try {
            draft = draftService.getDraft(user.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //草稿为空，添加草稿
        if (draft == null) {
            draftService.addDraft(title, content, type, user.getUserId());
        } else {
            //草稿不为空，更新草稿
            draftService.modify(title, content, type, user.getUserId());
        }
    }

    /**
     * 获取草稿
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    public void getDraft(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=utf-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Draft draft = null;
        //获取草稿
        DraftService draftService = new DraftServiceImpl();
        try {
            draft = draftService.getDraft(user.getUserId());
        } catch (Exception e) {
            draft = null;
        }
        HashMap<Object, Object> map = new HashMap<>();
        map.put("draft", draft);
        map.put("user", user);
        String jsonStr = gson.toJson(map);
        resp.getWriter().write(jsonStr);
    }

    /**
     * 点赞
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    public void like(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Like like = null;
        BlogService blogService = new BlogServiceImpl();
        //用户未登录，直接返回0
        LikeService likeService = new LikeServiceImpl();
        if (user == null) {
            resp.getWriter().write("0");
        } else {
            //用户登录了,获取当前博客文章
            int blogId = Integer.parseInt(req.getParameter("BlogId"));
            try {
                //判断是否已经点赞
                like = likeService.getLike(user.getUserId(), blogId);
            } catch (Exception e) {

            }
            if (like == null) {
                //未点赞，添加点赞
                likeService.addLike(user.getUserId(), blogId);
                blogService.modifyLikeNum(blogId, 1);
            } else {
                //已经点赞、取消点赞
                likeService.cancelLike(user.getUserId(), blogId);
                blogService.modifyLikeNum(blogId, -1);
            }
            Blog blog = null;
            try {
                //重新获取点赞之后的文章信息返回给前端，
                blog = blogService.getBlogById(blogId);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String json = gson.toJson(blog);
            resp.getWriter().write(json);
        }
    }


    /**
     * 添加关注
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void addConcern(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int authorId = Integer.parseInt(req.getParameter("authorId"));
        if (user != null) {
            int userId = user.getUserId();
            //判断用户和作者是否是同一人
            if (userId == authorId) {
                resp.getWriter().write("2");
            } else {
                RelationService relationService = new RelationServiceImpl();
                Relation concern = relationService.concern(userId, authorId);
                //判断用户是否已经关注作者
                if (concern != null) {
                    //已关注则取消关注
                    relationService.removeConcern(userId, authorId);
                    resp.getWriter().write("3");
                } else {
                    //未关注，添加关注
                    relationService.addConcern(userId, authorId);
                    resp.getWriter().write("1");
                }
            }
        } else {
            //用户未登录
            resp.getWriter().write("0");
        }
    }
}

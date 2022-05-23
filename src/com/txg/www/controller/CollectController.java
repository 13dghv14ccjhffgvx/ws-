package com.txg.www.controller;

import com.google.gson.Gson;
import com.txg.www.model.entity.Blog;
import com.txg.www.model.entity.Collect;
import com.txg.www.model.entity.User;
import com.txg.www.service.impl.BlogServiceImpl;
import com.txg.www.service.impl.CollectServiceImpl;
import com.txg.www.service.impl.CommentServiceImpl;
import com.txg.www.service.intf.BlogService;
import com.txg.www.service.intf.CollectService;
import com.txg.www.service.intf.CommentService;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@MultipartConfig
@WebServlet("/collect/*")
public class CollectController extends DispatcherController {


    /**
     * 添加收藏
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void addCollect(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.getWriter().write("0");
        } else {
            //获取文章id
            int blogId = Integer.parseInt(req.getParameter("blogId"));
            CollectService collectService = new CollectServiceImpl();
            if (collectService.addCollect(user.getUserId(), blogId)) {
                //收藏成功，返回1
                resp.getWriter().write("1");
            } else {
                //取消收藏，返回0
                resp.getWriter().write("2");
            }

        }
    }

    /**
     * 获取收藏的文章
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void getMyCollects(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        //用户id
        int userId = user.getUserId();
        //查询用户收藏
        CollectService collectService = new CollectServiceImpl();
        List<Collect> collects = collectService.getcollects(userId);
        List<Blog> blogs = new ArrayList<>();
        BlogService blogService = new BlogServiceImpl();
        //根据收藏的文章id获取文章
        for (Collect collect : collects) {
            Blog blog = blogService.getBlogById(collect.getBlogId());
            blogs.add(blog);
        }
        Gson gson = new Gson();
        String json = gson.toJson(blogs);
        resp.getWriter().write(json);
    }

    /**
     * 获取他人的收藏
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void getOthersCollects(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        //查询用户收藏，用户id
        int userId = Integer.parseInt(req.getParameter("userId"));
        CollectService collectService = new CollectServiceImpl();
        List<Collect> collects = collectService.getcollects(userId);
        List<Blog> blogs = new ArrayList<>();
        BlogService blogService = new BlogServiceImpl();
        for (Collect collect : collects) {
            Blog blog = blogService.getBlogById(collect.getBlogId());
            blogs.add(blog);
        }
        Gson gson = new Gson();
        String json = gson.toJson(blogs);
        resp.getWriter().write(json);

    }

    /**
     * 删除收藏
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void deleteMyCollect(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        //收藏的文章id
        int blogId = Integer.parseInt(req.getParameter("blogId"));
        //删除
        CollectService collectService = new CollectServiceImpl();
        collectService.removeCollect(user.getUserId(), blogId);
        resp.getWriter().write("1");

    }

    /**
     * 批量删除收藏
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void deleteMyCollects(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        CollectService collectService = new CollectServiceImpl();
        //获取收藏id
        String collectIds = req.getParameter("collectIds");
        //根据逗号隔开
        String[] strs = collectIds.split(",");
        for (int i = 0; i < strs.length; i++) {
            int collectId = Integer.parseInt(strs[i]);
            //删除
            collectService.removeCollect(user.getUserId(), collectId);
        }
        resp.getWriter().write("1");
    }
}

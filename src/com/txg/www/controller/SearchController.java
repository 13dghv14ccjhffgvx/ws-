package com.txg.www.controller;

import com.google.gson.Gson;
import com.txg.www.model.entity.Blog;
import com.txg.www.model.entity.User;
import com.txg.www.service.impl.BlogServiceImpl;
import com.txg.www.service.impl.UserServiceImpl;
import com.txg.www.service.intf.BlogService;
import com.txg.www.service.intf.UserService;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.List;

@MultipartConfig
@WebServlet("/search/*")
public class SearchController extends DispatcherController {

    Gson gson = new Gson();

    /**
     * 搜索用户
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void serachUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        //用户昵称
        String nickName = URLDecoder.decode(req.getParameter("userNickName"), "utf-8");
        UserService userService = new UserServiceImpl();
        //通过昵称查询用户
        List<User> users = userService.searchUsersByNickName(nickName);
        String json = gson.toJson(users);
        resp.getWriter().write(json);
    }

    /**
     * 搜索文章
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void searchBlog(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        //文章标题
        String title = URLDecoder.decode(req.getParameter("title"), "utf-8");
        BlogService blogService = new BlogServiceImpl();
        //通过标题查询文章
        List<Blog> blogs = blogService.searchBlogByTitle(title);
        String json = gson.toJson(blogs);
        resp.getWriter().write(json);
    }
}

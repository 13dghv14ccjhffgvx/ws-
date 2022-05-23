package com.txg.www.controller;


import com.google.gson.Gson;
import com.txg.www.model.entity.Blog;
import com.txg.www.model.entity.Report;
import com.txg.www.model.entity.User;
import com.txg.www.service.impl.BlogServiceImpl;
import com.txg.www.service.impl.ReportServiceImpl;
import com.txg.www.service.impl.UserServiceImpl;
import com.txg.www.service.intf.BlogService;
import com.txg.www.service.intf.ReportService;
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
@WebServlet("/report/*")
public class ReportController extends DispatcherController {

    Gson gson = new Gson();

    /**
     * 举报文章
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void report(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        //获取举报的截图
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
            System.out.println("图片为空");
        }
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        //举报内容
        String reportContent = URLDecoder.decode(req.getParameter("reportContent"), "utf-8");
        //举报类型
        String reportType = URLDecoder.decode(req.getParameter("reportType"), "utf-8");
        //举报的文章id
        int blogId = Integer.parseInt(req.getParameter("blogId"));
        ReportService reportService = new ReportServiceImpl();
        reportService.addReport(blogId, userId, reportType, reportContent, path);
    }

    /**
     * 获取所有举报
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void getReports(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        ReportService reportService = new ReportServiceImpl();
        List<Report> reports = reportService.getReports();
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Blog> blogs = new ArrayList<>();
        BlogService blogService = new BlogServiceImpl();
        UserService userService = new UserServiceImpl();
        for (Report report : reports) {
            //获取举报的文章
            Blog blog = blogService.getBlogById(report.getBlogId());
            blogs.add(blog);
            //发起举报的用户
            User user = userService.getUserByUserId(report.getUserId());
            users.add(user);
        }
        HashMap<Object, Object> map = new HashMap<>();
        map.put("reports", reports);
        map.put("blogs", blogs);
        map.put("users", users);
        String json = gson.toJson(map);
        resp.getWriter().write(json);
    }

    /**
     * 获取举报
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void getReport(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        //举报id
        int reportId = Integer.parseInt(req.getParameter("reportId"));
        ReportService reportService = new ReportServiceImpl();
        //通过举报id获取举报内容
        Report report = reportService.getReport(reportId);
        BlogService blogService = new BlogServiceImpl();
        //举报的文章
        Blog blog = blogService.getBlogById(report.getBlogId());
        HashMap<Object, Object> map = new HashMap<>();
        map.put("report",report);
        map.put("blog",blog);
        String json = gson.toJson(map);
        resp.getWriter().write(json);
    }

    /**
     * 获取已审核的举报
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void getCompleteReports(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        ReportService reportService = new ReportServiceImpl();
        //获取已经审核的举报
        List<Report> reports = reportService.getCompleteReports();
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Blog> blogs = new ArrayList<>();
        BlogService blogService = new BlogServiceImpl();
        UserService userService = new UserServiceImpl();
        for (Report report : reports) {
            //获取文章
            Blog blog = blogService.getBlogById(report.getBlogId());
            blogs.add(blog);
            //发起举报的用户
            User user = userService.getUserByUserId(report.getUserId());
            users.add(user);
        }
        HashMap<Object, Object> map = new HashMap<>();
        map.put("reports", reports);
        map.put("blogs", blogs);
        map.put("users", users);
        String json = gson.toJson(map);
        resp.getWriter().write(json);
    }

    /**
     * 获取未审核的举报
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void getIncompleteReports(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        ReportService reportService = new ReportServiceImpl();
        //未审核的举报
        List<Report> reports = reportService.getIncompleteReports();
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Blog> blogs = new ArrayList<>();
        BlogService blogService = new BlogServiceImpl();
        UserService userService = new UserServiceImpl();
        for (Report report : reports) {
            //举报文章
            Blog blog = blogService.getBlogById(report.getBlogId());
            blogs.add(blog);
            //发起举报的用户
            User user = userService.getUserByUserId(report.getUserId());
            users.add(user);
        }
        HashMap<Object, Object> map = new HashMap<>();
        map.put("reports", reports);
        map.put("blogs", blogs);
        map.put("users", users);
        String json = gson.toJson(map);
        resp.getWriter().write(json);
    }
}

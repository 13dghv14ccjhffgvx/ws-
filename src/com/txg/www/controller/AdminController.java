package com.txg.www.controller;

import com.google.gson.Gson;
import com.txg.www.model.entity.User;
import com.txg.www.service.impl.AdminServiceImpl;
import com.txg.www.service.impl.UserServiceImpl;
import com.txg.www.service.intf.AdminService;
import com.txg.www.service.intf.UserService;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@MultipartConfig
@WebServlet("/admin/*")
public class AdminController extends DispatcherController {
    Gson gson = new Gson();

    /**
     * 管理员删除文章
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void adminDeleteBlog(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        AdminService adminService = new AdminServiceImpl();
        resp.setContentType("application/json;charset=utf-8");
        int blogId = Integer.parseInt(req.getParameter("blogId"));
        boolean loop = adminService.adminRemoveBlog(blogId);
        resp.getWriter().write("1");
    }

    /**
     * 管理员删除评论
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void adminDeleteComment(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        AdminService adminService = new AdminServiceImpl();
        resp.setContentType("application/json;charset=utf-8");
        int commentId = Integer.parseInt(req.getParameter("commentId"));
        boolean loop = adminService.adminRemoveComment(commentId);
        resp.getWriter().write("1");
    }

    /**
     * 管理员审核举报
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void adminCheckReport(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        AdminService adminService = new AdminServiceImpl();
        resp.setContentType("application/json;charset=utf-8");
        int reportId = Integer.parseInt(req.getParameter("reportId"));
        boolean loop = adminService.adminCheckReport(reportId);
        resp.getWriter().write("1");

    }


    /**
     * 封禁用户
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void adminBanUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        AdminService adminService = new AdminServiceImpl();
        //封禁的用户
        int userId = Integer.parseInt(req.getParameter("userId"));
        //封禁时间
        int banTime = Integer.parseInt(req.getParameter("banTime"));
        boolean loop = adminService.adminBanUser(userId, banTime);
        resp.getWriter().write("1");
    }

    /**
     * 得到封禁用户
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void getBanUsers(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        AdminService adminService = new AdminServiceImpl();
        resp.setContentType("application/json;charset=utf-8");
        List<User> users = adminService.getBanUsers();
        String json = gson.toJson(users);
        resp.getWriter().write(json);
    }

    /**
     * 得到未封禁用户
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void getNormalUsers(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        AdminService adminService = new AdminServiceImpl();
        resp.setContentType("application/json;charset=utf-8");
        List<User> users = adminService.getNormalUsers();
        String json = gson.toJson(users);
        resp.getWriter().write(json);
    }

    /**
     * 解封用户
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void unblockUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        AdminService adminService = new AdminServiceImpl();
        resp.setContentType("application/json;charset=utf-8");
        int userId = Integer.parseInt(req.getParameter("userId"));
        boolean loop = adminService.unblockUser(userId);
        resp.getWriter().write("1");
    }

    /**
     * 通过用户id获取已封禁的用户
     * @param req
     * @param resp
     * @throws Exception
     */
    public void getBanUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        AdminService adminService = new AdminServiceImpl();
        resp.setContentType("application/json;charset=utf-8");
        //获取用户id
        int userId = Integer.parseInt(req.getParameter("userId"));
        User user = adminService.getBanUser(userId);
        //user为null，表示用户没有被封禁
        if (user != null) {
            //用户被封禁，返回1
            resp.getWriter().write("1");
        } else {
            //用户未被封禁，返回0
            resp.getWriter().write("0");
        }
    }

    /**
     * 批量删除文章
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    public void deleteBlogs(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        AdminService adminService = new AdminServiceImpl();
        //获取文章id
        //获取文章id
        String blogIds = req.getParameter("blogIds");
        //根据,截断
        String[] strs = blogIds.split(",");
        for (int i = 0; i < strs.length; i++) {
            int blogId = Integer.parseInt(strs[i]);
            //删除文章
            adminService.adminRemoveBlog(blogId);
        }
        resp.getWriter().write("1");
    }

    /**
     * 搜索用户
     * @param req
     * @param resp
     * @throws Exception
     */
    public void adminSearchUsers(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        //获取用户昵称
        String nickName = req.getParameter("nickName");
        AdminService adminService = new AdminServiceImpl();
        UserService userService = new UserServiceImpl();
        //通过昵称得到用户
        List<User> users = userService.searchUsersByNickName(nickName);
        //用户封禁状态
        ArrayList<Object> banStatus = new ArrayList<>();
        for (User user : users) {
            //判断用户是否被封禁
            User banUser = adminService.getBanUser(user.getUserId());
            System.out.println(banUser);
            if (banUser==null){
                //0代表未封禁
                banStatus.add("0");
            }else {
                //1代表已经封禁
                banStatus.add("1");
            }
        }
        HashMap<Object, Object> map = new HashMap<>();
        map.put("users",users);
        map.put("status",banStatus);
        String json = gson.toJson(map);
        resp.getWriter().write(json);
    }
}

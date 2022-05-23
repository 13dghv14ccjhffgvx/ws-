package com.txg.www.until;

import com.txg.www.model.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(urlPatterns = {"/addblog.html", "/userinfo.html", "/modify.html", "/logout.html", "/personSpace.html", "/commentManage.html", "/collectManage.html", "/contentManage.html", "/admin.html","/adminBan.html", "/adminBlogManage.html", "/adminReport.html", "/adminReportContent.html", "/adminSearch.html", "/admintouser.html", "/adminUserManage.html","/at.html","/privateMessage.html","/modifyPwd.html"})
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 判断用户是否登录，同时根据用户角色过滤用户网页的访问权限
     * @param servletRequest
     * @param servletResponse
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //管理员的访问网址
        String adminUrls[] = {"/admin.html", "/adminBlogManage.html", "/adminReport.html", "/adminReportContent.html", "/adminSearch.html", "/admintouser.html", "/adminUserManage.html","/adminBan.html"};
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        String url = request.getRequestURL().toString();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            //判断用户的角色权限，1代表是普通用户
            if (user.getRoleId() == 1) {
                //遍历管理员的url
                for (String adminUrl : adminUrls) {
                    //如果有普通用户访问管理员的url就return
                    if (url.contains(adminUrl)) {
                        return;
                    }
                }
            }
            chain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("login.html").forward(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}

package com.txg.www.service.impl;

import com.txg.www.dao.impl.*;
import com.txg.www.dao.intf.*;
import com.txg.www.model.entity.User;
import com.txg.www.service.intf.AdminService;

import java.sql.SQLException;
import java.util.List;

public class AdminServiceImpl implements AdminService {

    AdminDao adminDao = new AdminDaoImpl();
    CollectDao collectDao = new CollectDaoImpl();
    LikeDao likeDao = new LikeDaoImpl();
    ReportDao reportDao = new ReportDaoImpl();
    CommentDao commentDao = new CommentDaoImpl();

    @Override
    public boolean adminRemoveBlog(int blogId) throws SQLException {
        collectDao.deleteCollectByBlogId(blogId);
        likeDao.deleteLikeByBlogId(blogId);
        reportDao.deleteReportByBlogId(blogId);
        commentDao.deleteCommentsByBlogId(blogId);
        return 1 == adminDao.adminDeleteBlog(blogId);
    }

    @Override
    public boolean adminBanUser(int userId, int banTime) throws SQLException {
        return 1 == adminDao.adminInsertUserBan(userId, banTime);
    }

    @Override
    public boolean adminRemoveComment(int commentId) throws SQLException {
        return 1 == adminDao.adminDeleteComment(commentId);
    }

    @Override
    public boolean adminCheckReport(int reportId) throws SQLException {
        return 1 == adminDao.adminUpdateReportStatus(reportId);
    }

    @Override
    public List<User> getBanUsers() throws Exception {

        return adminDao.selectBanUsers();
    }

    @Override
    public List<User> getNormalUsers() throws Exception {
        return adminDao.selectNormalUsers();
    }

    @Override
    public boolean unblockUser(int userId) throws SQLException {
        return 1 == adminDao.deleteBan(userId);
    }

    @Override
    public User getBanUser(int userId) throws Exception {
        return adminDao.selectBanUserByUserId(userId);
    }
}

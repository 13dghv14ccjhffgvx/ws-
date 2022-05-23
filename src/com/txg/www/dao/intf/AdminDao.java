package com.txg.www.dao.intf;

import com.txg.www.model.entity.User;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface AdminDao {
    /**
     * 管理员删除文章
     * @param blogId 文章id
     * @return
     */
    int adminDeleteBlog(int blogId) throws SQLException;

    /**
     * 管理员删除评论
     * @param commentId 评论id
     * @return
     */
    int adminDeleteComment(int commentId) throws SQLException;

    /**
     * 修改举报的审核状态
     * @param reportId 举报id
     * @return
     */
    int adminUpdateReportStatus(int reportId) throws SQLException;

    /**
     * 封禁用户
     * @param userId 用户id
     * @param banTime 封禁时间
     * @return
     */
    int adminInsertUserBan(int userId,int banTime) throws SQLException;

    /**
     * 获取未封禁用户
     * @return
     * @throws Exception
     */
    List<User> selectNormalUsers() throws Exception;

    /**
     * 获取封禁用户
     * @return
     * @throws Exception
     */
    List<User> selectBanUsers() throws Exception;

    /**
     * 解封
     * @param userId
     * @return
     */
    int deleteBan(int userId) throws SQLException;

    /**
     * 判断用户是否被封
     * @param userId 用户id
     * @return
     * @throws Exception
     */
    User selectBanUserByUserId(int userId) throws Exception;

}

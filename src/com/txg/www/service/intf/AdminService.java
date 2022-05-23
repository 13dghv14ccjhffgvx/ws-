package com.txg.www.service.intf;

import com.txg.www.model.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface AdminService {
    /**
     * 管理员删除违规文章
     * @param blogId 文章id
     * @return
     */
    boolean adminRemoveBlog(int blogId) throws SQLException;

    /**
     * 管理员删除违规评论
     * @param commentId 评论id
     * @return
     */
    boolean adminRemoveComment(int commentId) throws SQLException;

    /**
     * 管理员审核举报,使举报状态改为已审核
     * @param reportId 举报id
     * @return
     */
    boolean adminCheckReport(int reportId) throws SQLException;

    /**
     * 封禁用户
     * @param userId 用户id
     * @param banTime 封禁世纪
     * @return
     */
    boolean adminBanUser(int userId,int banTime) throws SQLException;

    /**
     * 得到封禁用户
     * @return
     * @throws Exception
     */
    List<User> getBanUsers() throws Exception;

    /**
     * 得到未封禁用户
     * @return
     * @throws Exception
     */
    List<User> getNormalUsers() throws Exception;

    /**
     * 解封用户
     * @param userId 用户id
     * @return
     */
    boolean unblockUser(int userId) throws SQLException;

    /**
     * 判断用户是否被封
     * @param userId
     * @return
     * @throws Exception
     */
    User getBanUser(int userId) throws Exception;
}

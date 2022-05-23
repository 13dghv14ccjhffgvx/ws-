package com.txg.www.service.intf;

import com.txg.www.model.entity.Like;

import java.sql.SQLException;

public interface LikeService {
    /**
     * 点赞
     * @param userId  用户id
     * @param blogId 文章id
     * @return
     */
    public boolean addLike(int userId, int blogId) throws SQLException;

    /**
     * 取消点赞
     * @param userId 用户id
     * @param blogId 文章id
     * @return
     */
    public boolean cancelLike(int userId, int blogId) throws SQLException;

    /**
     * 判断用户是否已经点赞该文章
     * @param userId 用户id
     * @param blogId 文章id
     * @return
     * @throws Exception
     */
    public Like getLike(int userId, int blogId) throws Exception;

    /**
     * 用户获取自己的点赞数
     * @param userId 用户id
     * @return
     * @throws Exception
     */
    public int getLikeNum(int userId) throws Exception;
}

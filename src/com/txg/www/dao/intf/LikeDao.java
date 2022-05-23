package com.txg.www.dao.intf;

import com.txg.www.model.entity.Like;

import java.sql.SQLException;

public interface LikeDao {
    /**
     * 添加点赞
     *
     * @param userId 用户id
     * @param blogId 被点赞的文章id
     * @return
     */
     int insertLike(int userId, int blogId) throws SQLException;

    /**
     * 删除点赞
     * @param userId 用户id
     * @param blogId 文章id
     * @return
     */
     int deleteLike(int userId, int blogId) throws SQLException;

    /**
     * 查询是否点赞该文章
     * @param userId 用户id
     * @param blogId 文章id
     * @return
     * @throws Exception
     */
    Like selectLike(int userId, int blogId) throws Exception;

    /**
     * 查询用户的点赞数
     * @param userId 用户id
     * @return
     * @throws Exception
     */
   int slectLikeNum(int userId) throws Exception;

    /**
     * 通过文章id删除文章点赞
     * @param blogId
     * @return
     * @throws SQLException
     */
   int deleteLikeByBlogId(int blogId) throws SQLException;
}

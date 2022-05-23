package com.txg.www.dao.intf;

import com.txg.www.model.entity.Collect;

import java.sql.SQLException;
import java.util.List;

public interface CollectDao {
    /**
     * 添加收藏
     *
     * @param userId 用户id
     * @param blogId 文章id
     * @return
     */
    int insertCollect(int userId, int blogId) throws SQLException;

    /**
     * 删除收藏
     *
     * @param userId 用户id
     * @param blogId 文章id
     * @return
     */
    int deleteCollect(int userId, int blogId) throws SQLException;

    /**
     * 查找用户的收藏
     *
     * @param userId 用户id
     * @return 用户收藏的所有文章
     * @throws Exception
     */
    List<Collect> selectCollects(int userId) throws Exception;

    /**
     * 判断用户是否收藏
     *
     * @param userId 用户id
     * @param blogId 文章id
     * @return
     * @throws Exception
     */
    Collect selectCollect(int userId, int blogId) throws Exception;

    /**
     * 通过文章id删除文章
     * @param blogId
     * @return
     * @throws SQLException
     */
    int deleteCollectByBlogId(int blogId) throws SQLException;

}

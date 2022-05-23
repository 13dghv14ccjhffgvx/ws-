package com.txg.www.dao.intf;

import com.txg.www.model.entity.Draft;

import java.sql.SQLException;

public interface DraftDao {
    /**
     * 保存草稿
     *
     * @param title   文章标题
     * @param userId  用户id
     * @param content 文章内容
     * @param type    文章类型
     * @return
     */
    int save(String title, int userId, String content, String type) throws SQLException;

    /**
     * 查找草稿
     *
     * @param userId 用户id
     * @return
     * @throws Exception
     */
    Draft select(int userId) throws Exception;

    /**
     * 修改草稿
     *
     * @param title   标题
     * @param userId  用户id
     * @param content 内容
     * @param type    类型
     * @return
     */
    int update(String title, int userId, String content, String type) throws SQLException;

    /**
     * 删除草稿
     *
     * @param userId 用户id
     * @return
     */
    int delete(int userId) throws SQLException;
}

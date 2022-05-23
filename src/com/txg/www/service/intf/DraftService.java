package com.txg.www.service.intf;

import com.txg.www.model.entity.Draft;

import java.sql.SQLException;

public interface DraftService {
    /**
     * 添加草稿
     *
     * @param title   草稿的标题
     * @param content 草稿内容
     * @param type    草稿的文章类型
     * @param userId  用户id
     * @return
     */
    public boolean addDraft(String title, String content, String type, int userId) throws SQLException;

    /**
     * 得到草稿
     *
     * @param userId 用户id
     * @return 草稿
     * @throws Exception
     */
    public Draft getDraft(int userId) throws Exception;

    /**
     * 修改草稿
     *
     * @param title   草稿的标题
     * @param content 草稿的内容
     * @param type    草稿的文章类型
     * @param userId  用户id
     * @return
     */
    public boolean modify(String title, String content, String type, int userId) throws SQLException;

    /**
     * 删除草稿
     *
     * @param userId 用户id
     * @return
     */
    public boolean removeDraft(int userId) throws SQLException;
}

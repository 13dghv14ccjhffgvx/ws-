package com.txg.www.service.intf;

import com.txg.www.model.entity.Collect;

import java.sql.SQLException;
import java.util.List;

public interface CollectService {
    /**
     * 添加收藏
     *
     * @param userId 用户id
     * @param blogId 文章id
     * @return
     * @throws Exception
     */
    boolean addCollect(int userId, int blogId) throws Exception;

    /**
     * 删除收藏
     * @param userId 用户id
     * @param blogId 文章id
     * @return
     */
    boolean removeCollect(int userId, int blogId) throws SQLException;

    /**
     * 得到收藏
     * @param userId 用户id
     * @return 收藏的文章
     * @throws Exception
     */
    List<Collect> getcollects(int userId) throws Exception;
}

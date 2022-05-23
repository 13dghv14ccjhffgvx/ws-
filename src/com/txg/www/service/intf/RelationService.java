package com.txg.www.service.intf;

import com.txg.www.model.entity.Relation;

import java.sql.SQLException;
import java.util.List;

public interface RelationService {
    /**
     * 添加关注
     *
     * @param userId    用户id
     * @param concernId 被关注用户的id
     * @return
     */
    boolean addConcern(int userId, int concernId) throws SQLException;

    /**
     * 查询关注
     *
     * @param userId 用户id
     * @return 关注对象
     * @throws Exception
     */
    List<Relation> concernList(int userId) throws Exception;

    /**
     * 查询粉丝
     *
     * @param userId 用户id
     * @return 粉丝
     * @throws Exception
     */
    List<Relation> fanList(int userId) throws Exception;

    /**
     * 取消关注
     *
     * @param userId    用户id
     * @param concernId 被关注的用户id
     * @return
     */
    boolean removeConcern(int userId, int concernId) throws SQLException;

    /**
     * 查询用户是否关注该用户
     *
     * @param userId    用户id
     * @param concernId 被关注的用户id
     * @return
     * @throws Exception
     */
    Relation concern(int userId, int concernId) throws Exception;
}

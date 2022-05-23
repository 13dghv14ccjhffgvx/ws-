package com.txg.www.dao.intf;

import com.txg.www.model.entity.Relation;

import java.sql.SQLException;
import java.util.List;

public interface RelationDao {
    /**
     * 添加关注
     *
     * @param userId
     * @param concernId
     * @return
     */
    int insertRelation(int userId, int concernId) throws SQLException;

    /**
     * 查找所有关注的人
     *
     * @param userId
     * @return
     * @throws Exception
     */
    List<Relation> selectConcerns(int userId) throws Exception;

    /**
     * 查找粉丝
     *
     * @param userId
     * @return
     * @throws Exception
     */
    List<Relation> selectFans(int userId) throws Exception;

    /**
     * 删除关注
     *
     * @param userId
     * @param concernId
     * @return
     */
    int deleteRelation(int userId, int concernId) throws SQLException;

    /**
     * 查找用户是否关注某人
     *
     * @param userId
     * @param concernId
     * @return
     * @throws Exception
     */
    Relation selectConcern(int userId, int concernId) throws Exception;
}

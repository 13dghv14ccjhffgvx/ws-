package com.txg.www.dao.impl;

import com.txg.www.dao.BasicDao;
import com.txg.www.dao.intf.RelationDao;
import com.txg.www.model.entity.Relation;

import java.sql.SQLException;
import java.util.List;

public class RelationDaoImpl extends BasicDao implements RelationDao {

    @Override
    public int insertRelation(int userId, int concernId) throws SQLException {
        String sql="insert into tb_relation (relation_id,user_id,concern_id)values(null,?,?)";
        return super.executeUpdate(sql,userId,concernId);
    }

    @Override
    public List<Relation> selectConcerns(int userId) throws Exception {
        String sql="select relation_id relationId,user_id userId,concern_id concernId from tb_relation where user_id=?";
        return queryMulti(sql,Relation.class,userId);
    }

    @Override
    public List<Relation> selectFans(int userId) throws Exception {
        String sql="select relation_id relationId,user_id userId,concern_id concernId from tb_relation where concern_id=?";
        return queryMulti(sql,Relation.class,userId);
    }

    @Override
    public int deleteRelation(int userId, int concernId) throws SQLException {
        String sql="delete from tb_relation where user_id=? and concern_id=?";
        return super.executeUpdate(sql,userId,concernId);
    }

    @Override
    public Relation selectConcern(int userId, int concernId) throws Exception {
        String sql="select relation_id relationId,user_id userId,concern_id concernId from tb_relation where user_id=? and concern_id=?";
        return querySingle(sql,Relation.class,userId,concernId);
    }

}

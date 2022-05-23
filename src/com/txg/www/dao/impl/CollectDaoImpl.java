package com.txg.www.dao.impl;

import com.txg.www.dao.BasicDao;
import com.txg.www.dao.intf.CollectDao;
import com.txg.www.model.entity.Collect;

import java.sql.SQLException;
import java.util.List;

public class CollectDaoImpl extends BasicDao implements CollectDao {


    @Override
    public int insertCollect(int userId, int blogId) throws SQLException {
        String sql="insert into tb_collect(collect_id,user_id,blog_id) values(null,?,?)";
        return super.executeUpdate(sql,userId,blogId);
    }

    @Override
    public int deleteCollect(int userId, int blogId) throws SQLException {
        String sql="delete from tb_collect where user_id=? and blog_id=?";
        return super.executeUpdate(sql,userId,blogId);
    }

    @Override
    public List<Collect> selectCollects(int userId) throws Exception {
        String sql="select collect_id collectId,user_id userId,blog_id blogId from tb_collect where user_id=?";
        return super.queryMulti(sql,Collect.class,userId);
    }

    @Override
    public Collect selectCollect(int userId, int blogId) throws Exception {
        String sql="select collect_id collectId,user_id userId,blog_id blogId from tb_collect where user_id=? and blog_id=?";
        return super.querySingle(sql,Collect.class,userId,blogId);
    }

    @Override
    public int deleteCollectByBlogId(int blogId) throws SQLException {
        String sql="delete from tb_collect where blog_id=?";
        return super.executeUpdate(sql,blogId);
    }
}

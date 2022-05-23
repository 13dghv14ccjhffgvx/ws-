package com.txg.www.dao.impl;

import com.txg.www.dao.BasicDao;
import com.txg.www.dao.intf.DraftDao;
import com.txg.www.model.entity.Draft;

import java.sql.SQLException;

public class DraftDaoImpl extends BasicDao implements DraftDao {
    @Override
    public int save(String title,int userId,String content,String type) throws SQLException {
        String sql="insert into tb_draft(draft_id,user_id,title,content,type) values(null,?,?,?,?)";
        return super.executeUpdate(sql ,userId,title,content,type);
    }

    @Override
    public Draft select(int userId) throws Exception {
        String sql="select draft_id draftId,user_id userId,title,content,type from tb_draft where user_id=?";
        return super.querySingle(sql, Draft.class,userId);
    }

    @Override
    public int update(String title, int userId, String content, String type) throws SQLException {
        String sql="update tb_draft set title=?,content=?,type=? where user_id=?";
        return super.executeUpdate(sql,title,content,type,userId);
    }

    @Override
    public int delete(int userId) throws SQLException {
        String sql="delete from tb_draft where user_id=?";
            return super.executeUpdate(sql,userId);
    }

}

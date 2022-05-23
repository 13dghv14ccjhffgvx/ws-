package com.txg.www.dao.impl;

import com.txg.www.dao.BasicDao;
import com.txg.www.dao.intf.AtDao;
import com.txg.www.model.entity.At;
import com.txg.www.until.GetTime;

import java.sql.SQLException;
import java.util.List;

public class AtDaoImpl extends BasicDao implements AtDao {
    @Override
    public List<At> selectAtByReceiverId(int receiverId) throws Exception {
        String sql="select at_id atId,sender_id senderId,receiver_id receiverId,message,status,at_time atTime from tb_at where receiver_id=?";
        return super.queryMulti(sql,At.class,receiverId);
    }

    @Override
    public int updateStatus(int receiverId) throws SQLException {
        String sql="update tb_at set status=1 where receiver_id=?";
        return super.executeUpdate(sql,receiverId);
    }

    @Override
    public List<At> selectUnreadAtByReceiverId(int receiverId) throws Exception {
        String sql="select at_id atId,sender_id senderId,receiver_id receiverId,message,status,at_time atTime from tb_at where receiver_id=? and status=0";
        return super.queryMulti(sql,At.class,receiverId);
    }

    @Override
    public int insertAt(int senderId, int receiverId, String message) throws SQLException {
        String sql = "insert into tb_at(at_id,sender_id,receiver_id,message,status,at_time)values(null,?,?,?,0,?)";
        return super.executeUpdate(sql, senderId, receiverId, message, GetTime.getTime());
    }
}

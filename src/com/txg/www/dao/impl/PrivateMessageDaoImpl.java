package com.txg.www.dao.impl;

import com.txg.www.dao.BasicDao;
import com.txg.www.dao.intf.PrivateMessageDao;
import com.txg.www.model.entity.PrivateMessage;
import com.txg.www.until.GetTime;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class PrivateMessageDaoImpl extends BasicDao implements PrivateMessageDao {
    @Override
    public int insertMessage(int senderId, int receiverId, String message, String imgPath, String belong) throws SQLException {
        String sql = "insert into tb_private_message(private_message_id,sender_id,receiver_id,message,img_path,belong,message_time,status)values(null,?,?,?,?,?,?,0)";
        return super.executeUpdate(sql, senderId, receiverId, message, imgPath, belong, GetTime.getTime());
    }

    @Override
    public List<PrivateMessage> selectMessage(String blong) throws Exception {
        String sql = "select private_message_id privateMessageId,sender_id senderId,receiver_id receiverId,message,img_path imgPath,belong,message_time messageTime from tb_private_message where belong=?";
        return super.queryMulti(sql, PrivateMessage.class, blong);
    }

    @Override
    public int deleteMessage(int userId, int messageId) throws SQLException {
        String sql="delete from tb_private_message where sender_id=? and private_message_id=? and DATE_ADD(now(), Interval -2 MINUTE)<=message_time";
        return super.executeUpdate(sql,userId,messageId);
    }

    @Override
    public int updateStatus(int senderId,int receiverId) throws SQLException {
       String sql="update tb_private_message set status=1 where sender_id=? and receiver_id=?";
        return super.executeUpdate(sql,senderId,receiverId);
    }

    @Override
    public List<PrivateMessage> selectUnreadMessageByReceiverId(int receiverId) throws Exception {
        String sql="select private_message_id privateMessageId,sender_id senderId,receiver_id receiverId,message,img_path imgPath,belong,message_time messageTime,status from tb_private_message where status=0 and receiver_id=?";
        return super.queryMulti(sql,PrivateMessage.class,receiverId);
    }


}

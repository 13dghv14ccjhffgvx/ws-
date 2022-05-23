package com.txg.www.dao.intf;

import com.txg.www.model.entity.PrivateMessage;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface PrivateMessageDao {
    int insertMessage(int senderId,int receiverId,String message,String imgPath,String blong) throws SQLException;

    List<PrivateMessage> selectMessage(String blong) throws Exception;

    int deleteMessage(int userId,int messageId) throws SQLException;

    int updateStatus(int senderId,int receiverId) throws SQLException;

    List<PrivateMessage> selectUnreadMessageByReceiverId(int receiverId) throws Exception;
}

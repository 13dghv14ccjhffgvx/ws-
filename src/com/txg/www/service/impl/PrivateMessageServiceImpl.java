package com.txg.www.service.impl;

import com.txg.www.dao.impl.PrivateMessageDaoImpl;
import com.txg.www.dao.intf.PrivateMessageDao;
import com.txg.www.model.entity.PrivateMessage;
import com.txg.www.service.intf.PrivateMessageService;

import java.sql.SQLException;
import java.util.List;

public class PrivateMessageServiceImpl implements PrivateMessageService {
    PrivateMessageDao privateMessageDao = new PrivateMessageDaoImpl();

    @Override
    public boolean addMessage(int senderId, int receiverId, String message, String imgPath, String belong) throws SQLException {
        return 1 == privateMessageDao.insertMessage(senderId, receiverId, message, imgPath, belong);
    }

    @Override
    public List<PrivateMessage> getMessage(String belong) throws Exception {
        return privateMessageDao.selectMessage(belong);
    }

    @Override
    public boolean deleteMessage(int userId, int messageId) throws SQLException {
        return 1== privateMessageDao.deleteMessage(userId, messageId);
    }

    @Override
    public boolean modifyStatus(int senderId, int receiverId) throws SQLException {
        return 1==privateMessageDao.updateStatus(senderId, receiverId);
    }

    @Override
    public List<PrivateMessage> getUnreadMessage(int receiverId) throws Exception {
        return privateMessageDao.selectUnreadMessageByReceiverId(receiverId);
    }
}

package com.txg.www.service.impl;

import com.txg.www.dao.impl.AtDaoImpl;
import com.txg.www.dao.intf.AtDao;
import com.txg.www.model.entity.At;
import com.txg.www.service.intf.AtService;

import java.sql.SQLException;
import java.util.List;

public class AtServiceImpl implements AtService {
    AtDao atDao=new AtDaoImpl();

    @Override
    public List<At> getAt(int receiverId) throws Exception {
        return atDao.selectAtByReceiverId(receiverId);
    }

    @Override
    public boolean modifyStatus(int receiverId) throws SQLException {
        return 1==atDao.updateStatus(receiverId);
    }

    @Override
    public List<At> getUnreadAt(int receiverId) throws Exception {
        return atDao.selectUnreadAtByReceiverId(receiverId);
    }

    @Override
    public boolean addAt(int senderId, int receiverId, String message) throws SQLException {
        return 1==atDao.insertAt(senderId, receiverId, message);
    }
}

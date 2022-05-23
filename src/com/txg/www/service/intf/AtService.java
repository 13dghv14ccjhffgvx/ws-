package com.txg.www.service.intf;

import com.txg.www.model.entity.At;

import java.sql.SQLException;
import java.util.List;

public interface AtService {
    /**
     * 发送at
     * @param senderId 发送者的id
     * @param receiverId 接收者的id
     * @param message 发送内容
     * @return
     */
    boolean addAt(int senderId, int receiverId, String message) throws SQLException;

    /**
     * 通过接收者的id获取at
     * @param receiverId 接收者id
     * @return
     * @throws Exception
     */
    List<At> getAt(int receiverId) throws Exception;

    /**
     * 修改at的状态,变为已读
     * @param receiverId 接收者id
     * @return
     */
    boolean modifyStatus(int receiverId) throws SQLException;

    /**
     * 获取未读的at
     * @param receiverId 接收者id
     * @return
     * @throws Exception
     */
    List<At> getUnreadAt(int receiverId) throws Exception;
}

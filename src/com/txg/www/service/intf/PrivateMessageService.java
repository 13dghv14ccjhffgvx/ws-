package com.txg.www.service.intf;

import com.txg.www.model.entity.PrivateMessage;

import java.sql.SQLException;
import java.util.List;

public interface PrivateMessageService {
    /**
     * 添加消息
     *
     * @param senderId   发送者id
     * @param receiverId 接收者id
     * @param message    消息内容
     * @param imgPath    图片路径
     * @param belong     消息属于哪两个用户
     * @return
     * @throws SQLException
     */
    boolean addMessage(int senderId, int receiverId, String message, String imgPath, String belong) throws SQLException;

    /**
     * 获取消息
     *
     * @param belong
     * @return
     * @throws Exception
     */
    List<PrivateMessage> getMessage(String belong) throws Exception;

    /**
     * 删除消息
     *
     * @param userId
     * @param messageId
     * @return
     * @throws SQLException
     */
    boolean deleteMessage(int userId, int messageId) throws SQLException;

    /**
     * 修改消息的状态
     *
     * @param senderId   发送者id
     * @param receiverId 接收者id
     * @return
     * @throws SQLException
     */
    boolean modifyStatus(int senderId, int receiverId) throws SQLException;

    /**
     * 获取未读消息
     *
     * @param receiverId 接收者id
     * @return
     * @throws Exception
     */
    List<PrivateMessage> getUnreadMessage(int receiverId) throws Exception;
}

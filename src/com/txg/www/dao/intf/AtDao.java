package com.txg.www.dao.intf;

import com.txg.www.model.entity.At;

import java.sql.SQLException;
import java.util.List;

public interface AtDao {
    /**
     * 添加at
     * @param senderId
     * @param receiverId
     * @param message
     * @return
     */
    int insertAt(int senderId, int receiverId, String message) throws SQLException;

    /**
     * 通过接收者id查询at
     * @param receiverId 接收者的id
     * @return
     * @throws Exception
     */
    List<At> selectAtByReceiverId(int receiverId) throws Exception;

    /**
     * 更改at的状态
     * @param receiverId 接收者id
     * @return
     */
    int updateStatus(int receiverId) throws SQLException;

    /**
     * 查询未读的at
     * @param receiverId 接收者id
     * @return
     * @throws Exception
     */
    List<At> selectUnreadAtByReceiverId(int receiverId) throws Exception;
}

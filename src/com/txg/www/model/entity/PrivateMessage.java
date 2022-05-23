package com.txg.www.model.entity;

import java.util.Date;

public class PrivateMessage {
    int privateMessageId;
    //发送者id
    int senderId;
    //接收者id
    int receiverId;
    //消息
    String message;
    //图片
    String imgPath;
    //私信属于哪两个用户的判断字段
    String belong;
    //消息时间
    Date messageTime;
    //消息状态
    int status;
    public PrivateMessage() {
    }

    public PrivateMessage(int privateMessageId, int senderId, int receiverId, String message, String imgPath, String belong, Date messageTime, int status) {
        this.privateMessageId = privateMessageId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.imgPath = imgPath;
        this.belong = belong;
        this.messageTime = messageTime;
        this.status = status;
    }

    public int getPrivateMessageId() {
        return privateMessageId;
    }

    public void setPrivateMessageId(int privateMessageId) {
        this.privateMessageId = privateMessageId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }


    public Date getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Date messageTime) {
        this.messageTime = messageTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

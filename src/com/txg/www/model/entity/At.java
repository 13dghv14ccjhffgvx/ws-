package com.txg.www.model.entity;

import java.util.Date;


public class At {
    private int atId;
    //发送＠的用户的id
    private int senderId;
    //接受＠的用户id
    private int receiverId;
    //消息内容
    private String message;
    //消息的状态
    private int status;
    //发送时间
    private Date atTime;

    public At() {
    }

    public At(int atId, int senderId, int receiverId, String message, int status, Date atTime) {
        this.atId = atId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.status = status;
        this.atTime = atTime;
    }

    public int getAtId() {
        return atId;
    }

    public void setAtId(int atId) {
        this.atId = atId;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getAtTime() {
        return atTime;
    }

    public void setAtTime(Date atTime) {
        this.atTime = atTime;
    }
}

package com.txg.www.model.entity;

import java.util.Date;

public class User {
    private int userId;
    //用户角色id
    private int roleId;
    //用户名
    private String userName;
    //用户密码
    private String userPwd;
    //昵称
    private String nickName;
    //性别
    private String userSex;
    //个人简介
    private String userIntroduction;
    //头像
    private String headImg;
    //创建时间
    Date createTime;

    public User() {
    }

    public User(int userId, int roleId, String userName, String userPwd, String nickName, String userSex, String userIntroduction, String headImg, Date createTime) {
        this.userId = userId;
        this.roleId = roleId;
        this.userName = userName;
        this.userPwd = userPwd;
        this.nickName = nickName;
        this.userSex = userSex;
        this.userIntroduction = userIntroduction;
        this.headImg = headImg;
        this.createTime = createTime;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserIntroduction() {
        return userIntroduction;
    }

    public void setUserIntroduction(String userIntroduction) {
        this.userIntroduction = userIntroduction;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getHearImg() {
        return headImg;
    }

    public void setHearImg(String hearImg) {
        this.headImg = hearImg;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", nickName='" + nickName + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userIntroduction='" + userIntroduction + '\'' +
                ", headImg='" + headImg + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}

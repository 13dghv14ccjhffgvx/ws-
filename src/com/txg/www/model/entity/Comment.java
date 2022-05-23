package com.txg.www.model.entity;

import java.util.Date;
import java.util.List;

public class Comment {
    private int commentId;
    //用户id
    private int userId;
    //评论内容
    private String content;
    //文章id
    private int blogId;
    //评论时间
    private Date commentTime;
    //父级评论id
    private int parentCommentId;
    //评论者的昵称
    private String userNickName;
    //子评论
    private List<Comment> child;


    public Comment() {
    }

    public Comment(int commentId, int userId, String content, int blogId, Date commentTime, int parentCommentId, String userNickName, List<Comment> child) {
        this.commentId = commentId;
        this.userId = userId;
        this.content = content;
        this.blogId = blogId;
        this.commentTime = commentTime;
        this.parentCommentId = parentCommentId;
        this.userNickName = userNickName;
        this.child = child;
    }

    public List<Comment> getChild() {
        return child;
    }

    public void setChild(List<Comment> child) {
        this.child = child;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public int getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(int parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }
}

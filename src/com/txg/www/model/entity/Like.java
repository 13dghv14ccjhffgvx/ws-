package com.txg.www.model.entity;

public class Like {
    private int likeId;
    //用户id
    private int userId;
    //文章id
    private int blogId;

    public Like() {
    }

    public Like(int likeId, int userId, int blogId) {
        this.likeId = likeId;
        this.userId = userId;
        this.blogId = blogId;
    }

    public int getLikeId() {
        return likeId;
    }

    public void setLikeId(int likeId) {
        this.likeId = likeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }
}

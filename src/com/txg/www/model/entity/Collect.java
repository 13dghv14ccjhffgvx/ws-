package com.txg.www.model.entity;

public class Collect {
    private int collectId;
    //用户id
    private int userId;
    //文章id
    private int blogId;

    public Collect() {
    }

    public Collect(int collectId, int userId, int blogId) {
        this.collectId = collectId;
        this.userId = userId;
        this.blogId = blogId;
    }

    public int getCollectId() {
        return collectId;
    }

    public void setCollectId(int collectId) {
        this.collectId = collectId;
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

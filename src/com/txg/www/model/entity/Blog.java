package com.txg.www.model.entity;

import java.util.Date;

public class Blog {
    private int blogId;
    //标题
    private String title;
    //文章类型
    private String type;
    //内容
    private String content;
    //图片
    private String imgPath;
    //点赞数
    private int likeNum;
    //收藏数
    private int collectNum;
    //用户id
    private int userId;
    //发布时间
    private Date releaseTime;

    public Blog() {
    }


    public Blog(int blogId, String title, String type, String content, String imgPath, int likeNum, int collectNum, int userId, Date releaseTime) {
        this.blogId = blogId;
        this.title = title;
        this.type = type;
        this.content = content;
        this.imgPath = imgPath;
        this.likeNum = likeNum;
        this.collectNum = collectNum;
        this.userId = userId;
        this.releaseTime = releaseTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public int getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(int collectNum) {
        this.collectNum = collectNum;
    }
}

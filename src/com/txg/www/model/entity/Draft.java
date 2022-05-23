package com.txg.www.model.entity;

public class Draft {
    private int draftId;
    //用户id
    private int userId;
    //文章内容
    private String content;
    //标题
    private String title;
    //文章类型
    private String type;

    public Draft() {
    }

    public Draft(int draftId, int userId, String content, String title, String type) {
        this.draftId = draftId;
        this.userId = userId;
        this.content = content;
        this.title = title;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDraftId() {
        return draftId;
    }

    public void setDraftId(int draftId) {
        this.draftId = draftId;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

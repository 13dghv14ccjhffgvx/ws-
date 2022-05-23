package com.txg.www.model.entity;

public class Relation {
    private int relationId;
    //用户id
    private int userId;
    //关注的用户id
    private int concernId;

    public Relation() {
    }

    public Relation(int relationId, int userId, int concernId) {
        this.relationId = relationId;
        this.userId = userId;
        this.concernId = concernId;
    }

    public int getRelationId() {
        return relationId;
    }

    public void setRelationId(int relationId) {
        this.relationId = relationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getConcernId() {
        return concernId;
    }

    public void setConcernId(int concernId) {
        this.concernId = concernId;
    }
}

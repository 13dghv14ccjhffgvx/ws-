package com.huangjunqiang.www.po;

/**
 * Create with IntelliJ IDEA
 *
 * @author: hjq
 * @Date:
 * @Description: 帖子的实体类
 */
public class Post {
    int id;
    String barName;
    String author;
    String time;
    public Post(){
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBarName() {
        return barName;
    }

    public void setBarName(String barName) {
        this.barName = barName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Post(int id, String barName, String author, String time) {
        this.id = id;
        this.barName = barName;
        this.author = author;
        this.time = time;
    }
}

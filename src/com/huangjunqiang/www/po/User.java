package com.huangjunqiang.www.po;

/**
 * Create with IntelliJ IDEA
 *
 * @author: hjq
 * @Date:
 * @Description:
 */
public class User {
    int id;
    String username;
    String password;
    String nickname;
    String birthday;

    public User() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public User(String username, String password, String nickname, String birthday) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.birthday = birthday;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

package com.huangjunqiang.www.Dao.Impl;

import com.huangjunqiang.www.po.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Create with IntelliJ IDEA
 *
 * @author: hjq
 * @Date:
 * @Description:
 */
public class UserLoginDao {
    /**
     * 登录验证
     */
    public User login(Connection conn, User user){
        User  resultuser = null;
        PreparedStatement stmt = null;
        ResultSet rs =null;
        String sql = "select*from user where username=? and password=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            rs = stmt.executeQuery();
            if (rs.next()) {
                resultuser = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
            return resultuser;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return resultuser;
    }
}

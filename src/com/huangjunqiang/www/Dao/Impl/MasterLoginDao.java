package com.huangjunqiang.www.Dao.Impl;

import com.huangjunqiang.www.Util.JdbcUtils;
import com.huangjunqiang.www.po.Master;

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
public class MasterLoginDao {
    public Master login(Connection conn, Master master) {
        Master  result = null;
        PreparedStatement stmt = null;
        ResultSet rs =null;
        String sql = "select*from master where username=? and password=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, master.getUsername());
            stmt.setString(2, master.getPassword());
            rs = stmt.executeQuery();
            if (rs.next()) {
                result = new Master();
                master.setId(rs.getInt("id"));
                master.setUsername(rs.getString("username"));
                master.setPassword(rs.getString("password"));
            }
            return result;
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            JdbcUtils.close(conn,stmt,rs);
        }
        return result;
    }
}

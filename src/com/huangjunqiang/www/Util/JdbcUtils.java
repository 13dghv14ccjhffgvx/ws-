package com.huangjunqiang.www.Util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * Create with IntelliJ IDEA
 *
 * @author: hjq
 * @Date:
 * @Description: JDBC操作的工具类
 */
public class JdbcUtils {
    private static String driverClass;
    private static String url;
    private static String username;
    private static String password;
    private static Connection conn;
    static {
        try {
            InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("config.properties");
            Properties prop=new Properties();
            prop.load(in);

            driverClass=prop.getProperty("driverClass");
            url=prop.getProperty("url");
            username=prop.getProperty("username");
            password=prop.getProperty("password");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    //获取连接
    public static Connection getConnection(){
        try{
            conn= DriverManager.getConnection(url,username,password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }
    //关闭资源
    public static void close(Connection conn, Statement stat, ResultSet rs) {
        try {
            if (conn != null) {
                conn.close();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (stat != null) {
                stat.close();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (rs != null) {
                rs.close();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void close(Connection conn,Statement stat){
        try {
            if (conn != null) {
                conn.close();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (stat != null) {
                stat.close();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}

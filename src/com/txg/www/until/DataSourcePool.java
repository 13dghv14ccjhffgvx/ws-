package com.txg.www.until;

import com.sun.corba.se.spi.protocol.ClientDelegateFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.Properties;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DataSourcePool {
    private static LinkedList<Connection> pool = new LinkedList<>();
    private static ThreadLocal<Connection> conns = new ThreadLocal<>();


    static String url;
    static String password;
    static String driver;
    static String user;
    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    //连接池初始数量
    private static final int INIT_CONNECTIONS = 30;
    //连接池最大数量
    private static final int MAX_CONNECTIONS = 50;
    //连接池当前数量
    private static int CURRENT_CONNECTIONS = 0;

    static {
        // 注册驱动
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("C:\\Users\\22577\\IdeaProjects\\CSDN\\src\\mysql.properties"));
            url = properties.getProperty("url");
            password = properties.getProperty("password");
            driver = properties.getProperty("driver");
            user = properties.getProperty("user");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
    // 通过构造方法初始化连接

    private DataSourcePool() throws ClassNotFoundException {
        Class.forName(driver);
        for (int i = 0; i < INIT_CONNECTIONS; i++) {
            try {
                Connection conn = DriverManager.getConnection(url, user, password);
                pool.addLast(conn);
                CURRENT_CONNECTIONS++;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    static DataSourcePool dataSourcePool;

    static {
        try {
            dataSourcePool = new DataSourcePool();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static DataSourcePool getInstance() {
        return dataSourcePool;
    }

    public Connection getConnection() throws SQLException {
        lock.lock();
        try {
            //如果当前连接池里空闲的连接的连接数量大于连接池初始数量
            for (; pool.size() > INIT_CONNECTIONS; ) {
                //获取连接池的最后一个连接
                Connection conn = pool.removeLast();
                //关闭连接
                conn.close();
                //连接池的连接数量-1
                CURRENT_CONNECTIONS--;
            }
            Connection conn = conns.get();
            if (conn == null) {
                //连接池里的空闲连接为0
                if (pool.size() == 0) {
                    //连接池的连接数量小于最大连接数，扩容
                    if (CURRENT_CONNECTIONS < MAX_CONNECTIONS) {
                        conn = DriverManager.getConnection(url, user, password);
                        conns.set(conn);
                        conn.setAutoCommit(false);
                        //当前连接池的数量+1
                        CURRENT_CONNECTIONS++;
                    } else {
                        try {
                            //连接池已经达到最大数量，等待
                            condition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    try {
                        conn = pool.removeFirst();
                        conns.set(conn);
                        conn.setAutoCommit(false);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
            return conn;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 提交事务
     */
    public static void commitAndClose() {
        Connection conn = conns.get();
        lock.lock();
        try {
            if (conn != null) {
                try {
                    //提交事务
                    conn.commit();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } finally {
                    pool.addLast(conn);
                    condition.signal();
                }
            }
        } finally {
            lock.unlock();
        }
        conns.remove();
    }

    /**
     * 回滚事务
     */
    public static void rollbackAndClose() {
        Connection conn = conns.get();
        lock.lock();
        try {
            if (conn != null) {
                try {
                    //事务回滚
                    conn.rollback();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } finally {
                    pool.addLast(conn);
                    condition.signal();
                }
            }
        } finally {
            lock.unlock();
        }
        conns.remove();
    }
}



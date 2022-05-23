package com.txg.www.dao;


import com.txg.www.until.DataSourcePool;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BasicDao {
    //创建一个连接池
    DataSourcePool pool = DataSourcePool.getInstance();

    /**
     * 功能:将传入的参数赋给SQL语句的占位符
     *
     * @param ps
     * @param values
     */
    public void setParam(PreparedStatement ps, Object... values) {
        if (values != null && values.length > 0) {
            for (int i = 0; i < values.length; i++) {
                try {
                    ps.setObject(i + 1, values[i]);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }

        }
    }

    /**
     * 功能:返回SQL语句查询的结果集
     *
     * @param ps
     * @param values
     * @return
     */
    public ResultSet executeQuery(PreparedStatement ps, Object... values) {
        ResultSet resultSet;
        try {
            setParam(ps, values);
            resultSet = ps.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;

    }

    /**
     * 功能:执行数据库修改的操作
     *
     * @param sql
     * @param values
     * @return
     */
    public int executeUpdate(String sql, Object... values) throws SQLException {
        Connection conn = pool.getConnection();
        int count;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            new BasicDao().setParam(ps, values);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            count = 0;
        }
            ps.close();
        return count;
    }

    /**
     * 功能:用于查询多条数据,将resultset封装到集合中,传回一个list对象
     *
     * @param sql
     * @param values
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> List<T> queryMulti(String sql, Class<T> clazz, Object... values) throws Exception {
        Connection conn = pool.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        BasicDao basicDao = new BasicDao();
        //定义一个集合来存放需要转成的对象集合
        List<T> list = new ArrayList<T>();
        //遍历结果集，封装成外界用户所需要的对象集合
        //1>获取结果集
        ResultSet rs = basicDao.executeQuery(ps, values);
        //2>开始遍历
        while (rs.next()) {
            // 创建一个clazz对象实例并将其赋给要返回的那个返回值。
            T obj = (T) clazz.newInstance();
            // 获取结果集的数据源
            ResultSetMetaData rsmeta = rs.getMetaData();

            // 获取结果集中的字段数
            int count = rsmeta.getColumnCount();

            // 循环取出个字段的名字以及他们的值并将其作为值赋给对应的实体对象的属性
            for (int i = 0; i < count; i++) {
                // 获取字段名
                String name = rsmeta.getColumnLabel(i + 1);
                // 利用反射将结果集中的字段名与实体对象中的属性名相对应，由于
                // 对象的属性都是私有的所以要想访问必须加上getDeclaredField(name)和
                Field f = obj.getClass().getDeclaredField(name);
                f.setAccessible(true);
                // 将结果集中的值赋给相应的对象实体的属性
                f.set(obj, rs.getObject(name));
            }
            //封装到集合
            list.add(obj);
        }
        rs.close();
        ps.close();
        return list;
    }

    /**
     * 功能:用于查询单条数据,将resultset封装到类对象中,传回一个类对象
     *
     * @param sql
     * @param values
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T querySingle(String sql, Class<T> clazz, Object... values) throws Exception {
        Connection conn = pool.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        BasicDao basicDao = new BasicDao();
        T obj = null;
        //获取结果集
        ResultSet rs = basicDao.executeQuery(ps, values);
        while (rs.next()) {
            obj = (T) clazz.newInstance();
            // 创建一个clazz对象实例并将其赋给要返回的那个返回值。
            // 获取结果集的数据源
            ResultSetMetaData rsmeta = rs.getMetaData();
            // 获取结果集中的字段数
            int count = rsmeta.getColumnCount();
            // 循环取出个字段的名字以及他们的值并将其作为值赋给对应的实体对象的属性
            for (int i = 0; i < count; i++) {
                // 获取字段名
                String name = rsmeta.getColumnLabel(i + 1);
                // 利用反射将结果集中的字段名与实体对象中的属性名相对应，由于
                // 对象的属性都是私有的所以要想访问必须加上getDeclaredField(name)和
                Field f = obj.getClass().getDeclaredField(name);
                f.setAccessible(true);
                // 将结果集中的值赋给相应的对象实体的属性
                f.set(obj, rs.getObject(name));
            }
            //封装到集合
        }
        rs.close();
        ps.close();
        return obj;
    }

    public Object queryScalar(String sql, Object... parameters) throws Exception {
        Connection conn = pool.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet resultSet = null;
        try {
            setParam(ps, parameters);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return resultSet.getObject(1);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            resultSet.close();
            ps.close();
        }
    }
}

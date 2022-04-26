package com.huangjunqiang.www.Dao.Impl;

import com.huangjunqiang.www.Dao.BarDao;
import com.huangjunqiang.www.Util.JdbcUtils;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Create with IntelliJ IDEA
 *接口的实现类
 * @author: hjq
 * @Date:
 * @Description:
 */
public class BarDaoImpl extends JFrame implements BarDao {
    Connection conn=null;
    PreparedStatement ps=null;
    @Override
    public void insertBar(JTextField id,JTextField barName,JTextField desc,JTextField time,JTextField sid) {
        try {
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);
            String sql = "insert into postbar(id,barName,desc,createTime,sid) value(?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id.getText());
            ps.setString(2, barName.getText());
            ps.setString(3,desc.getText());
            ps.setString(4,time.getText());
            ps.setString(5,sid.getText());
            ps.executeUpdate();
            conn.commit();
            JOptionPane.showMessageDialog(null,"创建成功");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"创建失败");
            e.printStackTrace();
        }finally{
            JdbcUtils.close(conn,ps);
        }
    }

    @Override
    public void deleteBar(JTextField idField) {
        String sno = idField.getText();
        try {
            conn = JdbcUtils.getConnection();
            String sql = "delete from postbar where id =?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,sno);
            ps.execute();
            JOptionPane.showMessageDialog(null, "删除成功");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"删除失败");
            e.printStackTrace();
        } finally {
            JdbcUtils.close(conn,ps);
        }
    }

    @Override
    public void resetBar(JTextField id,JTextField barName,JTextField desc,JTextField time,JTextField sid) {
        id.setText("");
        barName.setText("");
        desc.setText("");
        time.setText("");
        sid.setText("");
    }
}

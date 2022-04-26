package com.huangjunqiang.www.Dao.Impl;

import com.huangjunqiang.www.Dao.PostDao;
import com.huangjunqiang.www.Util.JdbcUtils;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Create with IntelliJ IDEA
 *
 * @author: hjq
 * @Date:
 * @Description:
 */
public class PostDaoImpl extends JFrame implements PostDao {
    PreparedStatement ps = null;
    Connection conn = null;
    @Override
    public void addPost(JTextField idField,JTextField nameField,JTextField titleField, JTextField contentField,JTextField authorField,JTextField timeField) {
        try {
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);
            String sql = "insert into posts(id,barName,title,content,author,createTime) value(?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, idField.getText());
            ps.setString(2, nameField.getText());
            ps.setString(3, titleField.getText());
            ps.setString(4, contentField.getText());
            ps.setString(5,authorField.getText());
            ps.setString(6,timeField.getText());
            ps.executeUpdate();
            conn.commit();
            JOptionPane.showMessageDialog(null,"发布成功");
    }catch(Exception e){
            JOptionPane.showMessageDialog(null,"发布失败");
            try{conn.rollback();}
            catch(Exception e2){
                e2.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            JdbcUtils.close(conn,ps);
        }
}

    @Override
    public void deletePost(JTextField idField) {
        String sno = idField.getText();
        try {
            conn = JdbcUtils.getConnection();
            String sql = "delete from posts where id =?";
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
    public void resetPost(JTextField idField,JTextField nameField,JTextField titleField, JTextField contentField,JTextField authorField,JTextField timeField) {
        idField.setText("");
        nameField.setText("");
        titleField.setText("");
        contentField.setText("");
        authorField.setText("");
        timeField.setText("");
    }

}

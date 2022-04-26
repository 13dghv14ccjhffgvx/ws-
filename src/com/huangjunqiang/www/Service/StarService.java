package com.huangjunqiang.www.Service;

import com.huangjunqiang.www.Util.JdbcUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Create with IntelliJ IDEA
 *
 * @author: hjq
 * @Date:
 * @Description:
 */
public class StarService extends JFrame {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    JLabel postNameLabel = new JLabel("帖子名称");
    JTextField postNameField = new JTextField(15);
    JButton star = new JButton("收藏");
    JButton cancel = new JButton("取消收藏");

    //以表格形式展现收藏的帖子
    public StarService(){
        Container container = getContentPane();
        setBounds(520, 330, 400, 180);
        //添加一块桌布
        container.setLayout(new BorderLayout());
        JPanel root = new JPanel();
        root.setLayout(null);
        Font f = new Font("宋体", Font.PLAIN, 25);
        postNameLabel.setFont(f);
        postNameLabel.setBounds(40, 1, 130, 60);
        postNameField.setBounds(150, 17, 150, 30);
        star.setBounds(90, 80, 100, 50);
        cancel.setBounds(240, 80, 100, 50);
        star.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                star(postNameField);
                dispose();
            }
        });
        cancel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelStar(postNameField);
                dispose();
            }
        });
        root.add(postNameLabel);
        root.add(postNameField);
        root.add(star);
        root.add(cancel);
        container.add(root);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    //设置收藏帖子操作
    public void star(JTextField nameField){
        try {
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);
            String sql = "insert into star(name) value(?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, nameField.getText());
            ps.executeUpdate();
            conn.commit();
            JOptionPane.showMessageDialog(null,"收藏成功");

        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"收藏失败");
            e.printStackTrace();
        }finally{
            JdbcUtils.close(conn,ps);
        }
    }
    //添加取消收藏操作
    public void cancelStar(JTextField nameField){
        String username = nameField.getText();
        try {
            conn = JdbcUtils.getConnection();
            String sql = "delete from star where name =?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.execute();
            JOptionPane.showMessageDialog(null, "取消收藏成功");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"取消收藏失败");
            e.printStackTrace();
        } finally {
            JdbcUtils.close(conn,ps);
        }
    }
}

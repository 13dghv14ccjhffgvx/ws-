package com.huangjunqiang.www.Service;

import com.huangjunqiang.www.Util.JdbcUtils;
import com.huangjunqiang.www.View.UserView.UserMainView;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Create with IntelliJ IDEA
 *
 * @author: hjq
 * @Date:
 * @Description:
 */
public class SearchStarService extends JFrame {
    private Connection conn;
    private PreparedStatement ps;
    JButton fresh = new JButton("刷新");
    JButton back=new JButton("返回");

    /**
     * 展示收藏的帖子
     */
    public SearchStarService(){
        JFrame frame = new JFrame();
        DefaultTableModel dtm = new DefaultTableModel();
        DefaultTableCellRenderer dtr = new DefaultTableCellRenderer();
        dtr.setHorizontalAlignment(JLabel.CENTER);
        JTable table = new JTable(stuTableModel(dtm));
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.setDefaultRenderer(Object.class, dtr);
        JScrollPane jsp = new JScrollPane();
        jsp.setViewportView(table);
        frame.add(jsp);
        frame.setBounds(90, 10, 1300, 250);
        JPanel panel = new JPanel();
        frame.add(panel, BorderLayout.NORTH);
        JPanel root = new JPanel();
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserMainView();
            }
        });
        fresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SearchStarService();
            }
        });
        root.add(back);
        root.add(fresh);
        frame.setTitle("收藏帖子总览");
        frame.add(root, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
    /**
     * 使用DefaultTableModel导入表中数据
     * @param dtm
     * @return
     */
    private DefaultTableModel stuTableModel(DefaultTableModel dtm) {
        PreparedStatement st = null;
        ResultSet rs = null;
        String[] stu = {"序号","帖子标题"};
        dtm.setColumnIdentifiers(stu);
        Vector v = null;
        String sql="select *from star";
        try {
            conn = JdbcUtils.getConnection();
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                v = new Vector();
                int sno = rs.getInt("id");
                String name = rs.getString("name");
                v.add(sno);
                v.add(name);
                dtm.addRow(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try{
                if(conn!=null){
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return dtm;
    }
}

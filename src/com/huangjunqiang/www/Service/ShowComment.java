package com.huangjunqiang.www.Service;

import com.huangjunqiang.www.Util.JdbcUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
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
public class ShowComment extends JFrame {
    private Connection conn=null;
    private PreparedStatement ps=null;
    private ResultSet rs=null;
    //以表格的形式展现帖子信息
    public ShowComment() {
        JFrame frame = new JFrame();
        DefaultTableModel dtm = new DefaultTableModel();
        DefaultTableCellRenderer dtr = new DefaultTableCellRenderer();
        dtr.setHorizontalAlignment(JLabel.CENTER);
        JTable table = new JTable(stuTableModel(dtm));
        table.setDefaultRenderer(Object.class, dtr);
        JScrollPane jsp = new JScrollPane();
        jsp.setViewportView(table);
        frame.add(jsp);
        frame.setBounds(90, 10, 1300, 250);
        JPanel panel = new JPanel();
        frame.add(panel, BorderLayout.NORTH);
        frame.setTitle("帖子评论总览");
        frame.setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    /**
     * 使用DefaultTableModel导入表中数据
     * @param dtm
     * @return 贴子的评论
     */
    private DefaultTableModel stuTableModel(DefaultTableModel dtm) {
        PreparedStatement st = null;
        ResultSet rs = null;
        String[] stu = {"序号","帖子标题","内容","评论者"};
        dtm.setColumnIdentifiers(stu);
        Vector v = null;
        String sql="select *from comment";
        try {
            conn = JdbcUtils.getConnection();
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                v = new Vector();
                int sno = rs.getInt("id");
                String name = rs.getString("postName");
                String content = rs.getString("content");
                String author = rs.getString("commenter");
                v.add(sno);
                v.add(name);
                v.add(content);
                v.add(author);
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

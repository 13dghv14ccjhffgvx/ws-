package com.huangjunqiang.www.View;

import com.huangjunqiang.www.Service.CommentService;
import com.huangjunqiang.www.Dao.Impl.PostDaoImpl;
import com.huangjunqiang.www.Dao.PostDao;
import com.huangjunqiang.www.Service.ShowComment;
import com.huangjunqiang.www.Service.StarService;
import com.huangjunqiang.www.Util.JdbcUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 * Create with IntelliJ IDEA
 *
 * @author: hjq
 * @Date:
 * @Description:
 */
public class PostView extends JFrame {
    Connection conn=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    PostDao p=new PostDaoImpl();
    JLabel id=new JLabel("序    号: ");
    JTextField idField=new JTextField(15);
    JLabel barName=new JLabel("所属贴吧: ");
    JTextField barNameField=new JTextField(15);
    JLabel title=new JLabel("标    题: ");
    JTextField titleField=new JTextField(15);
    JLabel content=new JLabel("内    容: ");
    JTextField contentField=new JTextField(15);
    JLabel author=new JLabel("发 布 者: ");
    JTextField authorField=new JTextField(15);
    JLabel time=new JLabel("发布时间: ");
    JTextField timeField=new JTextField(15);
    JButton addButton = new JButton("发帖");
    JButton deleteButton = new JButton("删贴");
    JButton resetButton = new JButton("重置");
    JButton getButton = new JButton("获取");
    public PostView() {
        JFrame frame = new JFrame();
        DefaultTableModel dtm = new DefaultTableModel();
        DefaultTableCellRenderer dtr = new DefaultTableCellRenderer();
        dtr.setHorizontalAlignment(JLabel.CENTER);
        JTable table = new JTable(stuTableModel(dtm));
        table.getColumnModel().getColumn(0).setPreferredWidth(2);
        table.getColumnModel().getColumn(1).setPreferredWidth(10);
        table.getColumnModel().getColumn(2).setPreferredWidth(20);
        table.getColumnModel().getColumn(3).setPreferredWidth(180);
        table.getColumnModel().getColumn(4).setPreferredWidth(5);
        table.getColumnModel().getColumn(5).setPreferredWidth(30);
        table.setRowHeight(40);
        table.setDefaultRenderer(Object.class, dtr);
        JScrollPane jsp = new JScrollPane();
        jsp.setViewportView(table);
        frame.add(jsp);
        frame.setBounds(90, 10, 1300, 250);
        JPanel panel = new JPanel();
        frame.add(panel, BorderLayout.NORTH);
        JPanel root = new JPanel();
        JButton backButton=new JButton("返回");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StartView();
                dispose();
            }
        });
        JButton freshButton = new JButton("刷新");
        freshButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new PostView();
            }
        });
        JButton commentButton = new JButton("评论");
        commentButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new CommentService();
            }
        });
        JButton starButton =new JButton("收藏");
        starButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new StarService();
            }
        });
        JButton lookButton =new JButton("查看评论");
        lookButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new ShowComment();
            }
        });
        root.add(backButton);
        root.add(freshButton);
        root.add(commentButton);
        root.add(starButton);
        root.add(lookButton);
        frame.setTitle("帖子总览");
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
        String[] stu = {"序号","所属贴吧","标题","内容","发布者", "发布时间"};
        dtm.setColumnIdentifiers(stu);
        Vector v = null;
        String sql="select *from posts";
        try {
            conn = JdbcUtils.getConnection();
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                v = new Vector();
                int sno = rs.getInt("id");
                String name = rs.getString("barName");
                String title = rs.getString("title" );
                String content = rs.getString("content");
                String author = rs.getString("author");
                String time = rs.getString("createTime");
                v.add(sno);
                v.add(name);
                v.add(title);
                v.add(content);
                v.add(author);
                v.add(time);
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
    public void masterPost(){
        setTitle("贴子管理界面");
        Container container = getContentPane();
        setBounds(520, 300, 470, 500);
        //添加一块桌布
        container.setLayout(new BorderLayout());
        JPanel root = new JPanel();
        root.setLayout(null);
        Font f = new Font("宋体", Font.PLAIN, 20);
        id.setBounds(17, 1, 130, 60);
        idField.setBounds(133, 17, 280, 30);
        barName.setBounds(17,50,130,60);
        barNameField.setBounds(133,67,280,30);
        title.setBounds(17,100,130,60);
        titleField.setBounds(133,117,280,30);
        content.setBounds(17,150,130,60);
        contentField.setBounds(133,167,200,30);
        author.setBounds(17,200,130,60);
        authorField.setBounds(133,217,280,30);
        time.setBounds(17,250,130,60);
        timeField.setBounds(133,267,200,30);
        getButton.setBounds(350,260,60,40);
        getButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getTime();
            }
        });
        id.setFont(f);
        barName.setFont(f);
        title.setFont(f);
        content.setFont(f);
        author.setFont(f);
        time.setFont(f);
        root.add(id);
        root.add(idField);
        root.add(barName);
        root.add(barNameField);
        root.add(title);
        root.add(titleField);
        root.add(content);
        root.add(contentField);
        root.add(author);
        root.add(authorField);
        root.add(time);
        root.add(timeField);
        addButton.setBounds(50, 350, 90, 60);
        deleteButton.setBounds(170, 350, 90, 60);
        resetButton.setBounds(290,350,90,60);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    p.addPost(idField,barNameField,titleField,contentField,authorField,timeField);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.resetPost(idField,barNameField,titleField,contentField,authorField,timeField);
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.deletePost(idField);
            }
        });
        root.add(addButton);
        root.add(deleteButton);
        root.add(resetButton);
        root.add(getButton);
        container.add(root);
        this.setVisible(true);
    }

    public void getTime(){
        java.util.Date d=new Date();
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String s=sdf.format(d);
        timeField.setText(s);
    }

    public static void main(String[] args) {
        new PostView().masterPost();
    }
}

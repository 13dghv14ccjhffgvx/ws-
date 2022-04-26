package com.huangjunqiang.www.View;

import com.huangjunqiang.www.Dao.BarDao;
import com.huangjunqiang.www.Dao.Impl.BarDaoImpl;
import com.huangjunqiang.www.Util.JdbcUtils;
import com.huangjunqiang.www.View.MasterView.MasterMainView;

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
public class BarView extends JFrame {
    Connection conn=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    BarDao b=new BarDaoImpl();
    JLabel idLabel=new JLabel("序    号: ");
    JTextField idField=new JTextField(15);
    JLabel barNameLabel=new JLabel("贴吧名称: ");
    JTextField barNameField=new JTextField(15);
    JLabel descLabel=new JLabel("简    介: ");
    JTextField descField=new JTextField(15);
    JLabel createTimeLabel=new JLabel("创建时间: ");
    JTextField createTimeField=new JTextField(15);
    JLabel Mid=new JLabel("管理员id: ");
    JTextField MidField=new JTextField(15);
    JButton addButton = new JButton("添加");
    JButton deleteButton = new JButton("删除");
    JButton resetButton = new JButton("重置");
    JButton getButton = new JButton("获取");
    public BarView(){
        JFrame frame = new JFrame();
        DefaultTableModel dtm = new DefaultTableModel();
        DefaultTableCellRenderer dtr = new DefaultTableCellRenderer();
        dtr.setHorizontalAlignment(JLabel.CENTER);
        JTable table = new JTable(stuTableModel(dtm));
        table.setRowHeight(60);
        table.setDefaultRenderer(Object.class, dtr);
        JScrollPane jsp = new JScrollPane();
        jsp.setViewportView(table);
        frame.add(jsp);
        frame.setBounds(50, 10, 1400, 300);
        JPanel panel = new JPanel();
        frame.add(panel, BorderLayout.NORTH);
        JPanel Jpanel = new JPanel();
        JButton backButton=new JButton("返回");
        //按钮添加监听事件
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MasterMainView();
                dispose();
            }
        });
        JButton freshButton = new JButton("刷新");
        freshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BarView();
            }
        });
        JButton visitButton = new JButton("逛贴吧");
        visitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PostView();
            }
        });
        Jpanel.add(backButton);
        Jpanel.add(freshButton);
        Jpanel.add(visitButton);
        frame.setTitle("欢迎来到AiDu贴吧");
        frame.add(Jpanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    /**
     * 使用DefaultTableModel导入表中数据
     * @param dtm
     * @return
     */
    private DefaultTableModel stuTableModel(DefaultTableModel dtm) {
        String[] stu = {"序号","贴吧名称", "贴吧简介", "创建日期","管理员id"};
        dtm.setColumnIdentifiers(stu);
        Vector v = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql = "select * from postbar";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                v = new Vector();
                int sno = rs.getInt("id");
                String name = rs.getString("barName");
                String desc = rs.getString("desc");
                String createTime = rs.getString("createTime");
                String sid=rs.getString("sid");
                v.add(sno);
                v.add(name);
                v.add(desc);
                v.add(createTime);
                v.add(sid);
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

    //展示贴吧信息
    public void showPostBar(){
        setTitle("贴吧管理界面");
        Container container = getContentPane();
        setBounds(520, 300, 470, 450);
        //添加一块桌布
        container.setLayout(new BorderLayout());
        JPanel root = new JPanel();
        root.setLayout(null);
        Font f = new Font("宋体", Font.PLAIN, 20);
        idLabel.setBounds(17, 1, 130, 60);
        idField.setBounds(133, 17, 280, 30);
        barNameLabel.setBounds(17,50,130,60);
        barNameField.setBounds(133,67,280,30);
        descLabel.setBounds(17,100,130,60);
        descField.setBounds(133,117,280,30);
        createTimeLabel.setBounds(17,150,130,60);
        createTimeField.setBounds(133,167,200,30);
        getButton.setBounds(350,160,60,40);
        getButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getTime();
            }
        });
        Mid.setBounds(17,200,130,60);
        MidField.setBounds(133,217,280,30);
        idLabel.setFont(f);
        barNameLabel.setFont(f);
        descLabel.setFont(f);
        createTimeLabel.setFont(f);
        Mid.setFont(f);
        root.add(idLabel);
        root.add(idField);
        root.add(barNameLabel);
        root.add(barNameField);
        root.add(descLabel);
        root.add(descField);
        root.add(createTimeLabel);
        root.add(createTimeField);
        root.add(Mid);
        root.add(MidField);
        addButton.setBounds(50, 310, 90, 60);
        deleteButton.setBounds(300, 310, 90, 60);
        resetButton.setBounds(175,310,90,60);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b.insertBar(idField,barNameField,descField,createTimeField,MidField);
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b.deleteBar(idField);
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b.resetBar(idField,barNameField,descField,createTimeField,MidField);
            }
        });
        root.add(addButton);
        root.add(deleteButton);
        root.add(resetButton);
        root.add(getButton);
        container.add(root);
        this.setVisible(true);
    }
    //获取当前时间
    public void getTime() {
        Date d=new Date();
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String s=sdf.format(d);
        createTimeField.setText(s);
    }

    public static void main(String[] args) {
        new BarView().showPostBar();
    }
}

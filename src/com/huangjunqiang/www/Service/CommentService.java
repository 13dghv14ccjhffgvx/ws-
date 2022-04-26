package com.huangjunqiang.www.Service;
import com.huangjunqiang.www.Util.JdbcUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Create with IntelliJ IDEA
 *
 * @author: hjq
 * @Date:
 * @Description:
 */
public class CommentService extends JFrame {
    private Connection conn=null;
    private PreparedStatement ps=null;
    private ResultSet rs;
    JLabel postNameLabel =new JLabel("帖子标题: ");
    JTextField postNameField=new JTextField(15);
    JLabel contentLabel =new JLabel("评论内容: ");
    JTextField contentField=new JTextField(15);
    JLabel commenterLabel =new JLabel("评 论 者: ");
    JTextField commenterField=new JTextField(15);
    JButton okButton=new JButton("发布评论");
    JButton cancelButton=new JButton("删除评论");
    public CommentService() {
        setTitle("评论界面");
        Container container = getContentPane();
        setBounds(520, 300, 470, 300);
        //添加一块桌布
        container.setLayout(new BorderLayout());
        JPanel root = new JPanel();
        root.setLayout(null);
        Font f = new Font("宋体", Font.PLAIN, 20);
        postNameLabel.setFont(f);
        contentLabel.setFont(f);
        commenterLabel.setFont(f);
        postNameLabel.setBounds(17, 1, 130, 60);
        postNameField.setBounds(133, 17, 280, 30);
        contentLabel.setBounds(17,50,130,60);
        contentField.setBounds(133,67,280,30);
        commenterLabel.setBounds(17,100,130,60);
        commenterField.setBounds(133,117,280,30);
        okButton.setBounds(100,180,90,60);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addComment(postNameField,contentField,commenterField);
                dispose();
            }
        });
        cancelButton.setBounds(280,180,90,60);
        root.add(postNameLabel);
        root.add(postNameField);
        root.add(contentLabel);
        root.add(contentField);
        root.add(commenterLabel);
        root.add(commenterField);
        root.add(okButton);
        root.add(cancelButton);
        container.add(root);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    //评论帖子操作
    public void addComment(JTextField name,JTextField content,JTextField commenter){
        try {
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);
            String sql = "insert into comment(postName,content,commenter) value(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,name.getText());
            ps.setString(2,content.getText());
            ps.setString(3,commenter.getText());
            ps.executeUpdate();
            conn.commit();
            JOptionPane.showMessageDialog(null,"评论成功");
    }catch (Exception e) {
            JOptionPane.showMessageDialog(null,"评论失败");
            e.printStackTrace();
        }finally{
            JdbcUtils.close(conn,ps);
        }
    }
}

package com.huangjunqiang.www.View;


import com.huangjunqiang.www.View.MasterView.MasterLoginView;
import com.huangjunqiang.www.View.UserView.UserLoginView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Create with IntelliJ IDEA
 *
 * @author: hjq
 * @Date:
 * @Description:
 */
public class StartView extends JFrame {
    private final Container container =getContentPane();
    private final JButton userButton=new JButton("用户登录");
    private final JButton masterButton=new JButton("管理员登录");
    private final JButton visitorButton=new JButton("游客进入");
    private final JButton exitButton=new JButton("退出");
    public StartView() {
        setTitle("登录主界面");
        //设置窗口大小
        setBounds(620,150,300,660);
        //添加一块桌布
        container.setLayout(new BorderLayout());

        //初始化窗口
        init();
        //设计窗口可见
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void init() {
        JPanel root = new JPanel();
        root.setLayout(null);
        userButton.setBounds(1,5,280,135);
        masterButton.setBounds(1,165,280,135);
        visitorButton.setBounds(1,321,280,135);
        exitButton.setBounds(1,480,280,135);
        root.add(userButton);
        root.add(masterButton);
        root.add(visitorButton);
        root.add(exitButton);
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserLoginView();
                dispose();
            }
        });
        masterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MasterLoginView();
                dispose();
            }
        });
        visitorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PostView();
                dispose();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        container.add(root);
    }

    public static void main(String[] args) {
        new StartView();
    }

}

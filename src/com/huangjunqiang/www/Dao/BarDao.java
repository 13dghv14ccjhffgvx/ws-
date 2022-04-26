package com.huangjunqiang.www.Dao;

import javax.swing.*;

/**
 * Create with IntelliJ IDEA
 *
 * @author: hjq
 * @Date:
 * @Description:
 */
public interface BarDao {
    /**
     * 创建贴吧操作
     * @param id
     * @param barName
     * @param desc
     * @param time
     * @param sid
     */
    public abstract void insertBar(JTextField id,JTextField barName,JTextField desc,JTextField time,JTextField sid);

    /**
     * 删除贴吧
     * @param id
     */
    public abstract void deleteBar(JTextField id);

    /**
     * 重置事件
     * @param id
     * @param barName
     * @param desc
     * @param time
     * @param sid
     */
    public abstract void resetBar(JTextField id,JTextField barName,JTextField desc,JTextField time,JTextField sid);
}

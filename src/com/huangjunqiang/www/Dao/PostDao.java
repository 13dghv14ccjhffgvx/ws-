package com.huangjunqiang.www.Dao;

import javax.swing.*;

/**
 * Create with IntelliJ IDEA
 *
 * @author: hjq
 * @Date:
 * @Description:
 */
public interface PostDao {
    /**
     * 发布帖子
     * @param idField
     * @param nameField
     * @param titleField
     * @param contentField
     * @param authorField
     * @param timeField
     */
    public abstract void addPost(JTextField idField, JTextField nameField, JTextField titleField, JTextField contentField,JTextField authorField, JTextField timeField);

    /**
     * 删除帖子
     * @param idField
     */
    public abstract void deletePost(JTextField idField);

    /**
     * 重置事件
     * @param idField
     * @param nameField
     * @param titleField
     * @param contentField
     * @param authorField
     * @param timeField
     */
    public abstract void resetPost(JTextField idField,JTextField nameField,JTextField titleField, JTextField contentField ,JTextField authorField,JTextField timeField);
}

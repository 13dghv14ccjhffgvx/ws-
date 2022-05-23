package com.txg.www.service.intf;

import com.txg.www.model.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    /**
     * 验证用户登录
     *
     * @param userName 用户名
     * @param userPwd  密码
     * @return 用户对象
     * @throws Exception
     */
    User getUser(String userName, String userPwd) throws Exception;

    /**
     * 修改用户信息
     *
     * @param nickName     昵称
     * @param sex          性别I
     * @param introduction 介绍
     * @param userId       用户id
     * @return
     */
    boolean modifyUser(String nickName, String sex, String introduction, String headImg, int userId) throws Exception;

    /**
     * 用过用户id获取用户
     *
     * @param userId 用户id
     * @return
     * @throws Exception
     */
    User getUserByUserId(int userId) throws Exception;

    /**
     * 通过用户名查找相关用户
     *
     * @param userName 用户名
     * @return 所有通过用户名模糊查询的的用户对象集合
     * @throws Exception
     */
    List<User> searchUser(String userName) throws Exception;

    /**
     * 获取除管理员外的所有用户
     *
     * @return
     * @throws Exception
     */
    List<User> getAllUser() throws Exception;

    /**
     * 通过用户名得到用户,用户注册时验证用户名是否存在
     *
     * @param userName 用户名
     * @return
     * @throws Exception
     */
    User getUserByUserName(String userName) throws Exception;

    /**
     * 注册功能
     *
     * @param userName 用户名
     * @param nickName 昵称
     * @param pwd      密码
     * @return
     */
    int register(String userName, String nickName, String pwd) throws SQLException;

    /**
     * 通过昵称获取用户
     *
     * @param nickName
     * @return
     * @throws Exception
     */
    User getUserByNickName(String nickName) throws Exception;

    List<User> searchUsersByNickName(String nickName) throws Exception;

    /**
     * 修改用户密码
     *
     * @param pwd    新密码
     * @param userId 用户id
     * @return
     */
    boolean modifyPwd(String pwd, int userId) throws SQLException;
}

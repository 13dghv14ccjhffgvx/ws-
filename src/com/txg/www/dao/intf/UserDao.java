package com.txg.www.dao.intf;

import com.txg.www.model.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    /**
     * 验证用户
     *
     * @param userName
     * @param userPwd
     * @return
     * @throws Exception
     */
    User selectUser(String userName, String userPwd) throws Exception;

    /**
     * 更新角色信息
     *
     * @param nickName
     * @param sex
     * @param introduction
     * @param id
     * @return
     */
    int updateUser(String nickName, String sex, String introduction, String headImg, int id) throws Exception;

    /**
     * 通过用户id查询用户
     *
     * @param UserId 用户id
     * @return
     * @throws Exception
     */
    User selectUserByUserId(int UserId) throws Exception;

    /**
     * 通过用户名模糊查询相关用户
     *
     * @param userName
     * @return
     * @throws Exception
     */
    List<User> selectUsersByUserName(String userName) throws Exception;

    /**
     * 查询除管理员外的普通用户
     *
     * @return
     * @throws Exception
     */
    List<User> selectGeneralUser() throws Exception;

    /**
     * 通过用户名查找用户
     *
     * @param userName 用户名
     * @return 用户对象
     * @throws Exception
     */
    User selectUserByUserName(String userName) throws Exception;

    User selectUserByNickName(String nickName) throws Exception;

    /**
     * 插入新的用户
     *
     * @param userName 用户名
     * @param nickName 昵称
     * @param pwd      密码
     * @return
     */
    int insertUser(String userName, String nickName, String pwd) throws SQLException;

    /**
     * 通过昵称查询用户
     * SQL模糊查询
     * @param nickName
     * @return
     */
    List<User> selectUsersByNickName(String nickName) throws Exception;

    /**
     * 修改密码
     * @param pwd 新密码
     * @return
     */
    int updatePwd(String pwd,int userId) throws SQLException;
}

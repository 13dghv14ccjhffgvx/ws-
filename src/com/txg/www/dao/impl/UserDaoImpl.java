package com.txg.www.dao.impl;

import com.txg.www.dao.BasicDao;
import com.txg.www.dao.intf.UserDao;
import com.txg.www.model.entity.User;
import com.txg.www.until.GetTime;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl extends BasicDao implements UserDao {


    @Override
    public User selectUser(String userName, String userPwd) throws Exception {
        String sql = "select user_id userId, user_name userName,user_pwd userPwd,user_sex userSex,user_introduction userIntroduction,create_time createTime,head_img headImg ,role_id roleId,nick_name nickName from tb_user where user_name=? and user_pwd=MD5(?)";
        return super.querySingle(sql, User.class, userName, userPwd);
    }

    @Override
    public int updateUser(String nickName, String sex, String introduction,String headImg, int id) throws Exception {
        String sql = "update tb_user  set nick_name=?,user_sex=?,user_introduction=?,head_img=? where user_id=? ";
        return super.executeUpdate(sql, nickName, sex, introduction,headImg,id);
    }

    @Override
    public User selectUserByUserId(int UserId) throws Exception {
        String sql = "select user_id userId, user_name userName,nick_name nickName,user_pwd userPwd,user_sex userSex,user_introduction userIntroduction,create_time createTime,head_img headImg ,role_id roleId from tb_user where user_id=?";
        return super.querySingle(sql, User.class, UserId);
    }

    @Override
    public List<User> selectUsersByUserName(String userName) throws Exception {
        String sql = "select user_id userId, user_name userName,nick_name nickName,user_pwd userPwd,user_sex userSex,user_introduction userIntroduction,create_time createTime,head_img headImg ,role_id roleId from tb_user where user_name like ?";
        return super.queryMulti(sql, User.class, "%" + userName + "%");
    }


    @Override
    public List<User> selectGeneralUser() throws Exception {
        String sql = "select user_id userId, user_name userName,nick_name nickName,user_pwd userPwd,user_sex userSex,user_introduction userIntroduction,create_time createTime,head_img headImg ,role_id roleId from tb_user where role_id=1";
        return super.queryMulti(sql, User.class);
    }

    @Override
    public User selectUserByUserName(String userName) throws Exception {
        String sql = "select user_id userId, user_name userName,nick_name nickName,user_pwd userPwd,user_sex userSex,user_introduction userIntroduction,create_time createTime,head_img headImg ,role_id roleId from tb_user where user_name=?";
        return super.querySingle(sql, User.class, userName);
    }

    @Override
    public int insertUser(String userName, String nickName, String pwd) throws SQLException {
        String sql = "insert into tb_user(user_id,user_name,user_pwd,nick_name,user_sex,user_introduction,create_time,role_id,head_img)values(null,?,MD5(?),?,?,?,?,?,?)";
        return super.executeUpdate(sql, userName, pwd, nickName, "", "", GetTime.getTime(), 1, "");
    }

    @Override
    public List<User> selectUsersByNickName(String nickName) throws Exception {
        String sql = "select user_id userId, user_name userName,nick_name nickName,user_pwd userPwd,user_sex userSex,user_introduction userIntroduction,create_time createTime,head_img headImg ,role_id roleId from tb_user where nick_name like ?";
        return super.queryMulti(sql, User.class, "%" + nickName + "%");
    }

    @Override
    public int updatePwd(String pwd,int userId) throws SQLException {
        String sql="update  tb_user set user_pwd=MD5(?) where user_id=?";
        return super.executeUpdate(sql,pwd,userId);
    }

    @Override
    public User selectUserByNickName(String nickName) throws Exception {
        String sql = "select user_id userId, user_name userName,nick_name nickName,user_pwd userPwd,user_sex userSex,user_introduction userIntroduction,create_time createTime,head_img headImg ,role_id roleId from tb_user where nick_name=?";
        return super.querySingle(sql, User.class, nickName);
    }
}

package com.txg.www.service.impl;

import com.txg.www.dao.impl.CommentDaoImpl;
import com.txg.www.dao.impl.UserDaoImpl;
import com.txg.www.dao.intf.CommentDao;
import com.txg.www.dao.intf.UserDao;
import com.txg.www.model.entity.User;
import com.txg.www.service.intf.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();
    CommentDao commentDao=new CommentDaoImpl();

    @Override
    public User getUser(String userName, String userPwd) throws Exception {
        return userDao.selectUser(userName, userPwd);
    }

    @Override
    public boolean modifyUser(String nickName, String sex, String introduction,String headImg,int userId) throws Exception {
        commentDao.updateCommentNickName(nickName, userId);
        return 1==userDao.updateUser(nickName, sex, introduction,headImg,userId);
    }

    @Override
    public User getUserByUserId(int userId) throws Exception {
        return userDao.selectUserByUserId(userId);
    }

    @Override
    public List<User> searchUser(String userName) throws Exception {
        return userDao.selectUsersByUserName(userName);
    }

    @Override
    public List<User> getAllUser() throws Exception {
        return userDao.selectGeneralUser();
    }

    @Override
    public User getUserByUserName(String userName) throws Exception {
        return userDao.selectUserByUserName(userName);
    }

    @Override
    public int register(String userName, String nickName, String pwd) throws SQLException {
        return userDao.insertUser(userName, nickName, pwd);
    }

    @Override
    public User getUserByNickName(String nickName) throws Exception {
        return userDao.selectUserByNickName(nickName);
    }

    @Override
    public List<User> searchUsersByNickName(String nickName) throws Exception {
        return userDao.selectUsersByNickName(nickName);
    }

    @Override
    public boolean modifyPwd(String pwd, int userId) throws SQLException {
        return 1==userDao.updatePwd(pwd,userId);
    }
}

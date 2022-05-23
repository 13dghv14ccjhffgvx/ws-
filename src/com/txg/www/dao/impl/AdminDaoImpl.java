package com.txg.www.dao.impl;

import com.txg.www.dao.BasicDao;
import com.txg.www.dao.intf.AdminDao;
import com.txg.www.model.entity.User;
import com.txg.www.until.GetTime;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class AdminDaoImpl extends BasicDao implements AdminDao {


    @Override
    public int adminInsertUserBan(int userId, int banTime) throws SQLException {
        String sql = "insert into tb_ban(ban_id,user_id,ban_time)values(null,?,?)";
        return super.executeUpdate(sql, userId, GetTime.getTime(banTime));
    }

    @Override
    public List<User> selectNormalUsers() throws Exception {
        String sql = "select  user_id userId, user_name userName,nick_name nickName,user_pwd userPwd,user_sex userSex,user_introduction userIntroduction,create_time createTime,head_img headImg ,role_id roleId from tb_user where role_id=1 and user_id not IN(select user_id from tb_ban  where ban_time>NOW())";
        return super.queryMulti(sql, User.class);
    }

    @Override
    public List<User> selectBanUsers() throws Exception {
        String sql = "select user_id userId, user_name userName,nick_name nickName,user_pwd userPwd,user_sex userSex,user_introduction userIntroduction,create_time createTime,head_img headImg ,role_id roleId from tb_user where user_id IN(select user_id from tb_ban where ban_time>NOW())";
        return super.queryMulti(sql, User.class);
    }

    @Override
    public int adminDeleteBlog(int blogId) throws SQLException {
        String sql = "delete from tb_blog where blog_id=?";
        return super.executeUpdate(sql, blogId);
    }

    @Override
    public int adminDeleteComment(int commentId) throws SQLException {
        String sql = "delete from tb_comment where comment_id=?";
        return super.executeUpdate(sql, commentId);
    }

    @Override
    public int adminUpdateReportStatus(int reportId) throws SQLException {
        String sql = "update tb_report set status =1 where report_id=?";
        return super.executeUpdate(sql, reportId);
    }

    @Override
    public int deleteBan(int userId) throws SQLException {
        String sql = "delete from tb_ban where user_id=?";
        return super.executeUpdate(sql, userId);
    }

    @Override
    public User selectBanUserByUserId(int userId) throws Exception {
        String sql = "select user_id userId, user_name userName,nick_name nickName,user_pwd userPwd,user_sex userSex,user_introduction userIntroduction,create_time createTime,head_img headImg ,role_id roleId from tb_user where user_id =(select user_id from tb_ban WHERE user_id=? and ban_time>NOW()) ";
        return super.querySingle(sql, User.class, userId);
    }

}

package com.txg.www.dao.impl;

import com.txg.www.dao.BasicDao;
import com.txg.www.dao.intf.CommentDao;
import com.txg.www.model.entity.Comment;
import com.txg.www.until.GetTime;

import java.sql.SQLException;
import java.util.List;

public class CommentDaoImpl extends BasicDao implements CommentDao {
    @Override
    public int insertComment(int userId, int blogId, String content,String userNickName) throws SQLException {
        String sql = "insert into tb_comment(comment_id,user_id,blog_id,content,comment_time,parent_comment_id,user_nick_name)values(null,?,?,?,?,0,?)";
        return super.executeUpdate(sql, userId, blogId, content, GetTime.getTime(),userNickName);
    }

    @Override
    public List<Comment> selectComments(int blogId) throws Exception {
        String sql = "select comment_id commentId,user_id userId,blog_id blogId,content,comment_time commentTime,parent_comment_id parentCommentId,user_nick_name userNickName from tb_comment where blog_id=? and parent_comment_id=0";
        return super.queryMulti(sql, Comment.class, blogId);
    }

    @Override
    public int deleteComment(int userId,int commentId) throws SQLException {
        String sql = "delete from tb_comment where user_id=? and comment_id=?";
        return super.executeUpdate(sql, userId,commentId);
    }

    @Override
    public List<Comment> selectUserComments(int userId) throws Exception {
        String sql = "select comment_id commentId,user_id userId,blog_id blogId,content,comment_time commentTime,parent_comment_id parentCommentId,user_nick_name userNickName from tb_comment where user_id=?";
        return super.queryMulti(sql, Comment.class, userId);
    }

    @Override
    public Comment selectCommentByCommentId(int commentId) throws Exception {
        String sql = "select comment_id commentId,user_id userId,blog_id blogId,content,comment_time commentTime,parent_comment_id parentCommentId,user_nick_name userNickName from tb_comment where comment_id=?";
        return super.querySingle(sql,Comment.class,commentId);
    }

    @Override
    public List<Comment> selectCommentByParentCommentId(int commentId) throws Exception {
        String sql = "select comment_id commentId,user_id userId,blog_id blogId,content,comment_time commentTime,parent_comment_id parentCommentId,user_nick_name userNickName from tb_comment where parent_comment_id=?";
        return super.queryMulti(sql,Comment.class,commentId);
    }

    @Override
    public int insertChildComment(int userId, int blogId, String content, String userNickName, int parentCommentId) throws SQLException {
        String sql = "insert into tb_comment(comment_id,user_id,blog_id,content,comment_time,parent_comment_id,user_nick_name)values(null,?,?,?,?,?,?)";
        return super.executeUpdate(sql, userId, blogId, content, GetTime.getTime(),parentCommentId,userNickName);
    }

    @Override
    public int deleteCommentsByBlogId(int blogId) throws SQLException {
        String sql="delete from tb_comment where blog_id=?";
        return super.executeUpdate(sql,blogId);
    }

    @Override
    public int updateCommentNickName(String nickName,int userId) throws SQLException {
        String sql="update tb_comment set user_nick_name=? where user_id=?";
        return super.executeUpdate(sql,nickName,userId);
    }
}

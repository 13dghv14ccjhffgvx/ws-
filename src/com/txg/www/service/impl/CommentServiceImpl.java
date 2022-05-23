package com.txg.www.service.impl;

import com.txg.www.dao.impl.CommentDaoImpl;
import com.txg.www.dao.intf.CommentDao;
import com.txg.www.model.entity.Comment;
import com.txg.www.service.intf.CommentService;

import java.sql.SQLException;
import java.util.List;

public class CommentServiceImpl implements CommentService {
    CommentDao commentDao = new CommentDaoImpl();

    @Override
    public boolean addComment(int userId, int blogId, String content,String userNickName) throws SQLException {
        return 1 == commentDao.insertComment(userId, blogId, content,userNickName);
    }

    @Override
    public List<Comment> getAllComment(int blogId) throws Exception {
        return commentDao.selectComments(blogId);
    }

    @Override
    public List<Comment> getUserComments(int userId) throws Exception {
        return commentDao.selectUserComments(userId);
    }

    @Override
    public boolean removeComment(int userId, int commentId) throws SQLException {
        return 1 == commentDao.deleteComment(userId, commentId);
    }

    @Override
    public boolean addReply(int userId, int blogId, String content, String userNickName, int parentCommentId) throws SQLException {
        return 1==commentDao.insertChildComment(userId, blogId, content, userNickName, parentCommentId);
    }
}

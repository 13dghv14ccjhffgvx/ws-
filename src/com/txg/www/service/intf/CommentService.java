package com.txg.www.service.intf;

import com.txg.www.model.entity.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentService {
    /**
     * 添加评论
     * @param userId 用户id
     * @param blogId 文章id
     * @param content 评论内容
     * @return
     */
    boolean addComment(int userId, int blogId, String content,String userNickName) throws SQLException;

    /**
     * 得到文章所有评论
     * @param blogId 文章id
     * @return
     * @throws Exception
     */
     List<Comment> getAllComment(int blogId) throws Exception;

    /**
     * 删除评论
     * @param commentId 评论id
     * @return
     */
     boolean removeComment(int userId,int commentId) throws SQLException;

    /**
     * 得到自己发表的所有评论
     * @param userId 用户id
     * @return 用户的所有评论
     * @throws Exception
     */
     List<Comment> getUserComments(int userId) throws Exception;
     boolean addReply(int userId, int blogId, String content,String userNickName,int parentCommentId) throws SQLException;
}

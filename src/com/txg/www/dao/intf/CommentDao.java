package com.txg.www.dao.intf;

import com.txg.www.model.entity.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentDao {
    /**
     * 发表评论
     *
     * @param userId  用户id
     * @param blogId  文章id
     * @param content 评论内容
     * @return
     */
    int insertComment(int userId, int blogId, String content, String userNickName) throws SQLException;

    /**
     * 添加子评论
     *
     * @param userId
     * @param blogId
     * @param content
     * @param userNickName
     * @param parentCommentId
     * @return
     * @throws SQLException
     */
    int insertChildComment(int userId, int blogId, String content, String userNickName, int parentCommentId) throws SQLException;

    /**
     * 查找文章的所有评论
     *
     * @param blogId 文章id
     * @return 评论集合
     * @throws Exception
     */
    List<Comment> selectComments(int blogId) throws Exception;

    /**
     * 删除评论
     *
     * @param commentId
     * @return
     */
    int deleteComment(int userId, int commentId) throws SQLException;

    /**
     * 查找自己的评论
     *
     * @param userId
     * @return 自己的所有评论
     * @throws Exception
     */
    List<Comment> selectUserComments(int userId) throws Exception;

    /**
     * 通过评论id查找评论
     *
     * @param commentId
     * @return
     * @throws Exception
     */
    Comment selectCommentByCommentId(int commentId) throws Exception;

    /**
     * 查询一级评论
     *
     * @param commentId
     * @return
     * @throws Exception
     */
    List<Comment> selectCommentByParentCommentId(int commentId) throws Exception;

    /**
     * 通过文章id删除评论
     *
     * @param blogId
     * @return
     */
    int deleteCommentsByBlogId(int blogId) throws SQLException;

    /**
     * 修改发表评论的昵称
     * @param nickName
     * @param userId
     * @return
     * @throws SQLException
     */
    int updateCommentNickName(String nickName, int userId) throws SQLException;
}

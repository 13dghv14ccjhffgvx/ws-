package com.txg.www.until;

import com.txg.www.dao.impl.CommentDaoImpl;
import com.txg.www.dao.intf.CommentDao;
import com.txg.www.model.entity.Comment;
import com.txg.www.service.impl.UserServiceImpl;
import com.txg.www.service.intf.UserService;

import java.util.List;

public class CommentSetChildNode {
    UserService userService = new UserServiceImpl();

    /**
     * 添加评论的子评论
     *
     * @param comments
     * @throws Exception
     */
    public static void setChildNode(List<Comment> comments) throws Exception {

        CommentDao commentDao = new CommentDaoImpl();
        for (Comment comment : comments) {
            //通过父级评论id获取子评论
            List<Comment> childComments = commentDao.selectCommentByParentCommentId(comment.getCommentId());
            if (childComments != null) {
                comment.setChild(childComments);
                setChildNode(childComments);
            }
        }
    }
}

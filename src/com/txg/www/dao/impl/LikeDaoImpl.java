package com.txg.www.dao.impl;

import com.txg.www.dao.BasicDao;
import com.txg.www.dao.intf.LikeDao;
import com.txg.www.model.entity.Like;

import java.math.BigDecimal;
import java.sql.SQLException;

public class LikeDaoImpl extends BasicDao implements LikeDao {


    @Override
    public int insertLike(int userId, int blogId) throws SQLException {
        String sql="insert into tb_like(like_id,user_id,blog_id)values(null,?,?)";
        return super.executeUpdate(sql,userId,blogId);
    }

    @Override
    public int deleteLike(int userId, int blogId) throws SQLException {
        String sql="delete from tb_like where user_id=? and blog_id=?";

        return super.executeUpdate(sql,userId,blogId);
    }

    @Override
    public Like selectLike(int userId, int blogId) throws Exception {
        String sql="select like_id likeId,user_id userId,blog_id blogId from tb_like where user_id=? and blog_id=?";
        return super.querySingle(sql,Like.class,userId,blogId);
    }

    @Override
    public int slectLikeNum(int userId) throws Exception {
        String sql="select sum(like_num) from tb_blog where user_id=?";
        BigDecimal likeNum = (BigDecimal)super.queryScalar(sql, userId);
        if (likeNum==null){
            return 0;
        }else {
            return Integer.parseInt(likeNum.toString());
        }
    }

    @Override
    public int deleteLikeByBlogId(int blogId) throws SQLException {
        String sql="delete from tb_like where  blog_id=?";
        return super.executeUpdate(sql,blogId);
    }
}

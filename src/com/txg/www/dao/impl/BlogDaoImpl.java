package com.txg.www.dao.impl;

import com.txg.www.dao.BasicDao;
import com.txg.www.dao.intf.BlogDao;
import com.txg.www.model.entity.Blog;
import com.txg.www.until.GetTime;

import java.sql.SQLException;
import java.util.List;

public class BlogDaoImpl extends BasicDao implements BlogDao {
    @Override
    public int intsertBlog(String type, String content, String imgPath, String title,int userId) throws SQLException {
        String sql = "insert into tb_blog (blog_id,type,content,img_path ,title,like_num,user_id,release_time,collect_num) values(null,?,?,?,?,0,?,?,0)";
        return super.executeUpdate(sql, type, content, imgPath, title,userId, GetTime.getTime());
    }

    @Override
    public List<Blog> selectBlogByType(int start, int limit, String type) throws Exception {
        String sql = "select blog_id blogId,content ,title,img_path imgPath ,like_num likeNum ,type,release_time releaseTime,collect_num collectNum from tb_blog where type=? limit ?,? ";
        return super.queryMulti(sql, Blog.class, type, start, limit);
    }

    @Override
    public int selectBlogCount(String type) throws Exception {
        String sql = "select COUNT(*)from tb_blog where type=? ";
        long lenth = (long) super.queryScalar(sql, type);
        return (int) lenth;
    }

    @Override
    public Blog selectBlogById(int blogId) throws Exception {
        String sql = "select blog_id blogId,content ,title,img_path imgPath ,like_num likeNum ,type,user_id userId,release_time releaseTime,collect_num collectNum from tb_blog where blog_id=?";
        return super.querySingle(sql, Blog.class, blogId);
    }

    @Override
    public int updateLikeNum(int blogId,int change) throws SQLException {
        String sql="update tb_blog set like_num=like_num+? where blog_id=?";
        return super.executeUpdate(sql,change,blogId);
    }


    @Override
    public int updateMyBlog(int userId, int blogId, String type,String title, String content, String imgPath) throws SQLException {
        String sql="update tb_blog set type=?,title=?,content=?,img_path=? where user_id=? and blog_Id=?";
        return super.executeUpdate(sql,type,title,content,imgPath,userId,blogId);
    }

    @Override
    public int deleteMyBlog(int userId,int blogId) throws SQLException {
        String sql="delete from tb_blog where user_id=? and blog_id=?";
        return super.executeUpdate(sql,userId,blogId);
    }

    @Override
    public List<Blog> selectBlogBytitle(String title) throws Exception {
        String sql="select blog_id blogId,content ,title,img_path imgPath ,like_num likeNum ,type,user_id userId,release_time releaseTime,collect_num collectNum from tb_blog where title like?";
        return super.queryMulti(sql,Blog.class,"%"+title+"%");
    }

    @Override
    public List<Blog> selectBlogByUserId(int userId) throws Exception {
        String sql="select blog_id blogId,content ,title,img_path imgPath ,like_num likeNum ,type,user_id userId,release_time releaseTime,collect_num collectNum from tb_blog where user_id=? ";
        return super.queryMulti(sql,Blog.class,userId);
    }

    @Override
    public List<Blog> selectBlogOrderByLikeNum() throws Exception {
        String sql="select blog_id blogId,content ,title,img_path imgPath ,like_num likeNum ,type,user_id userId,release_time releaseTime,collect_num collectNum from tb_blog ORDER BY  like_num desc LIMIT 0,10";
        return super.queryMulti(sql,Blog.class);
    }

    @Override
    public int updateCollectNum(int blogId, int change) throws SQLException {
        String sql="update tb_blog set collect_num=collect_num+? where blog_id=?";
        return super.executeUpdate(sql,change,blogId);
    }

    @Override
    public List<Blog> selectBlogOrderByCollectNum() throws Exception {
        String sql="select blog_id blogId,content ,title,img_path imgPath ,like_num likeNum ,type,user_id userId,release_time releaseTime,collect_num collectNum from tb_blog ORDER BY  collect_num desc LIMIT 0,10";
        return super.queryMulti(sql,Blog.class);
    }
}

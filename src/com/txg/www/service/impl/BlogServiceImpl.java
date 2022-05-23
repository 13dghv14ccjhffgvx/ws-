package com.txg.www.service.impl;

import com.txg.www.dao.impl.*;
import com.txg.www.dao.intf.*;
import com.txg.www.model.entity.Blog;
import com.txg.www.service.intf.BlogService;
import com.txg.www.until.PageHelper;

import java.sql.SQLException;
import java.util.List;

public class BlogServiceImpl implements BlogService {
    BlogDao blogDao = new BlogDaoImpl();
    CollectDao collectDao = new CollectDaoImpl();
    LikeDao likeDao = new LikeDaoImpl();
    ReportDao reportDao = new ReportDaoImpl();
    CommentDao commentDao = new CommentDaoImpl();

    @Override
    public int addBlog(String type, String content, String imgPath, String title, int usrId) throws SQLException {
        return blogDao.intsertBlog(type, content, imgPath, title, usrId);
    }

    @Override
    public PageHelper<Blog> listBlogsByPage(String type, int pageNum, int pageSize) throws Exception {
        //1.查询当前页码的数据
        int start = (pageNum - 1) * pageSize;
        int limit = pageSize;
        List<Blog> blogList = blogDao.selectBlogByType(start, limit, type);
        //2.查询并计算图书总页数
        // a.查询总记录
        int count = blogDao.selectBlogCount(type);
        //b.根据总记录数和每页显示的条数，计算总页数
        int pageCount = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
        if (pageCount == 0) {
            pageCount = 1;
        }
        // 3.将分页数据都放到一个PageHelper对象
        PageHelper<Blog> blogPageHelper = new PageHelper<Blog>(blogList, pageNum, pageCount);
        return blogPageHelper;
    }

    @Override
    public Blog getBlogById(int blogId) throws Exception {
        return blogDao.selectBlogById(blogId);
    }

    @Override
    public Boolean modifyLikeNum(int blogId, int change) throws SQLException {
        return 1 == blogDao.updateLikeNum(blogId, change);
    }


    @Override
    public boolean modifyBlog(int userId, int blogId, String type, String title, String content, String imgPath) throws SQLException {
        return 1 == blogDao.updateMyBlog(userId, blogId, type, title, content, imgPath);
    }

    @Override
    public boolean removeMyBlog(int userId, int blogId) throws SQLException {
        collectDao.deleteCollectByBlogId(blogId);
        likeDao.deleteLikeByBlogId(blogId);
        commentDao.deleteCommentsByBlogId(blogId);
        reportDao.deleteReportByBlogId(blogId);
        return 1 == blogDao.deleteMyBlog(userId, blogId);
    }

    @Override
    public List<Blog> searchBlogByTitle(String title) throws Exception {
        return blogDao.selectBlogBytitle(title);
    }

    @Override
    public List<Blog> searchBlogByUserId(int userId) throws Exception {
        return blogDao.selectBlogByUserId(userId);
    }

    @Override
    public List<Blog> getBlogOrderByLikeNum() throws Exception {
        return blogDao.selectBlogOrderByLikeNum();
    }


    @Override
    public List<Blog> getBlogOrderByCollectNum() throws Exception {
        return blogDao.selectBlogOrderByCollectNum();
    }
}

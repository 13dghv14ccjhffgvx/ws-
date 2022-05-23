package com.txg.www.service.intf;

import com.txg.www.model.entity.Blog;
import com.txg.www.until.PageHelper;

import java.sql.SQLException;
import java.util.List;

public interface BlogService {
    /**
     * 发表文章
     *
     * @param type    文章类型
     * @param content 文章内容
     * @param imgPath 文章的图片路径
     * @param title   文章标题
     * @param userId  用户id
     * @return
     */
    int addBlog(String type, String content, String imgPath, String title, int userId) throws SQLException;

    /**
     * 分页
     *
     * @param tyoe     分类,如后端,前端
     * @param pageNum  当前页数
     * @param pageSize 一页有多少条记录
     * @return
     * @throws Exception
     */
    public PageHelper<Blog> listBlogsByPage(String tyoe, int pageNum, int pageSize) throws Exception;

    /**
     * 得到文章
     *
     * @param blogId 文章id
     * @return
     * @throws Exception
     */
    public Blog getBlogById(int blogId) throws Exception;

    /**
     * @param blogId
     * @param change
     * @return
     */
    public Boolean modifyLikeNum(int blogId, int change) throws SQLException;


    /**
     * 修改文章
     *
     * @param userId  用户id
     * @param blogId  文章id
     * @param type    文章类型
     * @param title   文章标题
     * @param content 文章内容
     * @param imgPath 图片路径
     * @return
     */
    public boolean modifyBlog(int userId, int blogId, String type, String title, String content, String imgPath) throws SQLException;

    /**
     * 删除文章
     *
     * @param userId 用户id
     * @param blogId 文章id
     * @return
     */
    public boolean removeMyBlog(int userId, int blogId) throws SQLException;

    /**
     * 通过文章标题搜索文章
     *
     * @param title 文章标题
     * @return
     * @throws Exception
     */
     List<Blog> searchBlogByTitle(String title) throws Exception;


    /**
     * 得到用户的所有文章
     *
     * @param userId 用户id
     * @return
     * @throws Exception
     */
     List<Blog> searchBlogByUserId(int userId) throws Exception;

     List<Blog> getBlogOrderByLikeNum() throws Exception;

     List<Blog> getBlogOrderByCollectNum() throws Exception;
}

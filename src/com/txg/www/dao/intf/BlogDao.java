package com.txg.www.dao.intf;

import com.txg.www.model.entity.Blog;

import java.sql.SQLException;
import java.util.List;

public interface BlogDao {
    /**
     * 添加文章
     * @param type 文章类型
     * @param content 文章内容
     * @param imgPath 文章图片路径
     * @param title 文章标题
     * @param userId 用户id
     * @return
     */
    int intsertBlog(String type, String content, String imgPath, String title, int userId) throws SQLException;

    /**
     * 搜索该分类下的所有文章
     * @param start 开始
     * @param limit 文章条数
     * @param type 类型
     * @return
     * @throws Exception
     */
    List<Blog> selectBlogByType(int start, int limit, String type) throws Exception;

    /**
     * 查询该分类的文章总数
     * @param type 文章类型
     * @return
     * @throws Exception
     */
    int selectBlogCount(String type) throws Exception;

    /**
     * 通过文章id查询文章
     * @param blogId 文章id
     * @return
     * @throws Exception
     */
    Blog selectBlogById(int blogId) throws Exception;

    /**
     * 修改点赞数
     * @param blogId 文章id
     * @param change  增加点赞还是取消点赞
     * @return
     */
    int updateLikeNum(int blogId, int change) throws SQLException;

    /**
     * 修改文章
     * @param userId 用户id
     * @param blogId 文章id
     * @param type 文章类型
     * @param title 文章标题
     * @param content 文章内容
     * @param imgPath 图片路径
     * @return
     */
    int updateMyBlog(int userId, int blogId, String type, String title, String content, String imgPath) throws SQLException;

    /**
     * 删除文章
     * @param userId 用户id
     * @param blogId 文章id
     * @return
     */
    int deleteMyBlog(int userId,int blogId) throws SQLException;

    /**
     * 通过标题搜索文章
     * @param title 标题
     * @return
     * @throws Exception
     */
    List<Blog> selectBlogBytitle(String title) throws Exception;

    /**
     * 通过用户id搜索用户的文章
     * @param userId 用户id
     * @return
     * @throws Exception
     */
    List<Blog> selectBlogByUserId(int userId) throws Exception;

    /**
     * 点赞数排行获取文章
     * @return
     * @throws Exception
     */
    List<Blog> selectBlogOrderByLikeNum() throws Exception;

    /**
     * 修改文章收藏数
     * @param blogId
     * @param change
     * @return
     * @throws SQLException
     */
    int updateCollectNum(int blogId,int change) throws SQLException;

    /**
     * 根据收藏数排行文章
     * @return
     * @throws Exception
     */
    List<Blog>selectBlogOrderByCollectNum() throws Exception;
}

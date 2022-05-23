package com.txg.www.dao.intf;

import com.txg.www.model.entity.Report;

import java.sql.SQLException;
import java.util.List;

public interface ReportDao {
    /**
     * 添加举报
     * @param blogId 被举报的文章id
     * @param userId 发起举报的用户id
     * @param reportType 举报类型
     * @param reportContent 举报内容
     * @param imgPath 举报的截图
     * @return
     */
    int insertReport(int blogId, int userId, String reportType, String reportContent, String imgPath) throws SQLException;

    /**
     * 查找所有的举报
     * @return
     * @throws Exception
     */
    List<Report> selectReport() throws Exception;

    /**
     * 通过举报id查找举报
     * @param reportId 举报id
     * @return
     * @throws Exception
     */
    Report selectReportByReportId(int reportId) throws Exception;

    /**
     * 查询已审核的举报
     * @return
     * @throws Exception
     */
    List<Report> selectCompleteReport() throws Exception;

    /**
     * 查询未审核的举报
     * @return
     * @throws Exception
     */
    List<Report> selectInompleteReport() throws Exception;

    /**
     * 通过文章id删除举报
     * @param blogId
     * @return
     */
    int deleteReportByBlogId(int blogId) throws SQLException;
}

package com.txg.www.service.intf;

import com.txg.www.model.entity.Report;

import java.sql.SQLException;
import java.util.List;

public interface ReportService {
    /**
     * 添加举报
     * @param blogId 举报的文章id
     * @param userId 发起举报的用户id
     * @param reportType 举报类型
     * @param reportContent 举报的内容
     * @param imgPath 举报的截图
     * @return
     */
    boolean addReport(int blogId, int userId, String reportType, String reportContent, String imgPath) throws SQLException;

    /**
     * 获取所有的举报
     * @return
     * @throws Exception
     */
    List<Report> getReports() throws Exception;

    /**
     * 通过举报id获取举报内容
     * @param reportId
     * @return
     * @throws Exception
     */
    Report getReport(int reportId) throws Exception;

    /**
     * 获取已经审核的举报
     * @return
     * @throws Exception
     */
    List<Report> getCompleteReports() throws Exception;

    /**
     * 获取未审核的举报
     * @return
     * @throws Exception
     */
    List<Report> getIncompleteReports() throws Exception;
}

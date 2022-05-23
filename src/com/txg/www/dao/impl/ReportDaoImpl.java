package com.txg.www.dao.impl;

import com.txg.www.dao.BasicDao;
import com.txg.www.dao.intf.ReportDao;
import com.txg.www.model.entity.Report;

import java.sql.SQLException;
import java.util.List;

public class ReportDaoImpl extends BasicDao implements ReportDao {
    @Override
    public Report selectReportByReportId(int reportId) throws Exception {
        String sql = "select report_id reportId,blog_id blogId,user_id userId,report_type reportType,report_content reportContent,img_path imgPath from tb_report where report_id=?";
        return super.querySingle(sql, Report.class, reportId);
    }

    @Override
    public int insertReport(int blogId, int userId, String reportType, String reportContent, String imgPath) throws SQLException {
        String sql = "insert into tb_report(report_id,blog_id,user_id,report_type,report_content,img_path,status)values(null,?,?,?,?,?,0)";
        return super.executeUpdate(sql, blogId, userId, reportType, reportContent, imgPath);
    }

    @Override
    public List<Report> selectInompleteReport() throws Exception {
        String sql = "select report_id reportId,blog_id blogId,user_id userId,report_type reportType,report_content reportContent,img_path imgPath from tb_report where status=0";
        return super.queryMulti(sql,Report.class);
    }

    @Override
    public List<Report> selectCompleteReport() throws Exception {
        String sql = "select report_id reportId,blog_id blogId,user_id userId,report_type reportType,report_content reportContent,img_path imgPath from tb_report where status=1";
        return super.queryMulti(sql,Report.class);
    }

    @Override
    public List<Report> selectReport() throws Exception {
        String sql = "select report_id reportId,blog_id blogId,user_id userId,report_type reportType,report_content reportContent,img_path imgPath from tb_report";
        return super.queryMulti(sql, Report.class);
    }

    @Override
    public int deleteReportByBlogId(int blogId) throws SQLException {
        String sql="delete from tb_report where blog_id =?";
        return super.executeUpdate(sql,blogId);
    }
}

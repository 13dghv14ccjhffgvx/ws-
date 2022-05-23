package com.txg.www.service.impl;

import com.txg.www.dao.impl.ReportDaoImpl;
import com.txg.www.dao.intf.ReportDao;
import com.txg.www.model.entity.Report;
import com.txg.www.service.intf.ReportService;

import java.sql.SQLException;
import java.util.List;

public class ReportServiceImpl implements ReportService {
    ReportDao reportDao = new ReportDaoImpl();

    @Override
    public boolean addReport(int blogId, int userId, String reportType, String reportContent, String imgPath) throws SQLException {
        return 1 == reportDao.insertReport(blogId, userId, reportType, reportContent, imgPath);
    }

    @Override
    public Report getReport(int reportId) throws Exception {
        return reportDao.selectReportByReportId(reportId);
    }

    @Override
    public List<Report> getReports() throws Exception {
        return reportDao.selectReport();
    }

    @Override
    public List<Report> getCompleteReports() throws Exception {
        return reportDao.selectCompleteReport();
    }

    @Override
    public List<Report> getIncompleteReports() throws Exception {
        return reportDao.selectInompleteReport();
    }
}

package com.txg.www.model.entity;

public class Report {
    private int reportId;
    //文章id
    private int blogId;
    //用户id
    private int userId;
    //举报内容
    private String reportContent;
    //举报截图
    private String imgPath;
    //举报的审核状态
    private int status;
    //举报类型
    private String reportType;

    public Report() {
    }

    public Report(int reportId, int blogId, int userId, String reportContent, String imgPath, int status, String reportType) {
        this.reportId = reportId;
        this.blogId = blogId;
        this.userId = userId;
        this.reportContent = reportContent;
        this.imgPath = imgPath;
        this.status = status;
        this.reportType = reportType;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportId=" + reportId +
                ", blogId=" + blogId +
                ", userId=" + userId +
                ", reportContent='" + reportContent + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", status=" + status +
                ", reportType='" + reportType + '\'' +
                '}';
    }
}

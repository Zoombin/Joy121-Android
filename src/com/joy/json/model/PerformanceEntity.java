package com.joy.json.model;

/**
 * 绩效考核数据对象
 * @author ryan zhou 2015-01-12
 *
 */

public class PerformanceEntity extends TResult {
	private static final long serialVersionUID = 1L;
	
	private String ReportCaseId;
	private String ReportName;
	private String Period;
	private String EmployeeId;
	private String Company;
	private String PerformanceScore;
	private String PerformanceSeq;
	private String PerformancePoints;
	private String TotalNum;
	
	


	public String getReportCaseId() {
		return ReportCaseId;
	}



	public void setReportCaseId(String reportCaseId) {
		ReportCaseId = reportCaseId;
	}



	public String getReportName() {
		return ReportName;
	}



	public void setReportName(String reportName) {
		ReportName = reportName;
	}



	public String getPeriod() {
		return Period;
	}



	public void setPeriod(String period) {
		Period = period;
	}



	public String getEmployeeId() {
		return EmployeeId;
	}



	public void setEmployeeId(String employeeId) {
		EmployeeId = employeeId;
	}



	public String getCompany() {
		return Company;
	}



	public void setCompany(String company) {
		Company = company;
	}



	public String getPerformanceScore() {
		return PerformanceScore;
	}



	public void setPerformanceScore(String performanceScore) {
		PerformanceScore = performanceScore;
	}



	public String getPerformanceSeq() {
		return PerformanceSeq;
	}



	public void setPerformanceSeq(String performanceSeq) {
		PerformanceSeq = performanceSeq;
	}



	public String getPerformancePoints() {
		return PerformancePoints;
	}



	public void setPerformancePoints(String performancePoints) {
		PerformancePoints = performancePoints;
	}



	public String getTotalNum() {
		return TotalNum;
	}



	public void setTotalNum(String totalNum) {
		TotalNum = totalNum;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public PerformanceEntity() {
		// TODO Auto-generated constructor stub
	}

}

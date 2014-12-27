package com.joy.json.model;

/**
 * 积分信息
 * @author ryan zhou 2014-11-13
 *
 */

public class PayrollBriefEntity extends TResult {
	private static final long serialVersionUID = 1L;
	
	private String realwages;
	private String period;
	

	

	public String getRealwages() {
		return realwages;
	}

	public void setRealwages(String realwages) {
		this.realwages = realwages;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}


	public PayrollBriefEntity() {
		// TODO Auto-generated constructor stub
	}
	
	public PayrollBriefEntity(String realwages,String period) {
		// TODO Auto-generated constructor stub
		this.realwages = realwages;
		this.period = period;
	}

}

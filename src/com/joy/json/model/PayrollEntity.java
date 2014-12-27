package com.joy.json.model;

/**
 * 积分信息
 * @author ryan zhou 2014-11-13
 *
 */

public class PayrollEntity extends TResult {
	private static final long serialVersionUID = 1L;
	
	private String realwages;
	private String period;
	private String basepay;
	private String meritpay;
	private String secret;
	private String positionsalary;
	private String overtimesalary;
	private String subsidy;
	private String onechildfee;
	private String bonus;
	private String annualbonus;
	private String addothers;
	private String leavededuction;
	private String endowmentinsurance;
	private String endowmentinsuranceretroactive;
	private String hospitalizationinsurance;
	private String hospitalizationinsuranceretroactive;
	private String unemploymentinsurance;
	private String unemploymentinsuranceretroactive;
	private String reservefund;
	private String reservefundretroactive;
	private String deductothers;
	private String pretaxwages;
	private String incometax;
	private String others;

	public String getAddothers() {
		return addothers;
	}

	public void setAddothers(String addothers) {
		this.addothers = addothers;
	}

	public String getDeductothers() {
		return deductothers;
	}

	public void setDeductothers(String deductothers) {
		this.deductothers = deductothers;
	}

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

	public String getBasepay() {
		return basepay;
	}

	public void setBasepay(String basepay) {
		this.basepay = basepay;
	}

	public String getMeritpay() {
		return meritpay;
	}

	public void setMeritpay(String meritpay) {
		this.meritpay = meritpay;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getPositionsalary() {
		return positionsalary;
	}

	public void setPositionsalary(String positionsalary) {
		this.positionsalary = positionsalary;
	}

	public String getOvertimesalary() {
		return overtimesalary;
	}

	public void setOvertimesalary(String overtimesalary) {
		this.overtimesalary = overtimesalary;
	}

	public String getSubsidy() {
		return subsidy;
	}

	public void setSubsidy(String subsidy) {
		this.subsidy = subsidy;
	}

	public String getOnechildfee() {
		return onechildfee;
	}

	public void setOnechildfee(String onechildfee) {
		this.onechildfee = onechildfee;
	}

	public String getBonus() {
		return bonus;
	}

	public void setBonus(String bonus) {
		this.bonus = bonus;
	}

	public String getAnnualbonus() {
		return annualbonus;
	}

	public void setAnnualbonus(String annualbonus) {
		this.annualbonus = annualbonus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getLeavededuction() {
		return leavededuction;
	}

	public void setLeavededuction(String leavededuction) {
		this.leavededuction = leavededuction;
	}

	public String getEndowmentinsurance() {
		return endowmentinsurance;
	}

	public void setEndowmentinsurance(String endowmentinsurance) {
		this.endowmentinsurance = endowmentinsurance;
	}

	public String getHospitalizationinsurance() {
		return hospitalizationinsurance;
	}

	public void setHospitalizationinsurance(String hospitalizationinsurance) {
		this.hospitalizationinsurance = hospitalizationinsurance;
	}

	public String getUnemploymentinsurance() {
		return unemploymentinsurance;
	}

	public void setUnemploymentinsurance(String unemploymentinsurance) {
		this.unemploymentinsurance = unemploymentinsurance;
	}

	public String getReservefund() {
		return reservefund;
	}

	public void setReservefund(String reservefund) {
		this.reservefund = reservefund;
	}
	
	

	public String getEndowmentinsuranceretroactive() {
		return endowmentinsuranceretroactive;
	}

	public void setEndowmentinsuranceretroactive(
			String endowmentinsuranceretroactive) {
		this.endowmentinsuranceretroactive = endowmentinsuranceretroactive;
	}

	public String getHospitalizationinsuranceretroactive() {
		return hospitalizationinsuranceretroactive;
	}

	public void setHospitalizationinsuranceretroactive(
			String hospitalizationinsuranceretroactive) {
		this.hospitalizationinsuranceretroactive = hospitalizationinsuranceretroactive;
	}

	public String getUnemploymentinsuranceretroactive() {
		return unemploymentinsuranceretroactive;
	}

	public void setUnemploymentinsuranceretroactive(
			String unemploymentinsuranceretroactive) {
		this.unemploymentinsuranceretroactive = unemploymentinsuranceretroactive;
	}

	public String getReservefundretroactive() {
		return reservefundretroactive;
	}

	public void setReservefundretroactive(String reservefundretroactive) {
		this.reservefundretroactive = reservefundretroactive;
	}

	public String getPretaxwages() {
		return pretaxwages;
	}

	public void setPretaxwages(String pretaxwages) {
		this.pretaxwages = pretaxwages;
	}

	public String getIncometax() {
		return incometax;
	}

	public void setIncometax(String incometax) {
		this.incometax = incometax;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	public PayrollEntity() {
		// TODO Auto-generated constructor stub
	}
	
	public PayrollEntity(String realwages,String period, String basepay, String meritpay, String secret, String positionsalary
			, String overtimesalary, String subsidy, String onechildfee, String bonus, String annualbonus, String leavededuction, String endowmentinsurance
			, String hospitalizationinsurance, String unemploymentinsurance, String reservefund, String pretaxwages, String incometax, String others) {
		// TODO Auto-generated constructor stub
		this.realwages = realwages;
		this.period = period;
		this.basepay = basepay;
		this.meritpay = meritpay;
		this.secret = secret;
		this.positionsalary = positionsalary;
		this.overtimesalary = overtimesalary;
		this.subsidy = subsidy;
		this.onechildfee = onechildfee;
		this.bonus = bonus;
		this.annualbonus = annualbonus;
		this.leavededuction = leavededuction;
		this.endowmentinsurance = endowmentinsurance;
		this.hospitalizationinsurance = hospitalizationinsurance;
		this.unemploymentinsurance = unemploymentinsurance;
		this.reservefund = reservefund;
		this.pretaxwages = pretaxwages;
		this.incometax = incometax;
		this.others = others;
	}

}

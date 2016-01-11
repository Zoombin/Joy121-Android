package com.joy.json.model;

public class EntryManageBankCardImageEntity extends TResult{
	/*
	 * rainbow  入职管理中银行卡图片反正面
	 */
	private static final long serialVersionUID = 1L;
    private String BankCardPositive;//正面照
    private String BankCardReverse;//反面照
	public String getBankCardPositive() {
		return BankCardPositive;
	}
	public void setBankCardPositive(String bankCardPositive) {
		BankCardPositive = bankCardPositive;
	}
	public String getBankCardReverse() {
		return BankCardReverse;
	}
	public void setBankCardReverse(String bankCardReverse) {
		BankCardReverse = bankCardReverse;
	}
}

package com.joy.json.operation;

public interface ITaskOperation extends IOperation<Object, Object, Object> {

	// 服务器地址
	//public static final String IP = "http://cloud.joy121.com/ajaxpage/app/msg.ashx";
	public static final String IP = "http://cloud.joy121.com:99/ajaxpage/app/msg.ashx";
	public static final String entryGetInfoIp="http://test.joy121.com:999/api/Entry/GetPersonInfo";
	public static final String entryUpdatePersonInfo="http://test.joy121.com:999/api/Entry/PostUpdatePersonInfo";
	
	// 连接超时时间
	static final int TIMEOUT = 5*1000;
}
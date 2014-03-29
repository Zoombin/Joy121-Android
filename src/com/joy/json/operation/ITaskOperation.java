package com.joy.json.operation;

public interface ITaskOperation extends IOperation<Object, Object, Object> {

	// 服务器地址
	public static final String IP = "http://www.joy121.com/sys/ajaxpage/app/Msg.ashx";
	// 连接超时时间
	static final int TIMEOUT = 5*1000;
}

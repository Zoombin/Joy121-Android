package com.joy.json.operation;

public interface ITaskOperation extends IOperation<Object, Object, Object> {

    // 服务器地址
	//public static final String IP = "http://cloud.joy121.com/ajaxpage/app/msg.ashx";
	public static final String IP = "http://cloud.joy121.com:99/ajaxpage/app/msg.ashx";
    //得到的模块
    public static final String getModules="http://cloud.joy121.com:999/api/SysData/GetModulesByCompany";
	public static final String entryGetInfoIp="http://test.joy121.com:999/api/Entry/GetPersonInfo";
	public static final String entryUpdatePersonInfo="http://test.joy121.com:999/api/Entry/PostUpdatePersonInfo";
    //部门绑定
	public static final String getDepartmentData="http://cloud.joy121.com:999/api/Entry/GetSysData";
    //得到所有字典数据
	public static final String getComGroupSysData="http://cloud.joy121.com:999/api/SysData/GetComGroupSysData";
	//通讯录中的重要联系人
	public static final String relationContacts="http://cloud.joy121.com:999/api/entry/GetEntryRelation";
	//上传图片
	public static final String uploadImg="http://test.joy121.com:999/api/UpFile/PostFile";
	
	
	
	
//	//得到的模块
//	public static final String getModules="http://cloud.joy121.com:70/api/SysData/GetModulesByCompany";
//	public static final String entryGetInfoIp="http://test.joy121.com:70/api/Entry/GetPersonInfo";
//	public static final String entryUpdatePersonInfo="http://test.joy121.com:70/api/Entry/PostUpdatePersonInfo";
//	//部门绑定
//	public static final String getDepartmentData="http://cloud.joy121.com:70/api/Entry/GetSysData";
//	//得到所有字典数据
//	public static final String getComGroupSysData="http://cloud.joy121.com:70/api/SysData/GetComGroupSysData";
//	//通讯录中的重要联系人
//	public static final String relationContacts="http://cloud.joy121.com:70/api/entry/GetEntryRelation";
//	//上传图片
//	public static final String uploadImg="http://test.joy121.com:70/api/UpFile/PostFile";
//	
	
	
	
	
	// 连接超时时间
	static final int TIMEOUT = 5*1000;
}
package com.joy.json.operation.impl;


import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;

import android.util.Log;

import com.joy.JoyApplication;
import com.joy.Utils.SharedPreferencesUtils;
import com.joy.json.http.AbstractHttpApi;
import com.joy.json.http.HttpApi;
import com.joy.json.http.HttpApiWithBasicAuth;
import com.joy.json.model.EntryManageEntity;
import com.joy.json.operation.ITaskOperation;
import com.joy.json.parse.EntrySaveParse;

public class EntrySaveOp implements ITaskOperation {

	@Override
	public Object exec(Object in, Object res) throws Exception {
		EntryManageEntity entity=(EntryManageEntity) in;
		DefaultHttpClient httpClient = AbstractHttpApi.createHttpClient();
		httpClient.getParams().setParameter(
				HttpConnectionParams.CONNECTION_TIMEOUT, TIMEOUT);
		httpClient.getParams().setParameter(HttpConnectionParams.SO_TIMEOUT,
				TIMEOUT);
		HttpApi httpApi = new HttpApiWithBasicAuth(httpClient, "testRest");
		HttpPost post = httpApi.createHttpPost(
				entryUpdatePersonInfo,
				new BasicNameValuePair("loginname", SharedPreferencesUtils
						.getLoginName(JoyApplication.getSelf())),
				
//				private String ComEntryDate;//到岗日期
//				private String Residence;//现居地址
//				private String Mobile;//联系方式
//				private String UrgentContact;//紧急联系人
//				private String UrgentMobile;//紧急联系方式
//				private String Regions;//户口所在地
				new BasicNameValuePair("PersonInfo", String.format(
						"{\"loginname\":\"%s\",\"ComEntryDate\":\"%s\",\"Residence\":\"%s\",\"Mobile\":\"%s\"}",
						SharedPreferencesUtils
						.getLoginName(JoyApplication
										.getSelf()), entity
										.getComEntryDate(),entity
										.getResidence(),entity
										.getMobile()))
				);
		Log.e("1", String.format(
				"{\"ComEntryDate\":\"%s\",\"Residence\":\"%s\",\"Mobile\":\"%s\"}",
				entity.getComEntryDate(),
				entity.getResidence(),
				entity.getMobile()));
		Log.e("dfdfdsfsdfdfdfdfffffffffff","ddddddddddddddddddddddddd");
		return (EntryManageEntity) httpApi.doHttpRequest(post, new EntrySaveParse());
	}

}

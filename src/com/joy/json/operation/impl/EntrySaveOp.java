package com.joy.json.operation.impl;


import org.apache.http.client.methods.HttpPost;
import com.google.gson.Gson;
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
		String str = new Gson().toJson(entity);
		HttpPost post = httpApi.createHttpPost(
				entryUpdatePersonInfo+"?loginname="+SharedPreferencesUtils
				.getLoginName(JoyApplication.getSelf()),			
				new BasicNameValuePair("PersonInfo",str));
//		String str1 = new Gson().toJson(entity);
//		Log.e("++++++++++++++++++++++++++",str1);
//		
//		Log.e("1", String.format(
//				"{\"ComEntryDate\":\"%s\",\"Residence\":\"%s\",\"Mobile\":\"%s\"}",
//				entity.getComEntryDate(),
//				entity.getResidence(),
//				entity.getMobile()));
		return (EntryManageEntity) httpApi.doHttpRequest(post, new EntrySaveParse());
	}

}

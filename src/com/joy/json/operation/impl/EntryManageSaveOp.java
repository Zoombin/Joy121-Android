package com.joy.json.operation.impl;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;

import com.joy.JoyApplication;
import com.joy.Utils.SharedPreferencesUtils;
import com.joy.json.http.AbstractHttpApi;
import com.joy.json.http.HttpApi;
import com.joy.json.http.HttpApiWithBasicAuth;
import com.joy.json.model.EntryEntity;
import com.joy.json.operation.ITaskOperation;
import com.joy.json.parse.EntryManageParse;

public class EntryManageSaveOp implements ITaskOperation {

	@Override
	public Object exec(Object in, Object res) throws Exception {
		DefaultHttpClient httpClient = AbstractHttpApi.createHttpClient();
		httpClient.getParams().setParameter(
				HttpConnectionParams.CONNECTION_TIMEOUT, TIMEOUT);
		httpClient.getParams().setParameter(HttpConnectionParams.SO_TIMEOUT,
				TIMEOUT);
		HttpApi httpApi = new HttpApiWithBasicAuth(httpClient, "testRest");
		HttpGet get = httpApi.createHttpGet(
				entryUpdatePersonInfo,
				new BasicNameValuePair("loginName", SharedPreferencesUtils
						.getLoginName(JoyApplication.getSelf()))
//				new BasicNameValuePair("json", String.format(
//						"{\"loginname\":\"%s\"}", SharedPreferencesUtils
//								.getLoginName(JoyApplication.getSelf()))),
//				new BasicNameValuePair("token", new MD5()
//						.getMD5ofStr(SharedPreferencesUtils
//								.getLoginName(JoyApplication.getSelf())
//								+ MD5.key))
				);
		return (EntryEntity) httpApi.doHttpRequest(get, new EntryManageParse());
	}

}

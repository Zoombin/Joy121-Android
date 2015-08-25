package com.joy.json.operation.impl;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;

import android.util.Log;

import com.joy.JoyApplication;
import com.joy.Utils.MD5;
import com.joy.Utils.SharedPreferencesUtils;
import com.joy.json.http.AbstractHttpApi;
import com.joy.json.http.HttpApi;
import com.joy.json.http.HttpApiWithBasicAuth;
import com.joy.json.model.EntryDepartmentDetailEntity;
import com.joy.json.model.EntryDepartmentEntity;
import com.joy.json.operation.ITaskOperation;
import com.joy.json.parse.EntryDepartmentParse;

public class EntryDepartmentOp implements ITaskOperation {

	@Override
	public Object exec(Object in, Object res) throws Exception {
		EntryDepartmentDetailEntity details=(EntryDepartmentDetailEntity) in;
		DefaultHttpClient httpClient = AbstractHttpApi.createHttpClient();
		httpClient.getParams().setParameter(
				HttpConnectionParams.CONNECTION_TIMEOUT, TIMEOUT);
		httpClient.getParams().setParameter(HttpConnectionParams.SO_TIMEOUT,
				TIMEOUT);
		HttpApi httpApi = new HttpApiWithBasicAuth(httpClient, "testRest");
		HttpGet get = httpApi.createHttpGet(
				getDepartmentData,
				new BasicNameValuePair("loginName", SharedPreferencesUtils
						.getLoginName(JoyApplication.getSelf())),
				new BasicNameValuePair("type", "CostCenterno"),
				new BasicNameValuePair("parentId","-1")
				);
		return (EntryDepartmentEntity) httpApi.doHttpRequest(get, new EntryDepartmentParse());
	}

}

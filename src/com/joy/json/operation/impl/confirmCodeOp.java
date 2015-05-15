package com.joy.json.operation.impl;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import com.joy.JoyApplication;
import com.joy.Utils.SharedPreferencesUtils;
import com.joy.json.http.AbstractHttpApi;
import com.joy.json.http.HttpApi;
import com.joy.json.http.HttpApiWithBasicAuth;
import com.joy.json.model.BindMobileEntity;
import com.joy.json.operation.ITaskOperation;
import com.joy.json.parse.BindMobileParse;
import com.joy.json.parse.ChangePwdParse;

public class confirmCodeOp implements ITaskOperation{

	@Override
	public Object exec(Object in, Object res) throws Exception {
	    BindMobileEntity entity=(BindMobileEntity) in;
	    DefaultHttpClient httpClient=AbstractHttpApi.createHttpClient();
	    httpClient.getParams().setParameter(
				HttpConnectionParams.CONNECTION_TIMEOUT, TIMEOUT);
		httpClient.getParams().setParameter(HttpConnectionParams.SO_TIMEOUT,
				TIMEOUT);
		HttpApi httpApi = new HttpApiWithBasicAuth(httpClient, "testRest");
		HttpGet get = httpApi
				.createHttpGet(
						IP,
						new BasicNameValuePair("action", "bindmobile"),
						new BasicNameValuePair(
								"json",
								String.format(
										"{\"loginname\":\"%s\",\"verifycode\":\"%s\"}",
										SharedPreferencesUtils
										.getLoginName(JoyApplication
														.getSelf()), entity
														.getsecuritycode())));
		return (BindMobileEntity) httpApi.doHttpRequest(get,
				new BindMobileParse());
	}
}

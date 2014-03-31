package com.joy.json.operation.impl;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;

import com.joy.json.http.AbstractHttpApi;
import com.joy.json.http.HttpApi;
import com.joy.json.http.HttpApiWithBasicAuth;
import com.joy.json.model.LoginEntity;
import com.joy.json.operation.ITaskOperation;
import com.joy.json.parse.LoginParse;

public class LoginOp implements ITaskOperation {

	@Override
	public Object exec(Object in, Object res) throws Exception {
		LoginEntity login = (LoginEntity) in;
		DefaultHttpClient httpClient = AbstractHttpApi.createHttpClient();
		httpClient.getParams().setParameter(HttpConnectionParams.CONNECTION_TIMEOUT, TIMEOUT);
		httpClient.getParams().setParameter(HttpConnectionParams.SO_TIMEOUT, TIMEOUT);
		HttpApi httpApi = new HttpApiWithBasicAuth(httpClient, "testRest");
		HttpGet get = httpApi.createHttpGet(IP,
				new BasicNameValuePair("action", "login"),
				new BasicNameValuePair("json", String
								.format("{\"loginname\":\"%s\",\"loginpwd\":\"%s\"}",
										login.getLoginname(), login.getLoginpwd())));
		return (LoginEntity) httpApi.doHttpRequest(get,
				new LoginParse());
	}
}

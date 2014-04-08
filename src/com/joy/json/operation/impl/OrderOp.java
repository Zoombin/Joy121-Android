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
import com.joy.json.model.OrderEntity;
import com.joy.json.operation.ITaskOperation;
import com.joy.json.parse.OrderParse;

public class OrderOp implements ITaskOperation {

	@Override
	public Object exec(Object in, Object res) throws Exception {
		DefaultHttpClient httpClient = AbstractHttpApi.createHttpClient();
		httpClient.getParams().setParameter(
				HttpConnectionParams.CONNECTION_TIMEOUT, TIMEOUT);
		httpClient.getParams().setParameter(HttpConnectionParams.SO_TIMEOUT,
				TIMEOUT);
		HttpApi httpApi = new HttpApiWithBasicAuth(httpClient, "testRest");
		HttpGet get = httpApi.createHttpGet(
				IP,
				new BasicNameValuePair("action", "user_order"),
				new BasicNameValuePair("json", String.format(
						"{\"loginname\":\"%s\"}", SharedPreferencesUtils
								.getLoginName(JoyApplication.getSelf()))));
		return (OrderEntity) httpApi.doHttpRequest(get, new OrderParse());
	}
}

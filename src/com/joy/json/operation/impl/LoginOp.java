package com.joy.json.operation.impl;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.telephony.TelephonyManager;

import com.joy.JoyApplication;
import com.joy.Utils.MD5;
import com.joy.Utils.Utils;
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
		httpClient.getParams().setParameter(
				HttpConnectionParams.CONNECTION_TIMEOUT, TIMEOUT);
		httpClient.getParams().setParameter(HttpConnectionParams.SO_TIMEOUT,
				TIMEOUT);
		HttpApi httpApi = new HttpApiWithBasicAuth(httpClient, "testRest");
		String imei = Utils.getDeviceImei(JoyApplication.getInstance().getApplicationContext());
		HttpGet get = httpApi.createHttpGet(
				IP,
				new BasicNameValuePair("action", "login"),
				new BasicNameValuePair("json", String.format(
						"{\"loginname\":\"%s\",\"loginpwd\":\"%s\",\"imeino\":\"%s\"}",
						login.getLoginname(), login.getLoginpwd(), imei)),
				new BasicNameValuePair("token", new MD5().getMD5ofStr(login.getLoginname()
						+ MD5.key)));
		HttpParams P  = get.getParams();
		return (LoginEntity) httpApi.doHttpRequest(get, new LoginParse());
	}
}

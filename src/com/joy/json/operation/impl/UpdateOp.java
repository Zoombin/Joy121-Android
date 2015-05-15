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
import com.joy.json.model.OrderSubmitEntity;
import com.joy.json.model.UpdateEntity;
import com.joy.json.operation.ITaskOperation;
import com.joy.json.parse.UpdateParse;

public class UpdateOp implements ITaskOperation {

	@Override
	public Object exec(Object in, Object res) throws Exception {
		String newVersion = in.toString();
<<<<<<< HEAD
		Log.d("0", "---newVersion = " + newVersion);
=======
>>>>>>> 34acdd014449076be19c67258f14caec9568e50d
		DefaultHttpClient httpClient = AbstractHttpApi.createHttpClient();
		httpClient.getParams().setParameter(
				HttpConnectionParams.CONNECTION_TIMEOUT, TIMEOUT);
		httpClient.getParams().setParameter(HttpConnectionParams.SO_TIMEOUT,
				TIMEOUT);
		HttpApi httpApi = new HttpApiWithBasicAuth(httpClient, "testRest");
		HttpGet get = httpApi.createHttpGet(
				IP,
				new BasicNameValuePair("action", "app_version"),
				new BasicNameValuePair("json", String.format(
						"{\"loginname\":\"%s\", \"currentappversion\":\"%s\", \"devicetype\":\"%s\"}", SharedPreferencesUtils
						.getLoginName(JoyApplication.getSelf())
							  , newVersion, "android")),
				new BasicNameValuePair("token", new MD5()
						.getMD5ofStr(SharedPreferencesUtils
								.getLoginName(JoyApplication.getSelf())
								+ MD5.key)));
		return (UpdateEntity) httpApi.doHttpRequest(get, new UpdateParse());
	}
}

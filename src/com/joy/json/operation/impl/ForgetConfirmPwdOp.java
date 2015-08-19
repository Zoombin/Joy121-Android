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
import com.joy.json.model.BindMobileEntity;
import com.joy.json.operation.ITaskOperation;
import com.joy.json.parse.BindMobileParse;
/**
 *忘记密码中提交新密码验证码
 * @author rainbow
*/
public class ForgetConfirmPwdOp implements ITaskOperation{
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
							new BasicNameValuePair("action", "comfirmmodifypassword"),
							new BasicNameValuePair(
									"json",
									String.format(
											"{\"loginname\":\"%s\",\"verifycode\":\"%s\",\"newpassword\":\"%s\"}",
											entity.getLoginName(), entity
											.getsecuritycode(), entity
											.getNewPwd())),
					new BasicNameValuePair("token", new MD5()
							.getMD5ofStr(SharedPreferencesUtils
									.getLoginName(JoyApplication
											.getSelf()) + MD5.key)));
			return (BindMobileEntity) httpApi.doHttpRequest(get,
					new BindMobileParse());
	}
	
}

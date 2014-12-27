package com.joy.json.operation.impl;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;

import com.joy.JoyApplication;
import com.joy.Utils.MD5;
import com.joy.Utils.SharedPreferencesUtils;
import com.joy.json.http.AbstractHttpApi;
import com.joy.json.http.HttpApi;
import com.joy.json.http.HttpApiWithBasicAuth;
import com.joy.json.model.AttendanceEntity;
import com.joy.json.model.AttendanceListEntity;
import com.joy.json.operation.ITaskOperation;
import com.joy.json.parse.AttendanceParse;

/**
 * 活动报名
 * @author ryan zhou 2014-12-12
 *
 */
public class AttendancePunchOp implements ITaskOperation {

	@Override
	public Object exec(Object in, Object res) throws Exception {
		AttendanceEntity entity = (AttendanceEntity) in;
		DefaultHttpClient httpClient = AbstractHttpApi.createHttpClient();
		httpClient.getParams().setParameter(
				HttpConnectionParams.CONNECTION_TIMEOUT, TIMEOUT);
		httpClient.getParams().setParameter(HttpConnectionParams.SO_TIMEOUT,
				TIMEOUT);
		HttpApi httpApi = new HttpApiWithBasicAuth(httpClient, "testRest");
		
		HttpGet get = null;
		get = httpApi.createHttpGet(
				IP,
				new BasicNameValuePair("action", "punch"),
				new BasicNameValuePair("json", String.format(
						"{\"loginname\":\"%s\",\"puntchType\":\"%s\",\"longitude\":\"%s\",\"latitude\":\"%s\"}", SharedPreferencesUtils
								.getLoginName(JoyApplication.getSelf()), entity.getPunchType(), entity.getLongitude(), entity.getLatitude())),
								new BasicNameValuePair("token", new MD5()
								.getMD5ofStr(SharedPreferencesUtils
										.getLoginName(JoyApplication.getSelf())
										+ MD5.key)));
		return (AttendanceListEntity) httpApi.doHttpRequest(get, new AttendanceParse());
	}
}

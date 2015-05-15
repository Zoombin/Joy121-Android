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
import com.joy.json.model.CategoryEntity;
import com.joy.json.model.ContactListEntity;
import com.joy.json.model.PayrollListEntity;
import com.joy.json.model.PerformanceListEntity;
import com.joy.json.model.RosterEntity;
import com.joy.json.model.RosterListEntity;
import com.joy.json.operation.ITaskOperation;
import com.joy.json.parse.ContactListParse;
import com.joy.json.parse.PayrollListParse;
import com.joy.json.parse.PerformanceListParse;
import com.joy.json.parse.RosterListParse;
/**
 * 联系人列表
 * @author ryan zhou 2014－11-13
 *
 */
public class RosterOp implements ITaskOperation {
	
	
	public RosterOp() {
	}

	@Override
	public Object exec(Object in, Object res) throws Exception {
		String reportCaseId = in.toString();
		DefaultHttpClient httpClient = AbstractHttpApi.createHttpClient();
		httpClient.getParams().setParameter(
				HttpConnectionParams.CONNECTION_TIMEOUT, TIMEOUT);
		httpClient.getParams().setParameter(HttpConnectionParams.SO_TIMEOUT,
				TIMEOUT);
		HttpApi httpApi = new HttpApiWithBasicAuth(httpClient, "testRest");
		HttpGet get = httpApi.createHttpGet(
				IP,
				new BasicNameValuePair("action", "comp_performance_detail"),
				new BasicNameValuePair("json", String.format(
						"{\"loginname\":\"%s\",\"reportcaseid\":\"%s\"}", SharedPreferencesUtils
						.getLoginName(JoyApplication.getSelf()), reportCaseId)),
				new BasicNameValuePair("token", new MD5()
						.getMD5ofStr(SharedPreferencesUtils
								.getLoginName(JoyApplication.getSelf())
								+ MD5.key)));
		return (RosterListEntity) httpApi.doHttpRequest(get, new RosterListParse());
	}
}





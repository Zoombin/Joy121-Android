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
import com.joy.json.model.ContactListEntity;
import com.joy.json.operation.ITaskOperation;
import com.joy.json.parse.ContactListParse;
/**
 * 联系人列表
 * @author ryan zhou 2014－10-31
 *
 */
public class ContactOp implements ITaskOperation {
	
	
	private String qvalue;
	private String pageSize;
	private String pageNum;
	public ContactOp(String qvalue, int pageSize, int pageNum) {
		this.qvalue = qvalue;
		this.pageSize = pageSize + "";
		this.pageNum = pageNum + "";
	}

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
				new BasicNameValuePair("action", "comp_personinfos"),
				new BasicNameValuePair("json", String.format(
						"{\"loginname\":\"%s\",\"qvalue\":\"%s\",\"pagesize\":\"%s\",\"pagenum\":\"%s\"}", "310225198112162465"
						, qvalue.trim(), pageSize, pageNum)),
								new BasicNameValuePair("token", new MD5()
								.getMD5ofStr(SharedPreferencesUtils
										.getLoginName(JoyApplication.getSelf())
										+ MD5.key)));
		return (ContactListEntity) httpApi.doHttpRequest(get, new ContactListParse());
	}
}

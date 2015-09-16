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
import com.joy.json.model.RelationContactListEntity;
import com.joy.json.operation.ITaskOperation;
import com.joy.json.parse.ContactListParse;
import com.joy.json.parse.RelationContactListParse;

/**
 * 重要联系人列表
 * @author rainbow 2015－9-15
 *
 */
public class RelationContactOp implements ITaskOperation{
	@Override
	public Object exec(Object in, Object res) throws Exception {
		DefaultHttpClient httpClient = AbstractHttpApi.createHttpClient();
		httpClient.getParams().setParameter(
				HttpConnectionParams.CONNECTION_TIMEOUT, TIMEOUT);
		httpClient.getParams().setParameter(HttpConnectionParams.SO_TIMEOUT,
				TIMEOUT);
		HttpApi httpApi = new HttpApiWithBasicAuth(httpClient, "testRest");
		HttpGet get = httpApi.createHttpGet(
				relationContacts,
				new BasicNameValuePair("loginName", SharedPreferencesUtils
						.getLoginName(JoyApplication.getSelf()))
				);
		return (RelationContactListEntity) httpApi.doHttpRequest(get, new RelationContactListParse());
	}

}

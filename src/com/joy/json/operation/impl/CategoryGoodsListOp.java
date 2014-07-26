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
import com.joy.json.model.CategoriesGoodsDEntity;
import com.joy.json.model.CategoriesStoreEntity;
import com.joy.json.operation.ITaskOperation;
import com.joy.json.parse.CategoryGoodsParse;

public class CategoryGoodsListOp implements ITaskOperation {
	String categoryid;
	public CategoryGoodsListOp(String categoryid) {
		// TODO Auto-generated constructor stub
		this.categoryid = categoryid;
	}

	@Override
	public Object exec(Object in, Object res) throws Exception {
		//CategoriesGoodsEntity entity = (CategoriesGoodsEntity) in;
		DefaultHttpClient httpClient = AbstractHttpApi.createHttpClient();
		httpClient.getParams().setParameter(
				HttpConnectionParams.CONNECTION_TIMEOUT, TIMEOUT);
		httpClient.getParams().setParameter(HttpConnectionParams.SO_TIMEOUT,
				TIMEOUT);
		HttpApi httpApi = new HttpApiWithBasicAuth(httpClient, "testRest");
		HttpGet get = httpApi.createHttpGet(
				IP,
				new BasicNameValuePair("action", "comm_list"),
				new BasicNameValuePair("json", String.format(
						"{\"loginname\":\"%s\",\"company\":\"%s\",\"categorytype\":\"%s\",\"categoryid\":\"%s\"}", SharedPreferencesUtils
								.getLoginName(JoyApplication.getSelf()),SharedPreferencesUtils
								.getCompany(JoyApplication.getSelf()),2,categoryid)),
								new BasicNameValuePair("token", new MD5()
								.getMD5ofStr(SharedPreferencesUtils
										.getLoginName(JoyApplication.getSelf())
										+ MD5.key)));
		return (CategoriesGoodsDEntity) httpApi.doHttpRequest(get, new CategoryGoodsParse());
	}
}

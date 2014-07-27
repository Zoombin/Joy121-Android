package com.joy.json.operation.impl;

import java.util.List;

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
import com.joy.json.model.CategoriesStoreEntity;
import com.joy.json.model.CommitResultEntity;
import com.joy.json.model.ShoppingCarGoods;
import com.joy.json.operation.ITaskOperation;
import com.joy.json.parse.CategoryGoodsParse;
import com.joy.json.parse.CategoryStoreParse;
import com.joy.json.parse.CommitCarShppParse;

public class CommitShopCarOp implements ITaskOperation {
	List<ShoppingCarGoods> carGoods;

	public CommitShopCarOp(List<ShoppingCarGoods> carGoods) {
		// TODO Auto-generated constructor stub
		this.carGoods = carGoods;
	}

	@Override
	public Object exec(Object in, Object res) throws Exception {
		// CategoriesGoodsEntity entity = (CategoriesGoodsEntity) in;
		DefaultHttpClient httpClient = AbstractHttpApi.createHttpClient();
		httpClient.getParams().setParameter(HttpConnectionParams.CONNECTION_TIMEOUT, TIMEOUT);
		httpClient.getParams().setParameter(HttpConnectionParams.SO_TIMEOUT, TIMEOUT);
		HttpApi httpApi = new HttpApiWithBasicAuth(httpClient, "testRest");
		HttpGet get = httpApi
				.createHttpGet(
						IP,
						new BasicNameValuePair("action", "order_submit"),
						new BasicNameValuePair(
								"json",
								String.format(
										"{\"loginname\":\"%s\",\"cartlist\":\"%s\",\"receiver\":\"%s\",\"recadd\":\"%s\",\"recphone\":\"%s\"}",
										SharedPreferencesUtils.getLoginName(JoyApplication.getSelf()), getJsonStr(),"","","")),
						new BasicNameValuePair("token", new MD5().getMD5ofStr(SharedPreferencesUtils
								.getLoginName(JoyApplication.getSelf()) + MD5.key)));
		return (CommitResultEntity) httpApi.doHttpRequest(get, new CommitCarShppParse());
	}

	private String getJsonStr() {
		StringBuffer sBuffer = new StringBuffer("");
		if (carGoods != null && carGoods.size() > 0) {
			for (int i = 0; i < carGoods.size(); i++) {
				ShoppingCarGoods gd = carGoods.get(i);
				String temp = "";
				if (gd.getIsLogoStore()) {
					temp = "[" + gd.getGoods_id() + "]" + "[color:" + gd.getColor() + ";" + "size_cloth:"
							+ gd.getSize_cloth() + "]" + "[" + gd.getGoodsType() + "]" + "[" + gd.getCount() + "]";
					sBuffer.append(temp);
				} else {
					temp = "[" + gd.getGoods_id() + "]" + "[]" + "[" + gd.getGoodsParams() + "]" + "[" + gd.getCount() + "]";
					sBuffer.append(temp);
				}
				
				if (i != carGoods.size() - 1) {
					sBuffer.append("|");
				}
			}
		}
		Log.d("Result", sBuffer.toString());
		Log.i("LSD", sBuffer.toString());
		return sBuffer.toString();

		/*
		 * 商品提交：
		 * 
		 * loginname,cartlist([商品id][属性值][商品类别][商品数量]|[商品id][属性值][商品类别][商品数量]),
		 * receiver,recadd,recphone
		 * 
		 * 这个提交订单的，后面几个是什么参数？ receiver？recadd？recphone？
		 * 
		 * loginname:steven,cartlist:[123][color:黄色][1][2]|[123][color:黄色][1][2],
		 * receiver:”“,recadd:”“,recphone:““
		 * 
		 * 这里的123代表的是福利的id或者商品的id 那个属性如果没有就[]
		 * 
		 * 
		 * 1 福利和logo商店的东西都会放到购物车里 1 查看：推送的接口
		 * 
		 * {{{userKV}}, "cartlist":[3294][color:红色;size_trousers:29][102][1],
		 * "receiver":"", "recadd":"", "recphone":""} ％德芙 7:19:03
		 * 
		 * 
		 * 提交时候就把选择的属性的一起提交上去 loginname:steven,cartlist:[123][color:黄色][1][2] |
		 * [123][color:黄色][1][2],receiver:”“,recadd:”“,recphone:““
		 * 
		 * 后面三个参数都传空字符就行了
		 * 
		 * 这里的123代表的是商品的id 第二个[]里面是属性 第三个[]固定值是1 第四个[]是商品数量
		 * 
		 * 
		 * 最后 还有福利里的东西也要放到购物车中，提交的时候也用这个格式 第一个[]是福利id 第二个[]是空 第三个[]是固定值2
		 * 第四个[]是固定值1（一个人只能选一个福利）
		 */
	}
}

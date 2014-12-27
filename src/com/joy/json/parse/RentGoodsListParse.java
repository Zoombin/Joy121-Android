package com.joy.json.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.google.gson.Gson;
import com.joy.json.model.ContactListEntity;
import com.joy.json.model.RentGoodsListEntity;
import com.joy.json.model.TResult;
/**
 * 联系人列表
 * @author ryan zhou 2014－10-31
 *
 */
public class RentGoodsListParse extends AbstractParser<TResult> {
	
	@Override
	public RentGoodsListEntity parse(JSONObject json) throws JSONException {
		RentGoodsListEntity entity = new RentGoodsListEntity();
		if(json!=null){
			Log.d("0", "------json = " + json.toString());
			entity = new Gson().fromJson(
					 json.toString(), RentGoodsListEntity.class);
		} else {
			Log.d("0", "------json = null");
		}
		return entity;
	}
	
	@Override
	public RentGoodsListEntity parseArray(JSONArray json) throws JSONException {
		return null;
	}
}

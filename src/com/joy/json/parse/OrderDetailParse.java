package com.joy.json.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.joy.json.model.OrderDetailEntity;
import com.joy.json.model.TResult;

public class OrderDetailParse extends AbstractParser<TResult> {
	
	@Override
	public OrderDetailEntity parse(JSONObject json) throws JSONException {
		OrderDetailEntity entity = new OrderDetailEntity();
		if(json!=null){ 
			entity = new Gson().fromJson(
					 json.toString(), OrderDetailEntity.class);
		}
		return entity;
	}
	
	@Override
	public OrderDetailEntity parseArray(JSONArray json) throws JSONException {
		return null;
	}


}

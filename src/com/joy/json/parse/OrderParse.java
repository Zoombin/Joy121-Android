package com.joy.json.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.joy.json.model.OrderEntity;
import com.joy.json.model.TResult;

public class OrderParse extends AbstractParser<TResult> {
	
	@Override
	public OrderEntity parse(JSONObject json) throws JSONException {
		OrderEntity entity = new OrderEntity();
		if(json!=null){
			entity = new Gson().fromJson(
					 json.toString(), OrderEntity.class);
		}
		return entity;
	}
	
	@Override
	public OrderEntity parseArray(JSONArray json) throws JSONException {
		return null;
	}
}

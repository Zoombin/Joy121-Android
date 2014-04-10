package com.joy.json.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.joy.json.model.OrderSubmitEntity;
import com.joy.json.model.TResult;

public class OrderSubmitParse extends AbstractParser<TResult> {
	
	@Override
	public OrderSubmitEntity parse(JSONObject json) throws JSONException {
		OrderSubmitEntity login = new OrderSubmitEntity();
		if(json!=null){ 
			login = new Gson().fromJson(
					 json.toString(), OrderSubmitEntity.class);
		}
		return login;
	}
	
	@Override
	public OrderSubmitEntity parseArray(JSONArray json) throws JSONException {
		return null;
	}
}

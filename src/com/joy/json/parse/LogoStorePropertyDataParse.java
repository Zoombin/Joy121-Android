package com.joy.json.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.joy.json.model.LogoStorePropertyDataDetailEntity;
import com.joy.json.model.LogoStorePropertyDataEntity;
import com.joy.json.model.TResult;

public class LogoStorePropertyDataParse extends AbstractParser<TResult> {
	
	@Override
	public LogoStorePropertyDataEntity parse(JSONObject json) throws JSONException {
		LogoStorePropertyDataEntity entity = new LogoStorePropertyDataEntity();
		if(json!=null){
			entity = new Gson().fromJson(
					 json.toString(), LogoStorePropertyDataEntity.class);
		}
		return entity;
	}
	@Override
	public LogoStorePropertyDataEntity parseArray(JSONArray json) throws JSONException {
		return null;
	}

}

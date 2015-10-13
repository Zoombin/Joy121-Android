package com.joy.json.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.joy.json.model.PointEntity;
import com.joy.json.model.TResult;

public class PointParse extends AbstractParser<TResult> {
	
	@Override
	public PointEntity parse(JSONObject json) throws JSONException {
		PointEntity entity = new PointEntity();
		if(json!=null){
			entity = new Gson().fromJson(
					 json.toString(), PointEntity.class);
		}
		return entity;
	}
	
	@Override
	public PointEntity parseArray(JSONArray json) throws JSONException {
		return null;
	}

}

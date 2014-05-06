package com.joy.json.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.joy.json.model.ActjoinEntity;
import com.joy.json.model.TResult;

/**
 * 活动报名
 * @author daiye
 *
 */
public class ActjoinParse extends AbstractParser<TResult> {
	
	@Override
	public ActjoinEntity parse(JSONObject json) throws JSONException {
		ActjoinEntity entity = new ActjoinEntity();
		if(json!=null){
			entity = new Gson().fromJson(
					 json.toString(), ActjoinEntity.class);
		}
		return entity;
	}
	
	@Override
	public ActjoinEntity parseArray(JSONArray json) throws JSONException {
		return null;
	}
}

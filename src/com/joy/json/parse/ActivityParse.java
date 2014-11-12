package com.joy.json.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.joy.json.model.ActivityEntity;
import com.joy.json.model.TResult;

/**
 * 活动列表
 * @author daiye
 *
 */
public class ActivityParse extends AbstractParser<TResult> {
	
	@Override
	public ActivityEntity parse(JSONObject json) throws JSONException {
		ActivityEntity entity = new ActivityEntity();
		if(json!=null){
			entity = new Gson().fromJson(
					 json.toString(), ActivityEntity.class);
		}
		return entity;
	}
	
	@Override
	public ActivityEntity parseArray(JSONArray json) throws JSONException {
		return null;
	}
}

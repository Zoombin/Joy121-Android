package com.joy.json.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.joy.json.model.ActivityEntity;
import com.joy.json.model.CommitResultEntity;
import com.joy.json.model.TResult;

public class CommitCarShppParse extends AbstractParser<TResult> {
	
	@Override
	public CommitResultEntity parse(JSONObject json) throws JSONException {
		CommitResultEntity entity = new CommitResultEntity();
		//PLog.e("back Result-->%s", json.toString());
		if(json!=null){
			entity = new Gson().fromJson(
					 json.toString(), CommitResultEntity.class);
		}
		return entity;
	}
	
	@Override
	public ActivityEntity parseArray(JSONArray json) throws JSONException {
		return null;
	}
}

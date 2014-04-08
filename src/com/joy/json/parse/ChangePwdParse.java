package com.joy.json.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.joy.json.model.ChangePwdEntity;
import com.joy.json.model.TResult;

public class ChangePwdParse extends AbstractParser<TResult> {
	
	@Override
	public ChangePwdEntity parse(JSONObject json) throws JSONException {
		ChangePwdEntity entity = new ChangePwdEntity();
		if(json!=null){
			entity = new Gson().fromJson(
					 json.toString(), ChangePwdEntity.class);
		}
		return entity;
	}
	
	@Override
	public ChangePwdEntity parseArray(JSONArray json) throws JSONException {
		return null;
	}
}

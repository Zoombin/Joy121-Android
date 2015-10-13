package com.joy.json.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.joy.json.model.TResult;
import com.joy.json.model.UserEntity;

public class UserinfoParse extends AbstractParser<TResult> {
	
	@Override
	public UserEntity parse(JSONObject json) throws JSONException {
		UserEntity user = new UserEntity();
		if(json!=null){ 
			user = new Gson().fromJson(
					 json.toString(), UserEntity.class);
		}
		return user;
	}
	
	@Override
	public UserEntity parseArray(JSONArray json) throws JSONException {
		return null;
	}

}

package com.joy.json.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.joy.json.model.LoginEntity;
import com.joy.json.model.TResult;

public class LoginParse extends AbstractParser<TResult> {
	
	@Override
	public LoginEntity parse(JSONObject json) throws JSONException {
		LoginEntity login = new LoginEntity();
		if(json!=null){ 
			login = new Gson().fromJson(
					 json.toString(), LoginEntity.class);
		}
		return login;
	}
	
	@Override
	public LoginEntity parseArray(JSONArray json) throws JSONException {
		return null;
	}
}

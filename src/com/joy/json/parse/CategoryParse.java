package com.joy.json.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.joy.json.model.ActivityEntity;
import com.joy.json.model.CategoryEntity;
import com.joy.json.model.TResult;

public class CategoryParse extends AbstractParser<TResult> {
	
	@Override
	public CategoryEntity parse(JSONObject json) throws JSONException {
		CategoryEntity entity = new CategoryEntity();
		if(json!=null){
			entity = new Gson().fromJson(
					 json.toString(), CategoryEntity.class);
		}
		return entity;
	}
	
	@Override
	public ActivityEntity parseArray(JSONArray json) throws JSONException {
		return null;
	}


}

package com.joy.json.parse;

import gejw.android.quickandroid.log.PLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.joy.json.model.ActivityEntity;
import com.joy.json.model.CategoriesGoodsDEntity;
import com.joy.json.model.TResult;

public class CategoryGoodsParse extends AbstractParser<TResult> {
	
	@Override
	public CategoriesGoodsDEntity parse(JSONObject json) throws JSONException {
		CategoriesGoodsDEntity entity = new CategoriesGoodsDEntity();
		//PLog.e("back Result-->%s", json.toString());
		if(json!=null){
			entity = new Gson().fromJson(
					 json.toString(), CategoriesGoodsDEntity.class);
		}
		return entity;
	}
	
	@Override
	public ActivityEntity parseArray(JSONArray json) throws JSONException {
		return null;
	}
}

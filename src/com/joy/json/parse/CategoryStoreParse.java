package com.joy.json.parse;

import gejw.android.quickandroid.log.PLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.joy.json.model.ActivityEntity;
import com.joy.json.model.CategoriesStoreEntity;
import com.joy.json.model.TResult;

public class CategoryStoreParse extends AbstractParser<TResult> {
	
	@Override
	public CategoriesStoreEntity parse(JSONObject json) throws JSONException {
		CategoriesStoreEntity entity = new CategoriesStoreEntity();
		PLog.e("back Result-->%s", json.toString());
		if(json!=null){
			entity = new Gson().fromJson(
					 json.toString(), CategoriesStoreEntity.class);
		}
		return entity;
	}
	
	@Override
	public ActivityEntity parseArray(JSONArray json) throws JSONException {
		return null;
	}
}

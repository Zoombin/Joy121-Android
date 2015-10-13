package com.joy.json.parse;

import gejw.android.quickandroid.log.PLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.joy.json.model.ActivityEntity;
import com.joy.json.model.StoreDetailEntity;
import com.joy.json.model.TResult;

public class CategoryStoreParse extends AbstractParser<TResult> {
	
	@Override
	public StoreDetailEntity parse(JSONObject json) throws JSONException {
		StoreDetailEntity entity = new StoreDetailEntity();
		//PLog.e("back Result-->%s", json.toString());
		if(json!=null){
			entity = new Gson().fromJson(
					 json.toString(), StoreDetailEntity.class);
		}
		return entity;
	}
	
	@Override
	public ActivityEntity parseArray(JSONArray json) throws JSONException {
		return null;
	}

}

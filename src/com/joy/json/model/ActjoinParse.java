package com.joy.json.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.google.gson.Gson;
import com.joy.json.model.ActjoinEntity;
import com.joy.json.model.TResult;
import com.joy.json.parse.AbstractParser;

/**
 * 活动报名
 * @author daiye
 *
 */
public class ActjoinParse extends AbstractParser<TResult> {
	
	@Override
	public ActjoinEntity parse(JSONObject json) throws JSONException {
		//Log.i("LSD", json.toString());
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

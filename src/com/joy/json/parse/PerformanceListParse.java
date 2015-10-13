package com.joy.json.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.google.gson.Gson;
import com.joy.json.model.PerformanceListEntity;
import com.joy.json.model.TResult;
/**
 * 激励&绩效api对象
 * @author ryan zhou 2014－10-31
 *
 */
public class PerformanceListParse extends AbstractParser<TResult> {
	
	@Override
	public PerformanceListEntity parse(JSONObject json) throws JSONException {
		PerformanceListEntity entity = new PerformanceListEntity();
		Log.d("0", json.toString());
		if(json!=null){
			entity = new Gson().fromJson(
					 json.toString(), PerformanceListEntity.class);
		}
		return entity;
	}
	
	@Override
	public PerformanceListEntity parseArray(JSONArray json) throws JSONException {
		return null;
	}

}

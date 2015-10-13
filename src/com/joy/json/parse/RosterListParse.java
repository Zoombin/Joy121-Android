package com.joy.json.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.google.gson.Gson;
import com.joy.json.model.PerformanceListEntity;
import com.joy.json.model.RosterListEntity;
import com.joy.json.model.TResult;
/**
 * 激励&绩效api对象
 * @author ryan zhou 2014－10-31
 *
 */
public class RosterListParse extends AbstractParser<TResult> {
	
	@Override
	public RosterListEntity parse(JSONObject json) throws JSONException {
		RosterListEntity entity = new RosterListEntity();
		Log.d("0", "roster api ＝ " + json.toString());
		if(json!=null){
			entity = new Gson().fromJson(
					 json.toString(), RosterListEntity.class);
		}
		
		return entity;
	}
	
	@Override
	public RosterListEntity parseArray(JSONArray json) throws JSONException {
		return null;
	}


}

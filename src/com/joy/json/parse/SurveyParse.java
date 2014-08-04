package com.joy.json.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.google.gson.Gson;
import com.joy.json.model.SurveyEntity;
import com.joy.json.model.TResult;

/**
 * 调查列表
 * @author daiye
 *
 */
public class SurveyParse extends AbstractParser<TResult> {
	
	@Override
	public SurveyEntity parse(JSONObject json) throws JSONException {
		Log.i("LSD", json.toString());
		SurveyEntity entity = new SurveyEntity();
		if(json!=null){
			entity = new Gson().fromJson(
					 json.toString(), SurveyEntity.class);
		}
		return entity;
	}
	
	@Override
	public SurveyEntity parseArray(JSONArray json) throws JSONException {
		return null;
	}
}

package com.joy.json.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.google.gson.Gson;
import com.joy.json.model.SurveyAEntity;
import com.joy.json.model.TResult;

/**
 * 调查投票
 * @author daiye
 *
 */
public class SurveyAParse extends AbstractParser<TResult> {
	
	@Override
	public SurveyAEntity parse(JSONObject json) throws JSONException {
		SurveyAEntity entity = new SurveyAEntity();
		if(json!=null){
			Log.i("LSD", json.toString());
			entity = new Gson().fromJson(
					 json.toString(), SurveyAEntity.class);
		}
		return entity;
	}
	
	@Override
	public SurveyAEntity parseArray(JSONArray json) throws JSONException {
		return null;
	}

}

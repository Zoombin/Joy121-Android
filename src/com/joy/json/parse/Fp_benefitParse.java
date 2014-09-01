package com.joy.json.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.google.gson.Gson;
import com.joy.json.model.TResult;
import com.joy.json.model.WelfareEntity;

public class Fp_benefitParse extends AbstractParser<TResult> {
	
	@Override
	public WelfareEntity parse(JSONObject json) throws JSONException {
		//Log.i("LSD", json.toString());
		WelfareEntity entity = new WelfareEntity();
		if(json!=null){
			entity = new Gson().fromJson(
					 json.toString(), WelfareEntity.class);
		}
		return entity;
	}
	
	@Override
	public WelfareEntity parseArray(JSONArray json) throws JSONException {
		return null;
	}
}

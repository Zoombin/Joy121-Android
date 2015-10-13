package com.joy.json.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.google.gson.Gson;
import com.joy.json.model.TResult;
import com.joy.json.model.UpdateEntity;

public class UpdateParse extends AbstractParser<TResult> {
	
	@Override
	public UpdateEntity parse(JSONObject json) throws JSONException {
		UpdateEntity entity = new UpdateEntity();
		if(json != null){
			Log.d("0", json.toString());
			entity = new Gson().fromJson(
					 json.toString(), UpdateEntity.class);
		}
		return entity;
	}
	
	@Override
	public UpdateEntity parseArray(JSONArray json) throws JSONException {
		return null;
	}

}

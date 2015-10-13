package com.joy.json.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.google.gson.Gson;
import com.joy.json.model.ActivityEntity;
import com.joy.json.model.EntryManageEntity;
import com.joy.json.model.EntryUploadImageEntity;
import com.joy.json.model.TResult;

public class EntryUploadImageParse extends AbstractParser<TResult> {

	@Override
	public EntryUploadImageEntity parse(JSONObject json) throws JSONException {
		// TODO Auto-generated method stub
		Log.d("dfdfdsfsdfdfdfdfffffffffff", json.toString());
		EntryUploadImageEntity entity = new EntryUploadImageEntity();
		if(json!=null){
			entity = new Gson().fromJson(
					 json.toString(), EntryUploadImageEntity.class);
		}
		return entity;
	}
	@Override
	public EntryUploadImageEntity parseArray(JSONArray json) throws JSONException {
		// TODO Auto-generated method stub
		return null;
	}

}

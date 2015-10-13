package com.joy.json.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.google.gson.Gson;
import com.joy.json.model.EntryDepartmentEntity;
import com.joy.json.model.TResult;

public class EntryDepartmentParse  extends AbstractParser<TResult>{

	@Override
	public EntryDepartmentEntity parse(JSONObject json) throws JSONException {
		EntryDepartmentEntity entity=new EntryDepartmentEntity();
		Log.d("", json.toString());
		if(json!=null)
		{
			entity=new Gson().fromJson(
					 json.toString(), EntryDepartmentEntity.class);
		}
		return entity;
	}

	@Override
	public EntryDepartmentEntity parseArray(JSONArray json) throws JSONException {
		// TODO Auto-generated method stub
		return null;
	}

}
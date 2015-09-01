package com.joy.json.parse;

/**
 * rainbow 2015/8/19
 */
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.joy.json.model.EntryEntity;
import com.joy.json.model.TResult;

public class EntryManageParse  extends AbstractParser<TResult>{
	@Override
	public EntryEntity parse(JSONObject json) throws JSONException {
		EntryEntity entity=new EntryEntity();
		if(json!=null)
		{
			entity=new Gson().fromJson(
					 json.toString(), EntryEntity.class);//将josn转为对象
		}
		return entity;
	}

	@Override
	public EntryEntity parseArray(JSONArray json) throws JSONException {
		// TODO Auto-generated method stub
		return null;
	}
}

package com.joy.json.parse;

/**
 * rainbow 2015/8/19
 */
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.joy.json.model.EntryManageEntity;
import com.joy.json.model.TResult;

public class EntryManageParse  extends AbstractParser<TResult>{
	@Override
	public EntryManageEntity parse(JSONObject json) throws JSONException {
		EntryManageEntity entity=new EntryManageEntity();
		if(json!=null)
		{
			entity=new Gson().fromJson(
					 json.toString(), EntryManageEntity.class);
		}
		return entity;
	}

	@Override
	public EntryManageEntity parseArray(JSONArray json) throws JSONException {
		// TODO Auto-generated method stub
		return null;
	}
}

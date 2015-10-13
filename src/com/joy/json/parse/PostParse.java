package com.joy.json.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.google.gson.Gson;
import com.joy.json.model.PostEntity;
import com.joy.json.model.TResult;

/**
 * 公告
 * @author daiye
 *
 */
public class PostParse extends AbstractParser<TResult> {
	
	@Override
	public PostEntity parse(JSONObject json) throws JSONException {
		PostEntity entity = new PostEntity();
		if(json!=null){
			entity = new Gson().fromJson(
					 json.toString(), PostEntity.class);
		}
		return entity;
	}
	
	@Override
	public PostEntity parseArray(JSONArray json) throws JSONException {
		return null;
	}


}

package com.joy.json.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.joy.json.model.PicEntity;
import com.joy.json.model.TResult;

public class PicParse extends AbstractParser<TResult> {
	
	@Override
	public PicEntity parse(JSONObject json) throws JSONException {
		PicEntity pic = new PicEntity();
		if(json!=null){ 
			pic = new Gson().fromJson(
					 json.toString(), PicEntity.class);
		}
		return pic;
	}
	
	@Override
	public PicEntity parseArray(JSONArray json) throws JSONException {
		return null;
	}

}

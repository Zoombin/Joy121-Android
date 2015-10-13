package com.joy.json.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.joy.json.model.TResult;
import com.joy.json.model.BindMobileEntity;
import com.umeng.common.Log;
/**
 * @author rainbow
 */
public class BindMobileParse extends AbstractParser<TResult>{

	@Override
	public BindMobileEntity parse(JSONObject json) throws JSONException {
		BindMobileEntity entity=new BindMobileEntity();
		Log.d("0","----json = "+json.toString());
		if(json!=null)
		{
			entity=new Gson().fromJson(
					 json.toString(), BindMobileEntity.class);
		}
		return entity;
	}

	@Override
	public BindMobileEntity parseArray(JSONArray json) throws JSONException {
		// TODO Auto-generated method stub
		return null;
	}


	
}

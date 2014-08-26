package com.joy.json.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.google.gson.Gson;
import com.joy.json.model.PortalsModule;
import com.joy.json.model.TResult;

/**
 * 活动列表
 * 
 * @author daiye
 * 
 */
public class ModuleParse extends AbstractParser<TResult> {

	@Override
	public PortalsModule parse(JSONObject json) throws JSONException {
		PortalsModule entity = new PortalsModule();
		if (json != null) {
			//Log.i("LSD", json.toString());
			entity = new Gson().fromJson(json.toString(), PortalsModule.class);
		}
		return entity;
	}

	@Override
	public PortalsModule parseArray(JSONArray json) throws JSONException {
		return null;
	}
}

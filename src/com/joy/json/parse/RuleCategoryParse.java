package com.joy.json.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.joy.json.model.RuleCategorys;
import com.joy.json.model.TResult;

/**
 * 活动列表
 * 
 * @author ryan zhou 2014-12-21
 * 
 */
public class RuleCategoryParse extends AbstractParser<TResult> {

	@Override
	public RuleCategorys parse(JSONObject json) throws JSONException {
		RuleCategorys entity = new RuleCategorys();
		if (json != null) {
			//Log.i("LSD", json.toString());
			entity = new Gson().fromJson(json.toString(), RuleCategorys.class);
		}
		return entity;
	}

	@Override
	public RuleCategorys parseArray(JSONArray json) throws JSONException {
		return null;
	}
}

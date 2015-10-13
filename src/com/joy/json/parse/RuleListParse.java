package com.joy.json.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.joy.json.model.RuleCategorys;
import com.joy.json.model.RuleListEntity;
import com.joy.json.model.TResult;

/**
 * 活动列表
 * 
 * @author ryan zhou 2014-12-21
 * 
 */
public class RuleListParse extends AbstractParser<TResult> {

	@Override
	public RuleListEntity parse(JSONObject json) throws JSONException {
		RuleListEntity entity = new RuleListEntity();
		if (json != null) {
			entity = new Gson().fromJson(json.toString(), RuleListEntity.class);
		}
		return entity;
	}

	@Override
	public RuleListEntity parseArray(JSONArray json) throws JSONException {
		return null;
	}

}

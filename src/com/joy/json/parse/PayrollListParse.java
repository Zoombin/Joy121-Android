package com.joy.json.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.google.gson.Gson;
import com.joy.json.model.PayrollListEntity;
import com.joy.json.model.TResult;
/**
 * 工资单列表api对象
 * @author ryan zhou 2014－10-31
 *
 */
public class PayrollListParse extends AbstractParser<TResult> {
	
	@Override
	public PayrollListEntity parse(JSONObject json) throws JSONException {
		PayrollListEntity entity = new PayrollListEntity();
		if(json!=null){
			entity = new Gson().fromJson(
					 json.toString(), PayrollListEntity.class);
		}
		return entity;
	}
	
	@Override
	public PayrollListEntity parseArray(JSONArray json) throws JSONException {
		return null;
	}
}

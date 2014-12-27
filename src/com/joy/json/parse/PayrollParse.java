package com.joy.json.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.google.gson.Gson;
import com.joy.json.model.Payroll;
import com.joy.json.model.TResult;
/**
 * 工资单详细api对象
 * @author ryan zhou 2014－11-27
 *
 */
public class PayrollParse extends AbstractParser<TResult> {
	
	@Override
	public Payroll parse(JSONObject json) throws JSONException {
		Payroll entity = new Payroll();
		if(json!=null){
			entity = new Gson().fromJson(
					 json.toString(), Payroll.class);
		}
		return entity;
	}
	
	@Override
	public Payroll parseArray(JSONArray json) throws JSONException {
		return null;
	}
}

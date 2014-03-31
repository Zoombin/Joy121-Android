package com.joy.json.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.joy.json.model.CommoditySet;
import com.joy.json.model.TResult;

public class Fp_benefitParse extends AbstractParser<TResult> {
	
	@Override
	public CommoditySet parse(JSONObject json) throws JSONException {
		CommoditySet commoditySet = new CommoditySet();
		if(json!=null){
			commoditySet = new Gson().fromJson(
					 json.toString(), CommoditySet.class);
		}
		return commoditySet;
	}
	
	@Override
	public CommoditySet parseArray(JSONArray json) throws JSONException {
		return null;
	}
}

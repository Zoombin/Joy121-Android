package com.joy.json.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.joy.json.model.ContactListEntity;
import com.joy.json.model.TResult;
/**
 * 联系人列表
 * @author ryan zhou 2014－10-31
 *
 */
public class ContactListParse extends AbstractParser<TResult> {
	
	@Override
	public ContactListEntity parse(JSONObject json) throws JSONException {
		ContactListEntity entity = new ContactListEntity();
		if(json!=null){
			entity = new Gson().fromJson(
					 json.toString(), ContactListEntity.class);
		}
		return entity;
	}
	
	@Override
	public ContactListEntity parseArray(JSONArray json) throws JSONException {
		return null;
	}
}

package com.joy.json.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.joy.json.model.RelationContactListEntity;
import com.joy.json.model.TResult;

/**
 * 重要联系人列表
 * @author rainbow 2015－9-15
 *
 */
public class RelationContactListParse extends AbstractParser<TResult> {
	
	@Override
	public RelationContactListEntity parse(JSONObject json) throws JSONException {
		RelationContactListEntity entity = new RelationContactListEntity();
		if(json!=null){
			entity = new Gson().fromJson(
					 json.toString(), RelationContactListEntity.class);
		}
		return entity;
	}
	
	@Override
	public RelationContactListEntity parseArray(JSONArray json) throws JSONException {
		return null;
	}
}

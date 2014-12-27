package com.joy.json.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.google.gson.Gson;
import com.joy.json.model.ActjoinEntity;
import com.joy.json.model.AttendanceListEntity;
import com.joy.json.model.TResult;

/**
 * 活动报名
 * 
 * @author ryan zhou 2014-12-11
 * 
 */
public class AttendanceParse extends AbstractParser<TResult> {

	@Override
	public AttendanceListEntity parse(JSONObject json) throws JSONException {
		// Log.i("LSD", json.toString());
		AttendanceListEntity entity = new AttendanceListEntity();
		if (json != null) {
			entity = new Gson().fromJson(json.toString(),
					AttendanceListEntity.class);
		}
		return entity;
	}

	@Override
	public ActjoinEntity parseArray(JSONArray json) throws JSONException {
		return null;
	}
}

package com.joy.json.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.joy.json.util.JsonType;


public interface Parser<T extends JsonType> {

    public abstract T parse(JSONObject json) throws JSONException;
    
    public abstract T parseArray(JSONArray json)throws JSONException;
}

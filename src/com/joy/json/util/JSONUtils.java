package com.joy.json.util;

import java.io.IOException;
import java.security.acl.Group;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.joy.json.exception.JsonCredentialsException;
import com.joy.json.exception.JsonException;
import com.joy.json.exception.JsonParseException;
import com.joy.json.parse.Parser;


public class JSONUtils {
    
    private static final boolean DEBUG = false;
    private static final Logger LOG = Logger.getLogger(Group.class.getCanonicalName());
    
    /**
     * Takes a parser, a json string, and returns a jsontype.
     */
    public static JsonType consume(Parser<? extends JsonType> parser, String content)
        throws JsonCredentialsException, JsonParseException, JsonException, IOException {
        
        if (DEBUG) {
            LOG.log(Level.FINE, "http response: " + content);
        } 
        
        try {       
        	if("{".equals(content.substring(0,1))){
        		JSONObject json = new JSONObject(content);
        		return parser.parse(json);
        	}else{
        		JSONArray json = new JSONArray(content);
        		return parser.parseArray(json);
        	}
        } catch (JSONException ex) {
            throw new JsonException("Error parsing JSON response: " + ex.getMessage());
        }
    }
}
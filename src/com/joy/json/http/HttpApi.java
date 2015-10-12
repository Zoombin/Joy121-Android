package com.joy.json.http;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.mime.MultipartEntity;
import org.json.JSONObject;

import com.joy.json.exception.JsonCredentialsException;
import com.joy.json.exception.JsonException;
import com.joy.json.exception.JsonParseException;
import com.joy.json.parse.Parser;
import com.joy.json.util.JsonType;


public interface HttpApi {

    abstract public JsonType doHttpRequest(HttpRequestBase httpRequest,
            Parser<? extends JsonType> parser) throws JsonCredentialsException,
            JsonParseException, JsonException, IOException;

    abstract public String doHttpPost(String url,JSONObject jsonObject)
            throws JsonCredentialsException, JsonParseException, JsonException,
            IOException;

    abstract public HttpGet createHttpGet(String url, NameValuePair... nameValuePairs);

    abstract public HttpPost createHttpPost(String url,NameValuePair... nameValuePairs);
    
    abstract public HttpPost createHttpPost(String url, MultipartEntity mutiEntity);
    
    abstract public HttpPut createHttpPut(String url,JSONObject jsonObject);
    
    abstract public HttpDelete createHttpDlete(String url,NameValuePair... nameValuePairs);
    
    abstract public HttpURLConnection createHttpURLConnectionPost(URL url, String boundary)
            throws IOException; 
}

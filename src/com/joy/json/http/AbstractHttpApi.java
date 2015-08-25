package com.joy.json.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.util.Log;

import com.joy.json.exception.JsonCredentialsException;
import com.joy.json.exception.JsonException;
import com.joy.json.exception.JsonParseException;
import com.joy.json.parse.Parser;
import com.joy.json.util.JSONUtils;
import com.joy.json.util.JsonType;


abstract public class AbstractHttpApi implements HttpApi {
    protected static final Logger LOG = Logger.getLogger(AbstractHttpApi.class.getCanonicalName());
    protected static final boolean DEBUG = false;

    private static final String DEFAULT_CLIENT_VERSION = "com.test.json";
    private static final String CLIENT_VERSION_HEADER = "User-Agent";
    private static final int TIMEOUT = 30;

    private final DefaultHttpClient mHttpClient;
    private final String mClientVersion;

    public AbstractHttpApi(DefaultHttpClient httpClient, String clientVersion) {
        mHttpClient = httpClient;
        if (clientVersion != null) {
            mClientVersion = clientVersion;
        } else {
            mClientVersion = DEFAULT_CLIENT_VERSION;
        }
    }

    public JsonType executeHttpRequest(HttpRequestBase httpRequest,
            Parser<? extends JsonType> parser) throws JsonCredentialsException,
            JsonParseException, JsonException, IOException {
        if (DEBUG) LOG.log(Level.FINE, "doHttpRequest: " + httpRequest.getURI());
        Log.d("---------"+Level.FINE, "doHttpRequest: " + httpRequest.getURI());

        HttpResponse response = executeHttpRequest(httpRequest);
        if (DEBUG) LOG.log(Level.FINE, "executed HttpRequest for: "
                + httpRequest.getURI().toString());
        Log.d("---------"+Level.FINE, "executed HttpRequest for: "
                + httpRequest.getURI().toString());
        
        int statusCode = response.getStatusLine().getStatusCode();
        Log.d("---------", statusCode+"");
        switch (statusCode) {
            case 200:
                String content = EntityUtils.toString(response.getEntity(),HTTP.UTF_8);
                if (content == null || content.equals("")) {
                	return null;
                } else {
                	return JSONUtils.consume(parser, content);
                }
            case 400:
                if (DEBUG) LOG.log(Level.FINE, "HTTP Code: 400");
                throw new JsonException(
                        response.getStatusLine().toString(),
                        EntityUtils.toString(response.getEntity()));

            case 401:
                response.getEntity().consumeContent();
                if (DEBUG) LOG.log(Level.FINE, "HTTP Code: 401");
                throw new JsonCredentialsException(response.getStatusLine().toString());

            case 404:
                response.getEntity().consumeContent();
                if (DEBUG) LOG.log(Level.FINE, "HTTP Code: 404");
                throw new JsonException(response.getStatusLine().toString());

            case 500:
                response.getEntity().consumeContent();
                if (DEBUG) LOG.log(Level.FINE, "HTTP Code: 500");
                throw new JsonException("Foursquare is down. Try again later.");

            default:
                if (DEBUG) LOG.log(Level.FINE, "Default case for status code reached: "
                        + response.getStatusLine().toString());
                response.getEntity().consumeContent();
                throw new JsonException("Error connecting to Foursquare: " + statusCode + ". Try again later.");
        }
    }

    public String doHttpPost(String url,  NameValuePair... nameValuePairs)
            throws JsonCredentialsException, JsonParseException, JsonException,
            IOException {
        if (DEBUG) LOG.log(Level.FINE, "doHttpPost: " + url);
        HttpPost httpPost = createHttpPost(url,nameValuePairs );

        HttpResponse response = executeHttpRequest(httpPost);
        if (DEBUG) LOG.log(Level.FINE, "executed HttpRequest for: " + httpPost.getURI().toString());

        switch (response.getStatusLine().getStatusCode()) {
            case 200:
                try {
                    return EntityUtils.toString(response.getEntity());
                } catch (ParseException e) {
                    throw new JsonParseException(e.getMessage());
                }

            case 401:
                response.getEntity().consumeContent();
                throw new JsonCredentialsException(response.getStatusLine().toString());

            case 404:
                response.getEntity().consumeContent();
                throw new JsonException(response.getStatusLine().toString());

            default:
                response.getEntity().consumeContent();
                throw new JsonException(response.getStatusLine().toString());
        }
    }

    /**
     * execute() an httpRequest catching exceptions and returning null instead.
     *
     * @param httpRequest
     * @return
     * @throws IOException
     */
    public HttpResponse executeHttpRequest(HttpRequestBase httpRequest) throws IOException {
        if (DEBUG) LOG.log(Level.FINE, "executing HttpRequest for: "
                + httpRequest.getURI().toString());
        try {
            mHttpClient.getConnectionManager().closeExpiredConnections();
            return mHttpClient.execute(httpRequest);
        } catch (IOException e) {
            httpRequest.abort();
            throw e;
        }
    }

    public HttpGet createHttpGet(String url, NameValuePair... nameValuePairs) {
        if (DEBUG) LOG.log(Level.FINE, "creating HttpGet for: " + url);
       // String query = MegerParam(stripNulls(nameValuePairs));
        String 	query = URLEncodedUtils.format(stripNulls(nameValuePairs),HTTP.UTF_8);
        HttpGet httpGet = new HttpGet(url +"?"+query);
        httpGet.setHeader("accept","application/json");
        httpGet.setHeader("Content-type","application/json");
        httpGet.setHeader("ccept-Encoding","UTF-8");
        httpGet.addHeader(CLIENT_VERSION_HEADER, mClientVersion);
        if (DEBUG) LOG.log(Level.FINE, "Created: " + httpGet.getURI());
        return httpGet;
    }

    public HttpPost createHttpPost(String url, NameValuePair... nameValuePairs) {
        if (DEBUG) LOG.log(Level.FINE, "creating HttpPost for: " + url);
        UrlEncodedFormEntity endity=null ;
        try {
			 endity = new UrlEncodedFormEntity(stripNulls(nameValuePairs), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(endity);
        httpPost.addHeader(CLIENT_VERSION_HEADER, mClientVersion);
        httpPost.setHeader("ccept-Encoding","UTF-8");
       
        if (DEBUG) LOG.log(Level.FINE, "Created: " + httpPost);
        return httpPost;
    }
    
    @Override
	public HttpPut createHttpPut(String url, JSONObject jsonObject) {
    	if (DEBUG) LOG.log(Level.FINE, "creating HttpPut for: " + url);
    	HttpPut httpPut = new HttpPut(url);
    	httpPut.addHeader(CLIENT_VERSION_HEADER, mClientVersion);
    	try {
			StringEntity stringEntity = new StringEntity(jsonObject.toString());
			stringEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
			httpPut.setEntity(stringEntity);
			httpPut.setHeader("Content-type", "application/json");
			httpPut.setHeader("accept", "application/json");
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException("Unable to encode http parameters.");
		}
    	if (DEBUG) LOG.log(Level.FINE, "Created: " + httpPut);
		return httpPut;
	}

	@Override
	public HttpDelete createHttpDlete(String url,NameValuePair... nameValuePairs) {
		if (DEBUG) LOG.log(Level.FINE, "creating HttpDelete for: " + url);
    	
    	String query = MegerParam(stripNulls(nameValuePairs));
   
        HttpDelete httpDelete = new HttpDelete(url+ query);
    	httpDelete.addHeader(CLIENT_VERSION_HEADER, mClientVersion);
    	
    	if (DEBUG) LOG.log(Level.FINE, "Created: " + httpDelete);
		return httpDelete;
	}

	public HttpURLConnection createHttpURLConnectionPost(URL url, String boundary) 
        throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection(); 
        conn.setDoInput(true);        
        conn.setDoOutput(true); 
        conn.setUseCaches(false); 
        conn.setConnectTimeout(TIMEOUT * 1000);
        conn.setRequestMethod("POST");

        conn.setRequestProperty(CLIENT_VERSION_HEADER, mClientVersion);
        conn.setRequestProperty("Connection", "Keep-Alive"); 
        conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
        
        return conn;
    }

    private List<NameValuePair> stripNulls(NameValuePair... nameValuePairs) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        for (int i = 0; i < nameValuePairs.length; i++) {
            NameValuePair param = nameValuePairs[i];
            if (param.getValue() != null) {
                if (DEBUG) LOG.log(Level.FINE, "Param: " + param);
                params.add(param);
            }
        }
        return params;
    }
    
    
    public String MegerParam(List<NameValuePair> pairs){
    	String str= "";
    	for(NameValuePair pair:pairs){
    		str = str+"/"+pair.getValue();
    	}
    	return str;
    }

    /**
     * Create a thread-safe client. This client does not do redirecting, to allow us to capture
     * correct "error" codes.
     *
     * @return HttpClient
     */
    public static final DefaultHttpClient createHttpClient() {
        // Sets up the http part of the service.
        final SchemeRegistry supportedSchemes = new SchemeRegistry();

        // Register the "http" protocol scheme, it is required
        // by the default operator to look up socket factories.
        final SocketFactory sf = PlainSocketFactory.getSocketFactory();
        supportedSchemes.register(new Scheme("http", sf, 80));
        supportedSchemes.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        
        // Set some client http client parameter defaults.
        final HttpParams httpParams = createHttpParams();
        HttpClientParams.setRedirecting(httpParams, false);

        final ClientConnectionManager ccm = new ThreadSafeClientConnManager(httpParams,
                supportedSchemes);
        return new DefaultHttpClient(ccm, httpParams);
    }

    /**
     * Create the default HTTP protocol parameters.
     */
    private static final HttpParams createHttpParams() {
        final HttpParams params = new BasicHttpParams();

        // Turn off stale checking. Our connections break all the time anyway,
        // and it's not worth it to pay the penalty of checking every time.
        HttpConnectionParams.setStaleCheckingEnabled(params, false);

        HttpConnectionParams.setConnectionTimeout(params, TIMEOUT * 1000);
        HttpConnectionParams.setSoTimeout(params, TIMEOUT * 1000);
        HttpConnectionParams.setSocketBufferSize(params, 1024);

        return params;
    }

}

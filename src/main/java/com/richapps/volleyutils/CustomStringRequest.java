package com.richapps.volleyutils;

import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: fdoyle
 * Date: 9/4/13
 * Time: 4:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class CustomStringRequest extends StringRequest {


    private static final String PROTOCOL_CHARSET = "utf-8";
    Map<String, String> headers;
    String payload;

    public CustomStringRequest(int method, String url, Map<String, String> headers, String payload, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        this.headers = headers;
        this.payload = payload;
    }

    public static CustomStringRequest get(String url, Map<String, String> headers, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        CustomStringRequest request = new CustomStringRequest(Method.GET, url, headers, null, listener, errorListener);
        return request;
    }

    public static CustomStringRequest post(String url, Map<String, String> headers,  String payload, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        CustomStringRequest request = new CustomStringRequest(Method.POST, url, headers,  payload, listener, errorListener);
        return request;
    }

    public static CustomStringRequest put(String url, Map<String, String> headers, String payload, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        CustomStringRequest request = new CustomStringRequest(Method.PUT, url, headers, payload, listener, errorListener);
        return request;
    }


    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    public byte[] getBody() {
        try {
            if(payload == null) {
                payload = "";
            }
            return payload.getBytes(PROTOCOL_CHARSET);
        } catch (UnsupportedEncodingException e) {
            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", payload, PROTOCOL_CHARSET);
            return null;
        }
    }

    @Override
    public String getBodyContentType() {
        return "application/json";
    }

    public CustomStringRequest cSetSequence(int sequence) {
        setSequence(sequence);
        return this;
    }

    public CustomStringRequest cSetTag(Object obj) {
        setTag(obj);
        return this;
    }

    public CustomStringRequest cSetCacheEntry(Cache.Entry entry) {
        setCacheEntry(entry);
        return this;
    }

    public CustomStringRequest cSetRequestQueue(RequestQueue requestQueue) {
        setRequestQueue(requestQueue);
        return this;
    }

    public CustomStringRequest cSetRetryPolicy(RetryPolicy policy) {
        setRetryPolicy(policy);
        return this;
    }

    public CustomStringRequest cSetShouldCache(boolean cache) {
        setShouldCache(cache);
        return this;
    }

}

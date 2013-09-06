package com.richapps.volleyutils;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: fdoyle
 * Date: 9/4/13
 * Time: 4:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class FullRequest extends Request<NetworkResponseDecorator> {
    private final Response.Listener<NetworkResponseDecorator> mListener;
    Map<String, String> headers;
    String payload;

    public FullRequest(int method, String url, Map<String, String> headers, String payload, Response.Listener<NetworkResponseDecorator> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        mListener = listener;
        this.headers = headers;
        this.payload = payload;
    }

    public static FullRequest get(String url, Map<String, String> headers, Response.Listener<NetworkResponseDecorator> listener, Response.ErrorListener errorListener) {
        FullRequest request = new FullRequest(Method.GET, url, headers, null, listener, errorListener);
        request.setTag(url);
        return request;
    }

    public static FullRequest post(String url, Map<String, String> headers,  String payload, Response.Listener<NetworkResponseDecorator> listener, Response.ErrorListener errorListener) {
        FullRequest request = new FullRequest(Method.POST, url, headers,  payload, listener, errorListener);
        request.setTag(url);
        return request;
    }

    public static FullRequest put(String url, Map<String, String> headers, String payload, Response.Listener<NetworkResponseDecorator> listener, Response.ErrorListener errorListener) {
        FullRequest request = new FullRequest(Method.PUT, url, headers, payload, listener, errorListener);
        request.setTag(url);
        return request;
    }

    @Override
    protected void deliverResponse(NetworkResponseDecorator response) {
        mListener.onResponse(response);
    }

    @Override
    protected Response<NetworkResponseDecorator> parseNetworkResponse(NetworkResponse response) {
        return Response.success(new NetworkResponseDecorator(response), HttpHeaderParser.parseCacheHeaders(response));
    }
}
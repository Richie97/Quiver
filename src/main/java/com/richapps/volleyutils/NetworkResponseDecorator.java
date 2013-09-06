package com.richapps.volleyutils;

import com.android.volley.NetworkResponse;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;

/**
 * Created with IntelliJ IDEA.
 * User: fdoyle
 * Date: 9/4/13
 * Time: 4:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class NetworkResponseDecorator {
    NetworkResponse mResponse;
    public NetworkResponseDecorator(NetworkResponse response) {
        mResponse = response;
    }

    public String getResponseString() {
        try {
            return new String(mResponse.data, HttpHeaderParser.parseCharset(mResponse.headers));
        } catch (UnsupportedEncodingException e) {
            return new String(mResponse.data);
        }
    }
}

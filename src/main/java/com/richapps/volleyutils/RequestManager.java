package com.richapps.volleyutils;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.HurlStack;

/**
 * Created by ericrichardson on 8/13/13.
 */
public class RequestManager {

    private static RequestQueue mRequestQueue;

    private RequestManager() {
        // no instances
    }

    public static void init(Context context) {
        init(context, new OkHttpStack());
    }

    public static void init(Context context, HurlStack stack) {
        mRequestQueue = Volley.newRequestQueue(context, stack);
    }

    public static RequestQueue getRequestQueue() {
        if (mRequestQueue != null) {
            return mRequestQueue;
        } else {
            throw new IllegalStateException("Not initialized");
        }
    }
}

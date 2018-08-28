package com.example.hp.shopping;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyApplication extends Application {
    private static VolleyApplication sInstance;
    public RequestQueue requestQueue;
    @Override
    public void onCreate() {
        super.onCreate();
        requestQueue = Volley.newRequestQueue(this);

        sInstance = this;
    }

    public synchronized static VolleyApplication getInstance() {
        return sInstance;
    }

    public com.android.volley.RequestQueue getRequestQueue() {
        return requestQueue;
    }

}

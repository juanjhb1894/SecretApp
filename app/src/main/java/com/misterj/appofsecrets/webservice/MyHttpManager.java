package com.misterj.appofsecrets.webservice;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.misterj.appofsecrets.interfaces.ResponseListener;

import java.util.HashMap;
import java.util.Map;

public class MyHttpManager {

    private static MyHttpManager instance;
    private RequestQueue requestQueue;

    Context context;

    public MyHttpManager(Context context) {
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }

    private MyHttpManager() {
        // Initialize the request queue
    }

    public static synchronized MyHttpManager getInstance() {
        if (instance == null) {
            instance = new MyHttpManager();
        }
        return instance;
    }

    public void postData(final String data, final ResponseListener<String> listener) {
        // Create a StringRequest with POST method
        String url = new Parameters().ApiURL + "places/submit/";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listener.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError(error);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Put your data to send in the request body
                Map<String, String> params = new HashMap<>();
                params.put("data", data);
                return params;
            }
        };

        // Add the request to the request queue
        requestQueue.add(stringRequest);
    }
}
package com.misterj.appofsecrets.interfaces;

import com.android.volley.VolleyError;

public interface ResponseListener<T> {
    void onResponse(T response);
    void onError(VolleyError error);
}

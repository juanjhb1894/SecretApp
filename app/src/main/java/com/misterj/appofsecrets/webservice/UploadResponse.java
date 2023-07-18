package com.misterj.appofsecrets.webservice;

import com.google.gson.annotations.SerializedName;

public class UploadResponse {
    @SerializedName("data")
    private Data data;

    public Data getData() {
        return data;
    }

    public class Data {
        @SerializedName("url")
        private String url;

        public String getUrl() {
            return url;
        }
    }
}
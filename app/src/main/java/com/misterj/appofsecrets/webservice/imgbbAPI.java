package com.misterj.appofsecrets.webservice;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.LatLng;
import com.misterj.appofsecrets.MainActivity;
import com.misterj.appofsecrets.PlacesActivity;
import com.misterj.appofsecrets.R;
import com.misterj.appofsecrets.interfaces.ApiService;
import com.misterj.appofsecrets.interfaces.ResponseListener;
import com.misterj.appofsecrets.utils.DataProccessor;
import com.misterj.appofsecrets.utils.GeocodeUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class imgbbAPI {

    Context context;

    public imgbbAPI(Context context) {
        this.context = context;
    }

    private static final String BASE_URL = "https://api.imgbb.com/";

    public static Retrofit getClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }

    public void Upload (String imagePath)
    {
        String apiKey = new Parameters().ImgbbApiKey;
        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part imagePart = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
        ApiService apiService = getClient().create(ApiService.class);
        Call<UploadResponse> call = apiService.uploadImage(apiKey, imagePart);
        call.enqueue(new Callback<UploadResponse>() {
            @Override
            public void onResponse(Call<UploadResponse> call, Response<UploadResponse> response) {
                if (response.isSuccessful()) {
                    UploadResponse uploadResponse = response.body();
                    Log.e("Image_Uploaded",uploadResponse.getData().getUrl());
                    new DataProccessor(context).setStr("Image_Uploaded",uploadResponse.getData().getUrl());
                    // Do something with the uploaded image URL
                } else {
                    // Handle error response
                }
            }

            @Override
            public void onFailure(Call<UploadResponse> call, Throwable t) {
                // Handle network failure
            }
        });
    }

    public void UploadAndSubmitPlace (String imagePath, String name, int category, double latitude, double longitude)
    {
        String apiKey = new Parameters().ImgbbApiKey;
        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part imagePart = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
        ApiService apiService = getClient().create(ApiService.class);
        Call<UploadResponse> call = apiService.uploadImage(apiKey, imagePart);
        call.enqueue(new Callback<UploadResponse>() {
            @Override
            public void onResponse(Call<UploadResponse> call, Response<UploadResponse> response) {
                if (response.isSuccessful()) {
                    UploadResponse uploadResponse = response.body();
                    Log.e("Image_Uploaded",uploadResponse.getData().getUrl());
                    new DataProccessor(context).setStr("Image_Uploaded",uploadResponse.getData().getUrl());

                    // Replace "yourDataHere" with the actual data you want to send
                    postDataUsingVolley(name, Integer.toString(category), Double.toString(latitude), Double.toString(longitude),
                            new GeocodeUtils(context).getCountry(new LatLng(latitude, longitude)),
                            new GeocodeUtils(context).getState(new LatLng(latitude, longitude)),
                            new DataProccessor(context).getStr("Image_Uploaded"));


                    // Do something with the uploaded image URL
                } else {
                    // Handle error response
                }
            }

            @Override
            public void onFailure(Call<UploadResponse> call, Throwable t) {
                // Handle network failure
            }
        });
    }

    private void postDataUsingVolley(String name, String category, String lat, String lng, String country, String state, String image) {
        String url = new Parameters().ApiURL + "places/submit/";
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("POST_RESPONSE", response);
                Toast.makeText(context, "Data added to API", Toast.LENGTH_SHORT).show();
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR_RESPONSE", error.toString());
                Toast.makeText(context, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", name);
                params.put("category", category);
                params.put("lat", lat);
                params.put("lng", lng);
                params.put("country", country);
                params.put("state", state);
                params.put("image", image);
                return params;
            }
        };
        queue.add(request);
    }
}

package com.misterj.appofsecrets.webservice;

import android.graphics.Bitmap;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Places {

    private static String LOG_TAG = "App of Secrets";
    ArrayList<PlaceInfo> list;

    public ArrayList<PlaceInfo> ClosePlaces(double lat, double lng, int distance, int category, String country, String state, String verified)
    {
        list = new ArrayList<>();
        String filter = "";
        String url = new Parameters().ApiURL + "places/close/?lat="+lat+"&lng="+lng;
        //DO Task;
        if (Integer.toString(distance) != null)
        {
            filter+="&distance="+distance;
        }
        if (country != null)
        {
            filter+="&country="+country;
        }
        if (state != null)
        {
            filter+="&state="+state;
        }
        if (category != 0)
        {
            filter+="&cat="+category;
        }
        if (verified != null)
        {
            filter+="&verified="+verified;
        }

        try {
            String json = retrieveJSONFromURL(url+filter);
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                PlaceInfo markerPlaces = new PlaceInfo();
                markerPlaces.setId(jsonObj.getInt("id"));
                markerPlaces.setName(jsonObj.getString("name"));
                markerPlaces.setCategory(jsonObj.getInt("category"));
                markerPlaces.setStatus(jsonObj.getInt("status"));
                markerPlaces.setCountry(jsonObj.getString("country"));
                markerPlaces.setState(jsonObj.getString("state"));
                markerPlaces.setLat(jsonObj.getDouble("lat"));
                markerPlaces.setLng(jsonObj.getDouble("lng"));
                markerPlaces.setSponsored(jsonObj.getInt("sponsored"));
                markerPlaces.setImage(jsonObj.getString("image"));
                markerPlaces.setVerified(jsonObj.getInt("verified"));
                markerPlaces.setCreated_at(jsonObj.getString("created_at"));
                markerPlaces.setDistance(jsonObj.getDouble("distance"));
                list.add(markerPlaces);
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        Log.e("Count",Integer.toString(list.size()));
        return list;
    }


    public String submitNewPlaceBK(String jsonPayload)
    {
        try {
            String url = new Parameters().ApiURL + "places/submit/";
            return Integer.toString(postData(jsonPayload, url));
        }
        catch (IOException ioE)
        {
            throw new RuntimeException(ioE);
        }
    }

    private int postData(String jsonPayload, String link) throws IOException {
        URL url = new URL(link);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.connect();

        // Write the request body
        OutputStream outputStream = urlConnection.getOutputStream();
        outputStream.write(jsonPayload.getBytes("UTF-8"));
        outputStream.close();

        // Get the response code
        Log.e("RESPONSE CODE", Integer.toString(urlConnection.getResponseCode()));
        return urlConnection.getResponseCode();
    }

    private String retrieveJSONFromURL(String link) throws IOException {
        HttpURLConnection conn = null;
        Log.e(LOG_TAG, link);
        final StringBuilder json = new StringBuilder();
        try {
            // Connect to the web service
            URL url = new URL(link);
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            // Read the JSON data into the StringBuilder
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                json.append(buff, 0, read);
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error connecting to service", e);
            throw new IOException("Error connecting to service", e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return json.toString();
    }
    public class PlaceInfo {
        public Integer id;
        public String name;
        public Integer category;
        public Integer status;
        public String country;
        public String state;
        public Double lat;
        public Double lng;
        public Integer sponsored;
        public String image;
        public Integer verified;
        public String created_at;
        public Double distance;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getCategory() {
            return category;
        }

        public void setCategory(Integer category) {
            this.category = category;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLng() {
            return lng;
        }

        public void setLng(Double lng) {
            this.lng = lng;
        }

        public Integer getSponsored() {
            return sponsored;
        }

        public void setSponsored(Integer sponsored) {
            this.sponsored = sponsored;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public Double getDistance() {
            return distance;
        }

        public void setDistance(Double distance) {
            this.distance = distance;
        }

        public Integer getVerified() {
            return verified;
        }

        public void setVerified(Integer verfied) {
            this.verified = verfied;
        }
    }
}

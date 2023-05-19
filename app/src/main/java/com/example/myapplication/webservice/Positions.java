package com.example.myapplication.webservice;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executors;

public class Positions {

    private static String LOG_TAG = "App of Secrets";

    public DailyPosition Daily(int day)
    {
        DailyPosition dailyPosition = new DailyPosition();
            //DO Task;
            String url = new Parameters().ApiURL + "daily/positions/";
            try {
                String json = retrieveJSONFromURL(url);
                JSONArray jsonArray = new JSONArray(json);
                JSONObject jsonObj = jsonArray.getJSONObject(day);

                Log.e("Image", jsonObj.getString("img"));
                dailyPosition.setId(jsonObj.getInt("id"));
                dailyPosition.setName(jsonObj.getString("name"));
                dailyPosition.setDescription(jsonObj.getString("description"));
                dailyPosition.setHow(jsonObj.getString("how"));
                dailyPosition.setWhy(jsonObj.getString("why"));
                dailyPosition.setImage(jsonObj.getString("img"));

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }


        return dailyPosition;
    }

    public DailyPosition getRandomPosition()
    {
        DailyPosition dailyPosition = new DailyPosition();
        //DO Task;
        String url = new Parameters().ApiURL + "randomizer/positions/";
        try {
            String json = retrieveJSONFromURL(url);
            JSONArray jsonArray = new JSONArray(json);
            JSONObject jsonObj = jsonArray.getJSONObject(0);

            Log.e("Image", jsonObj.getString("img"));
            dailyPosition.setId(jsonObj.getInt("id"));
            dailyPosition.setName(jsonObj.getString("name"));
            dailyPosition.setDescription(jsonObj.getString("description"));
            dailyPosition.setHow(jsonObj.getString("how"));
            dailyPosition.setWhy(jsonObj.getString("why"));
            dailyPosition.setImage(jsonObj.getString("img"));

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }


        return dailyPosition;
    }

    private String retrieveJSONFromURL(String link) throws IOException {
        HttpURLConnection conn = null;
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

    public class DailyPosition {
        public int id;
        public String name;
        public String description;
        public String how;
        public String why;
        public String image;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getHow() {
            return how;
        }

        public void setHow(String how) {
            this.how = how;
        }

        public String getWhy() {
            return why;
        }

        public void setWhy(String why) {
            this.why = why;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }

}



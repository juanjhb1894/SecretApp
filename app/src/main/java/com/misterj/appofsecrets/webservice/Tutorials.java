package com.misterj.appofsecrets.webservice;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Tutorials {

    private static String LOG_TAG = "App of Secrets";

    public ArrayList<VideoTutorial> Videos(int target)
    {
        ArrayList<VideoTutorial>  list = new ArrayList<>();
        String url = "";
            //DO Task;
            if (target != 0)
            {
                url = new Parameters().ApiURL + "tutorials/list/?target="+target;
            }
            else
            {
                url = new Parameters().ApiURL + "tutorials/list/";
            }
            try {
                String json = retrieveJSONFromURL(url);
                JSONArray jsonArray = new JSONArray(json);
                for (int i = 0; i < jsonArray.length(); i++)
                {
                    JSONObject jsonObj = jsonArray.getJSONObject(i);
                    VideoTutorial videoTutorial = new VideoTutorial();
                    videoTutorial.setId(jsonObj.getInt("id"));
                    videoTutorial.setName(jsonObj.getString("name"));
                    videoTutorial.setDescription(jsonObj.getString("description"));
                    videoTutorial.setAuthor(jsonObj.getString("author"));
                    videoTutorial.setStatus(jsonObj.getInt("status"));
                    videoTutorial.setUrl(jsonObj.getString("url"));
                    videoTutorial.setCoverPictureUrl(jsonObj.getString("cover_picture_url"));
                    videoTutorial.setUploadedAt(jsonObj.getString("uploaded_at"));

                    list.add(videoTutorial);
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }


        return list;
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

    public class VideoTutorial {
        public int id;
        public String name;
        public String url;
        public String coverPictureUrl;
        public String description;
        public Integer status;
        public String author;
        public String uploadedAt;
        public int target;

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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCoverPictureUrl() {
            return coverPictureUrl;
        }

        public void setCoverPictureUrl(String coverPictureUrl) {
            this.coverPictureUrl = coverPictureUrl;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getUploadedAt() {
            return uploadedAt;
        }

        public void setUploadedAt(String uploadedAt) {
            this.uploadedAt = uploadedAt;
        }

        public int getTarget() {
            return target;
        }

        public void setTarget(int target) {
            this.target = target;
        }
    }

}



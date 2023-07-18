package com.misterj.appofsecrets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.misterj.appofsecrets.adapters.GamesAdapter;
import com.misterj.appofsecrets.items.GamesItems;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GamesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        List<GamesItems> itemsList = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(loadJSONFile());
            for(int i = 0; i<jsonArray.length();i++)
            {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                itemsList.add(new GamesItems(jsonObj.getInt("id"),
                        jsonObj.getString("image"),
                        jsonObj.getString("name"),
                        jsonObj.getString("description"),
                        jsonObj.getString("rules")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        GridView gridView = findViewById(R.id.gridGames);
        GamesAdapter customAdapter = new GamesAdapter(this, R.layout.item_game, itemsList);
        gridView.setAdapter(customAdapter);
    }

    public String loadJSONFile() {
        String json = null;
        try {
            InputStream inputStream = getAssets().open("games.json");
            int size = inputStream.available();
            byte[] byteArray = new byte[size];
            inputStream.read(byteArray);
            inputStream.close();
            json = new String(byteArray, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }
}
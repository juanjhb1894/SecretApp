package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.myapplication.adapters.PlacesAdapter;
import com.example.myapplication.items.PlacesItems;

import java.util.ArrayList;
import java.util.List;

public class PlacesActivity extends AppCompatActivity {

    ListView lstPlaces;
    ArrayList<PlacesItems> items;
    PlacesAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        items = new ArrayList<>();

        lstPlaces = (ListView) findViewById(R.id.lstPlaces);
        items= new ArrayList<>();
        items.add(new PlacesItems(11,"http://url","name","description",18.00,-70.00,10));

        adapter= new PlacesAdapter(getApplicationContext(), items);
        lstPlaces.setAdapter(adapter);

    }
}
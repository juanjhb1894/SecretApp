package com.misterj.appofsecrets.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.misterj.appofsecrets.R;
import com.misterj.appofsecrets.items.PlacesItems;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

public class PlacesAdapter extends ArrayAdapter{

    List<PlacesItems> items_list;
    int custom_layout_id;
    Context context;

    private static final DecimalFormat df = new DecimalFormat("0.00");


    public PlacesAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        items_list = objects;
        custom_layout_id = resource;
        this.context=context;
    }
    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            // getting reference to the main layout and initializing
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_place, null);
        }

        // initializing the imageview and textview and setting data
        ImageView imgItemPlacePicture = v.findViewById(R.id.imgItemPlacePicture);
        ImageView imgVerifiedPlace = v.findViewById(R.id.imgVerifiedPlace);
        TextView tvItemPlaceDistance = v.findViewById(R.id.tvItemPlaceDistance);
        TextView tvItemPlaceName = v.findViewById(R.id.tvItemPlaceName);
        TextView tvItemPlaceCity = v.findViewById(R.id.tvItemPlaceCity);
        TextView tvItemPlaceCategory = v.findViewById(R.id.tvItemPlaceCategory);


        // get the item using the  position param
        PlacesItems item = items_list.get(position);
        String cat = "";

        switch (item.getCategory())
        {
            case 0:
                cat = context.getString(R.string.cat0);
                break;

            case 1:
                cat = context.getString(R.string.cat1);
                break;

        }

        switch(item.getVerified())
        {
            case 0:
                imgVerifiedPlace.setVisibility(View.GONE);
                break;
            case 1:
                imgVerifiedPlace.setVisibility(View.VISIBLE);
                break;
            default:
                imgVerifiedPlace.setVisibility(View.INVISIBLE);
                break;
        }

        Log.e("Image", item.getImage());
        if(item.getImage() != null && !item.getImage().toLowerCase().equals("null"))
        {
            Glide.with(getContext()).load(item.getImage()).into(imgItemPlacePicture);
        }
        else
        {
            Drawable res = context.getResources().getDrawable(R.drawable.default_geocode);
            imgItemPlacePicture.setImageDrawable(res);
        }
        tvItemPlaceName.setText(item.getName());
        tvItemPlaceCity.setText(item.getState());
        tvItemPlaceCategory.setText(cat);
        df.setRoundingMode(RoundingMode.UP);
        tvItemPlaceDistance.setText(df.format(item.getDistance()) + " km");

        return v;
    }

}

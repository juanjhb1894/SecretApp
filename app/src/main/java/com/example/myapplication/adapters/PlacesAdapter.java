package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.items.PlacesItems;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class PlacesAdapter extends ArrayAdapter<PlacesItems> implements View.OnClickListener, AdapterView.OnItemClickListener{

    private ArrayList<PlacesItems> dataSet;
    Context mContext;

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        PlacesItems dataModel= dataSet.get(i);
        Snackbar.make(view, dataModel.getName(), Snackbar.LENGTH_LONG)
                .setAction("No action", null).show();
    }

    // View lookup cache
    private static class ViewHolder {
        TextView tvItemPlaceName;
        TextView tvItemPlaceLocation;
        TextView tvItemPlaceDistance;
        ImageView imgItemPlacePicture;
    }

    public PlacesAdapter(Context context, ArrayList<PlacesItems> data) {
        super(context, R.layout.item_place, data);
        this.dataSet = data;
        this.mContext=context;
    }

    @Override
    public void onClick(View v) {
        int position=(Integer) v.getTag();
        Object object= getItem(position);
        PlacesItems dataModel=(PlacesItems)object;

        switch (v.getId())
        {
           /// Do something onClick
        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        PlacesItems dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_place, parent, false);
            viewHolder.tvItemPlaceName = (TextView) convertView.findViewById(R.id.tvItemPlaceName);
            viewHolder.tvItemPlaceDistance = (TextView) convertView.findViewById(R.id.tvItemPlaceDistance);
            viewHolder.tvItemPlaceLocation = (TextView) convertView.findViewById(R.id.tvItemPlaceCity);
            viewHolder.imgItemPlacePicture = (ImageView) convertView.findViewById(R.id.imgItemPlacePicture);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.tvItemPlaceName.setText(dataModel.getName());
        viewHolder.tvItemPlaceDistance.setText(" "+Double.toString(dataModel.getDistance()) + " ");
        viewHolder.tvItemPlaceLocation.setText(Double.toString(dataModel.getLongitude()));
        // Return the completed view to render on screen
        return convertView;
    }

}

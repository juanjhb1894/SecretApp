package com.misterj.appofsecrets.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.misterj.appofsecrets.R;
import com.misterj.appofsecrets.items.TutorialItems;


import java.util.List;

public class VideosAdapter extends ArrayAdapter{

    List<TutorialItems> items_list;
    int custom_layout_id;
    Context context;

    public VideosAdapter(Context context, int resource, List objects) {
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
            v = inflater.inflate(R.layout.item_tutorial, null);
        }

        // initializing the imageview and textview and setting data
        ImageView imgTutorialItem = v.findViewById(R.id.imgTutorialItem);
        ImageView imgPlayutorialItem = v.findViewById(R.id.imgPlayutorialItem);
        TextView textView = v.findViewById(R.id.tvTutorialUsername);

        // get the item using the  position param
        TutorialItems item = items_list.get(position);

        ///ThumbnailExtract thumbnailExtract = new ThumbnailExtract(item.getUrl(),imgTutorialItem, true);
        ///thumbnailExtract.SetThumbnail();

        Glide.with(getContext()).load(item.getCoverPictureUrl()).into(imgTutorialItem);
        textView.setText(item.getAuthor());



        return v;
    }

}

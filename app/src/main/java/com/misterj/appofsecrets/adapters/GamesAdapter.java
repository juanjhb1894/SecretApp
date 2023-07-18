package com.misterj.appofsecrets.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.misterj.appofsecrets.DiceActivity;
import com.misterj.appofsecrets.R;
import com.misterj.appofsecrets.RandomizerActivity;
import com.misterj.appofsecrets.dialogs.GamesDialogs;
import com.misterj.appofsecrets.items.GamesItems;

import java.util.List;

public class GamesAdapter extends ArrayAdapter{

    List<GamesItems> items_list;
    int custom_layout_id;
    Context context;

    public GamesAdapter(Context context, int resource, List objects) {
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
            v = inflater.inflate(R.layout.item_game, null);
        }

        // initializing the imageview and textview and setting data
        ImageView imgGameInfo = v.findViewById(R.id.imgInformation);
        ImageView imageView = v.findViewById(R.id.imgGameItem);
        TextView textView = v.findViewById(R.id.tvGameItem1);

        // get the item using the  position param
        GamesItems item = items_list.get(position);

        Glide.with(getContext()).load(item.getIamge_url()).into(imageView);
        textView.setText(item.getName());

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (item.getId())
                {
                    case 1:
                        context.startActivity(new Intent(context, DiceActivity.class));
                        break;
                    case 2:
                        context.startActivity(new Intent(context, RandomizerActivity.class));
                        break;
                }
            }
        });

        imgGameInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GamesDialogs(context, item).Show();
            }
        });

        return v;
    }

}

package com.misterj.appofsecrets.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.misterj.appofsecrets.R;
import com.misterj.appofsecrets.webservice.Positions;

public class PositionDialogs {
    private Context context;
    private Button action;
    private TextView tvTitleInfoPosition, tvDescInfoPosition;
    private ImageView imgInfoPosition;
    private Positions.DailyPosition item;

    public PositionDialogs(Context context, Positions.DailyPosition item) {
        this.context = context;
        this.item = item;
    }

    public void Show(){
        // custom dialog
        Dialog dialog = new Dialog(context,android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.positioninfo);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        action = (Button) dialog.findViewById(R.id.btnPositionInfoAlright);
        imgInfoPosition = (ImageView) dialog.findViewById(R.id.imgInfoPosition);
        tvTitleInfoPosition = (TextView) dialog.findViewById(R.id.tvTitleInfoPosition);
        tvDescInfoPosition = (TextView) dialog.findViewById(R.id.tvDescInfoPosition);

        tvTitleInfoPosition.setText(item.name);
        String composition = "";

        if(item.description != null && item.description != "null" &&
                item.how != null && item.how != "null" &&
                item.why != null && item.why != "null")
        {
            if(item.description != null && item.description != "null" )
            {
                composition += context.getString(R.string.description_key) + item.description + "\n";
            }

            if(item.how != null && item.how != "null")
            {
                composition +=  context.getString(R.string.how_key) + item.how + "\n";
            }

            if(item.why != null && item.why != "null")
            {
                composition += context.getString(R.string.why_key) + item.why + "\n";
            }
        }
        else
        {
            composition= context.getString(R.string.selfexplenatory);
        }
        tvDescInfoPosition.setText(composition);
        Glide.with(context).load(item.image).into(imgInfoPosition);

        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


}

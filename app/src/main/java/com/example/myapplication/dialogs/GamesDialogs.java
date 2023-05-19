package com.example.myapplication.dialogs;

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
import com.example.myapplication.R;
import com.example.myapplication.items.GamesItems;

import org.w3c.dom.Text;

public class GamesDialogs {
    private Context context;
    private Button action;
    private TextView tvTitleInfoGame, tvDescInfoGame, tvRulesInfoGame;
    private ImageView imgInfoGame;
    private GamesItems item;

    public GamesDialogs(Context context,GamesItems item) {
        this.context = context;
        this.item = item;
    }

    public void Show(){
        // custom dialog
        Dialog dialog = new Dialog(context,android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.gameinfo);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        action = (Button) dialog.findViewById(R.id.btnGameInfoAlright);
        imgInfoGame = (ImageView) dialog.findViewById(R.id.imgInfoGame);
        tvTitleInfoGame = (TextView) dialog.findViewById(R.id.tvTitleInfoGame);
        tvDescInfoGame = (TextView) dialog.findViewById(R.id.tvDescInfoGame);
        tvRulesInfoGame = (TextView) dialog.findViewById(R.id.tvRulesInfoGame);

        tvTitleInfoGame.setText(item.getName());
        tvDescInfoGame.setText(item.getDescription());
        tvRulesInfoGame.setText(item.getRules());
        Glide.with(context).load(item.getIamge_url()).into(imgInfoGame);

        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


}

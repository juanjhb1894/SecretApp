package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.dialogs.UnderageDialogs;
import com.example.myapplication.utils.DataProccessor;
import com.example.myapplication.utils.DateCalculations;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LauncherActivity extends AppCompatActivity {
    DataProccessor dataProccessor;
    UnderageDialogs underageDialogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        TextView tvAppName = (TextView) findViewById(R.id.tvAppName);
        ImageView imgLauncherLogo = (ImageView) findViewById(R.id.imgLauncherLogo);

        Animation myFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        myFadeInAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                imgLauncherLogo.setVisibility(View.VISIBLE);
                imgLauncherLogo.setAlpha(1.0f);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        imgLauncherLogo.startAnimation(myFadeInAnimation);

        Animation myFadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        myFadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                tvAppName.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        tvAppName.startAnimation(myFadeOutAnimation);

        underageDialogs = new UnderageDialogs(LauncherActivity.this);
        dataProccessor = new DataProccessor(LauncherActivity.this);
        String today = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());

        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!dataProccessor.getStr("birthday").equals("DNF"))
                {
                    String bithday=dataProccessor.getStr("birthday");
                    int age = new DateCalculations().differenceInYears(bithday, today);
                    if(age >= 18)
                    {
                        startActivity(new Intent(LauncherActivity.this, MainActivity.class));
                    }
                    else
                    {
                        underageDialogs.Show();
                    }
                }
                else
                {
                    underageDialogs.Ask();
                }
            }
        }, 5000);

    }


}
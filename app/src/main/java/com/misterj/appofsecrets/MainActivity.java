package com.misterj.appofsecrets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.misterj.appofsecrets.utils.MultipleClickListener;
import com.misterj.appofsecrets.webservice.Positions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Positions.DailyPosition dailyPositions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        ImageView imgOpGames = (ImageView) findViewById(R.id.imgGames);
        ImageView imgOpPlaces = (ImageView) findViewById(R.id.imgPlaces);
        ImageView imgOpBooks = (ImageView) findViewById(R.id.imgBook);

        imgOpGames.setOnClickListener(this);
        imgOpPlaces.setOnClickListener(this);
        imgOpBooks.setOnClickListener(this);

        ImageView imgTodaysPosition = (ImageView) findViewById(R.id.imgTodaysPosition);
        TextView tvTodaysPosition = (TextView) findViewById(R.id.tvTodaysPosition);
        LinearLayout menuOptions = (LinearLayout) findViewById(R.id.menuOptions);
        ImageView imgOptions  = (ImageView) findViewById(R.id.imgOptions);

        Calendar calendar = Calendar.getInstance();
        DateFormat date= new SimpleDateFormat("dd", Locale.getDefault());
        String dayNumber = date.format(calendar.getTime());

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                //Background work here
                dailyPositions = new Positions().Daily(Integer.parseInt(dayNumber));
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //UI Thread work here
                        Glide.with(MainActivity.this).load(dailyPositions.image).into(imgTodaysPosition);
                        tvTodaysPosition.setText(dailyPositions.name);
                    }
                });
            }
        });

        imgOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    expand(menuOptions);
                    imgOptions.setVisibility(View.GONE);
            }
        });

        imgTodaysPosition.setOnClickListener(new MultipleClickListener() {
            @Override
            public void onSingleClick(View v) {
                if(imgOptions.getVisibility() == View.GONE) {
                    collapse(menuOptions);
                    imgOptions.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onDoubleClick(View v) {

            }
        });
    }



    public static void expand(final View v) {
        v.measure(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? WindowManager.LayoutParams.WRAP_CONTENT
                        : (int)(targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration((int)(targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                    v.setVisibility(View.GONE);
                }else{
                    v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration((int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.imgGames:
                    startActivity(new Intent(MainActivity.this, GamesActivity.class));
                break;
            case R.id.imgPlaces:
                startActivity(new Intent(MainActivity.this, PlacesActivity.class));
                break;
            case R.id.imgBook:
                startActivity(new Intent(MainActivity.this, TutorialsActivity.class));
                break;
        }
    }
}
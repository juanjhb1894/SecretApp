package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.dialogs.PositionDialogs;
import com.example.myapplication.webservice.Positions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RandomizerActivity extends AppCompatActivity {

    ImageView imgRandomPosition, icInfoRandomPosition;
    TextView tvRandomPosition, tvFlippingCard;
    FloatingActionButton fabRandomPosition;
    Positions.DailyPosition randomPositions;
    Animator flip_y;
    Animation fade_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randomizer);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        imgRandomPosition = (ImageView) findViewById(R.id.imgRandomPosition);
        icInfoRandomPosition = (ImageView) findViewById(R.id.icInfoRandomPosition);
        tvRandomPosition = (TextView) findViewById(R.id.tvRandomPosition);
        tvFlippingCard = (TextView) findViewById(R.id.tvFlippingCard);
        fabRandomPosition = (FloatingActionButton) findViewById(R.id.fabRandomPosition);

        fade_out = AnimationUtils.loadAnimation(RandomizerActivity.this, R.anim.fade_out);
        flip_y = AnimatorInflater.loadAnimator(RandomizerActivity.this, R.animator.flip_y);
        flip_y.setTarget(tvFlippingCard);

        loadRandomPosition();
        tvFlippingCard.setVisibility(View.GONE);

        fabRandomPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvFlippingCard.setVisibility(View.VISIBLE);
                flip_y.start();
            }
        });

        fade_out.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                tvFlippingCard.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        flip_y. addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animator) {
                loadRandomPosition();
            }

            @Override
            public void onAnimationEnd(@NonNull Animator animator) {
                tvFlippingCard.startAnimation(fade_out);
            }

            @Override
            public void onAnimationCancel(@NonNull Animator animator) {

            }

            @Override
            public void onAnimationRepeat(@NonNull Animator animator) {

            }
        });

        tvRandomPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PositionDialogs(RandomizerActivity.this, randomPositions).Show();
            }
        });

        icInfoRandomPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PositionDialogs(RandomizerActivity.this, randomPositions).Show();
            }
        });

        imgRandomPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PositionDialogs(RandomizerActivity.this, randomPositions).Show();
            }
        });
    }

    private void loadRandomPosition()
    {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                //Background work here
                randomPositions = new Positions().getRandomPosition();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //UI Thread work here
                        Glide.with(RandomizerActivity.this).load(randomPositions.image).into(imgRandomPosition);
                        tvRandomPosition.setText(randomPositions.name);
                    }
                });
            }
        });

    }


}
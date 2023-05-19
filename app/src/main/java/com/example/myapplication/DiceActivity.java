package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.myapplication.customs.DiceResults;

import java.io.IOException;
import java.util.Random;

public class DiceActivity extends AppCompatActivity {

    private Button rollDices;
    private ImageView imageView1, imageView2;
    private Spinner spGameLevel;
    DiceResults dr;

    Animation bounceRight;
    Animation bounceLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        imageView1 = (ImageView) findViewById(R.id.diceActions);
        imageView2 = (ImageView) findViewById(R.id.diceBodyPart);
        rollDices = (Button) findViewById(R.id.rollDices);
        spGameLevel = (Spinner) findViewById(R.id.spGameLevel);

        bounceRight = AnimationUtils.loadAnimation(DiceActivity.this, R.anim.bounce_interpolator_right);
        bounceRight.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                dr = new DiceResults(DiceActivity.this, spGameLevel.getSelectedItemPosition());
                dr.getWindow().getAttributes().windowAnimations = R.style.DiceResultAnimation;
                dr.show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        bounceLeft = AnimationUtils.loadAnimation(DiceActivity.this, R.anim.bounce_interpolator_left);
        bounceLeft.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                dr = new DiceResults(DiceActivity.this, spGameLevel.getSelectedItemPosition());
                dr.getWindow().getAttributes().windowAnimations = R.style.DiceResultAnimation;
                dr.show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        rollDices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randomDiceBouncing(imageView1, imageView2);
            }
        });
    }

    private void playRollingSound() {
        String filename = "audio/rolling_dice.mp3";
        AssetFileDescriptor afd = null;
        try {
            afd = getResources().getAssets().openFd(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MediaPlayer player = new MediaPlayer();
        try {
            assert afd != null;
            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.start();
    }

    private void randomDiceBouncing(ImageView dice1, ImageView dice2)
    {
        Random rand = new Random();
        int n = rand.nextInt(4);
        if (n == 0)
        {
            playRollingSound();
            dice1.startAnimation(bounceRight);
            dice2.startAnimation(bounceLeft);
        }
        else if(n == 1)
        {   playRollingSound();
            dice1.startAnimation(bounceLeft);
            dice2.startAnimation(bounceRight);
        }
        else if(n == 2)
        {
            playRollingSound();
            dice2.startAnimation(bounceLeft);
            dice1.startAnimation(bounceLeft);
        }else
        {
            playRollingSound();
            dice2.startAnimation(bounceRight);
            dice1.startAnimation(bounceRight);
        }

    }

    public int getRandomNumberRange(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }


}
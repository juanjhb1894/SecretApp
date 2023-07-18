package com.misterj.appofsecrets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.misterj.appofsecrets.dialogs.UnderageDialogs;
import com.misterj.appofsecrets.utils.DataProccessor;
import com.misterj.appofsecrets.utils.DateCalculations;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LauncherActivity extends AppCompatActivity {
    DataProccessor dataProccessor;
    UnderageDialogs underageDialogs;
    
    public static final int REQUEST_CODE_ASK_PERMISSIONS = 10;
    String[] permissions = new String[] {
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        if (checkPermissions()){
            // permissions granted.
            ValidateAge();
        } else {
            // show dialog informing them that we lack certain permissions
            askPermissions();
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
    }

    private boolean checkPermissions() {
            if ( Build.VERSION.SDK_INT >= 23){
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED )
                {
                    return false;
                }
                else
                {
                        return true;
                }
            }
            else
            {
                return true;
            }
    }

    private void askPermissions()
    {
        requestPermissions(permissions, REQUEST_CODE_ASK_PERMISSIONS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ValidateAge();
                } else {
                    // Permission Denied
                    askPermissions();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void ValidateAge()
    {
        underageDialogs = new UnderageDialogs(LauncherActivity.this);
        dataProccessor = new DataProccessor(LauncherActivity.this);
        String today = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());

        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(dataProccessor.getStr("birthday") != null)
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
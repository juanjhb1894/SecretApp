package com.misterj.appofsecrets;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {
    VideoView videoView;
    ImageView imgVideoInfo;
    TextView tvVideoName, tvVideoAuthor, tvVideoDescription;
    LinearLayout llVideoInformation;
    int stopPosition;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Intent intent = getIntent();
        String video_url = intent.getStringExtra("video");
        String name = intent.getStringExtra("name");
        String author = intent.getStringExtra("author");
        String description = intent.getStringExtra("description");

        llVideoInformation = (LinearLayout) findViewById(R.id.llVideoInformation);
        videoView = (VideoView) findViewById(R.id.fullScreenVideoView);
        imgVideoInfo = (ImageView) findViewById(R.id.imgVideoInfo);
        tvVideoName = (TextView) findViewById(R.id.tvVideoName);
        tvVideoAuthor = (TextView) findViewById(R.id.tvVideoAuthor);
        tvVideoDescription = (TextView) findViewById(R.id.tvVideoDescription);

        tvVideoName.setText(name);
        tvVideoAuthor.setText(author);
        tvVideoDescription.setText(description);

        imgVideoInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (llVideoInformation.getVisibility() == View.GONE)
                {
                    stopPosition = videoView.getCurrentPosition();
                    videoView.pause();
                    llVideoInformation.setVisibility(View.VISIBLE);
                }
                else
                {
                    llVideoInformation.setVisibility(View.GONE);
                    videoView.seekTo(stopPosition);
                    videoView.start();
                }
            }
        });

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        mediaController.setVisibility(View.GONE);
        Uri video = Uri.parse(video_url);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(video);
        videoView.start();

        /*
        Intent intent = new Intent(Intent.ACTION_VIEW );
        intent.setDataAndType(Uri.parse(video_url), "video/*");
        startActivity(intent);
        */
    }

    @Override
    public void onPause() {
        super.onPause();
        stopPosition = videoView.getCurrentPosition(); //stopPosition is an int
        videoView.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        videoView.seekTo(stopPosition);
        videoView.start(); //Or use resume() if it doesn't work. I'm not sure
    }

    @Override
    public void onBackPressed() {
        if(llVideoInformation.getVisibility() == View.GONE)
        {
            super.onBackPressed();
        }
        else
        {
            llVideoInformation.setVisibility(View.GONE);
        }
    }
}
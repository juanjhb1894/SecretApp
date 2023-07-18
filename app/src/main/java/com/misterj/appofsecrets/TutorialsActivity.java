package com.misterj.appofsecrets;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.misterj.appofsecrets.adapters.VideosAdapter;
import com.misterj.appofsecrets.items.TutorialItems;
import com.misterj.appofsecrets.webservice.Tutorials;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TutorialsActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etSearchVideo;
    TextView tvPopular, tvWomen, tvMen, tvTutotialName, tvTutotialAuthor, tvTutotialDescription;
    GridView gridView;
    View hiddenPanel;
    ImageView imgTutorialInfoClose, imgPlayTutorial, imgPlayOutsideTutorial, imgPlayWithTutorial;
    RadioButton rbSaveAsDefault;
    TutorialItems item;
    List<TutorialItems> list;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorials);


        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        etSearchVideo = (EditText) findViewById(R.id.etSearchVideo);
        tvPopular = (TextView) findViewById(R.id.tvPopularVideo);
        tvWomen = (TextView) findViewById(R.id.tvWomenVideo);
        tvMen = (TextView) findViewById(R.id.tvMenVideo);
        tvTutotialName = (TextView) findViewById(R.id.tvTutotialName);
        tvTutotialAuthor = (TextView) findViewById(R.id.tvTutotialAuthor);
        tvTutotialDescription = (TextView) findViewById(R.id.tvTutotialDescription);
        gridView = findViewById(R.id.gridVideo);
        hiddenPanel = findViewById(R.id.hidden_panel);
        imgTutorialInfoClose = (ImageView) findViewById(R.id.imgTutorialInfoClose);
        imgPlayTutorial = (ImageView) findViewById(R.id.imgPlayTutorial);
        imgPlayOutsideTutorial = (ImageView) findViewById(R.id.imgPlayOutsideTutorial);
        imgPlayWithTutorial = (ImageView) findViewById(R.id.imgPlayWithTutorial);

        tvPopular.setOnClickListener(this);
        tvWomen.setOnClickListener(this);
        tvMen.setOnClickListener(this);
        imgTutorialInfoClose.setOnClickListener(this);
        imgPlayTutorial.setOnClickListener(this);
        imgPlayOutsideTutorial.setOnClickListener(this);
        imgPlayWithTutorial.setOnClickListener(this);

        LoadTutorials(0);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                slideUpDown();

                item = list.get(position);

                tvTutotialName.setText(item.getName());
                tvTutotialAuthor.setText(item.getAuthor());
                tvTutotialDescription.setText(item.getDescription());
            }
        });

    }

    public void slideUpDown() {
        if (!isPanelShown()) {
            // Show the panel
            Animation bottomUp = AnimationUtils.loadAnimation(this, R.anim.bottom_up);
            hiddenPanel.startAnimation(bottomUp);
            hiddenPanel.setVisibility(View.VISIBLE);
        }
        else {
            // Hide the Panel
            Animation bottomDown = AnimationUtils.loadAnimation(this, R.anim.bottom_down);
            hiddenPanel.startAnimation(bottomDown);
            hiddenPanel.setVisibility(View.GONE);
        }
    }

    private boolean isPanelShown() {
        return hiddenPanel.getVisibility() == View.VISIBLE;
    }
    private void LoadTutorials(int target)
    {
        if(gridView.getAdapter() != null)
        {
            gridView.setAdapter(null);
        }

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                //Background work here
                ArrayList<Tutorials.VideoTutorial> videoTutorials = new Tutorials().Videos(target);
                list = new ArrayList<>();
                for ( Tutorials.VideoTutorial video: videoTutorials) {
                    list.add(new TutorialItems(video.id, video.name, video.url, video.coverPictureUrl,
                            video.description, video.status, video.author, video.uploadedAt, video.target));
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //UI Thread work here
                        VideosAdapter customAdapter = new VideosAdapter(TutorialsActivity.this, R.layout.item_tutorial, list);
                        gridView.setAdapter(customAdapter);
                    }
                });
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tvPopularVideo:
                tvPopular.setTextColor(getColor(R.color.primary_200));
                tvWomen.setTextColor(getColor(R.color.black));
                tvMen.setTextColor(getColor(R.color.black));

                tvPopular.setTypeface(null, Typeface.BOLD);
                tvWomen.setTypeface(null, Typeface.NORMAL);
                tvMen.setTypeface(null, Typeface.NORMAL);

                LoadTutorials(0);
                break;
            case R.id.tvWomenVideo:
                tvPopular.setTextColor(getColor(R.color.black));
                tvMen.setTextColor(getColor(R.color.black));
                tvWomen.setTextColor(getColor(R.color.primary_200));

                tvPopular.setTypeface(null, Typeface.NORMAL);
                tvWomen.setTypeface(null, Typeface.BOLD);
                tvMen.setTypeface(null, Typeface.NORMAL);

                LoadTutorials(2);
                break;
            case R.id.tvMenVideo:
                tvPopular.setTextColor(getColor(R.color.black));
                tvWomen.setTextColor(getColor(R.color.black));
                tvMen.setTextColor(getColor(R.color.primary_200));

                tvPopular.setTypeface(null, Typeface.NORMAL);
                tvWomen.setTypeface(null, Typeface.NORMAL);
                tvMen.setTypeface(null, Typeface.BOLD);

                LoadTutorials(1);
                break;
            case R.id.imgTutorialInfoClose:
                slideUpDown();
                break;
            case R.id.imgPlayTutorial:
                Intent myIntent = new Intent(TutorialsActivity.this, VideoActivity.class);
                myIntent.putExtra("id", item.getId());
                myIntent.putExtra("name", item.getName());
                myIntent.putExtra("author", item.getAuthor());
                myIntent.putExtra("description", item.getDescription());
                myIntent.putExtra("status", item.getStatus());
                myIntent.putExtra("video", item.getUrl());
                myIntent.putExtra("cover", item.getCoverPictureUrl());
                myIntent.putExtra("target", item.getTarget());
                myIntent.putExtra("uploaded", item.getUploadedAt());
                startActivity(myIntent);
                break;
            case R.id.imgPlayOutsideTutorial:
                Intent videoClient = new Intent(Intent.ACTION_VIEW);
                videoClient.setData(Uri.parse(item.getUrl()));
                startActivity(videoClient);
                break;
            case R.id.imgPlayWithTutorial:
                if(isPackageInstalled("org.videolan.vlc.betav7neon",getApplicationContext().getPackageManager()))
                {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setPackage("org.videolan.vlc.betav7neon");
                    intent.setDataAndType(Uri.parse(item.getUrl()), "application/*");
                    startActivity(intent);
                }
                else if (isPackageInstalled("com.samsung.android.video",getApplicationContext().getPackageManager()))
                {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setPackage("com.samsung.android.video");
                    intent.setDataAndType(Uri.parse(item.getUrl()), "application/*");
                    startActivity(intent);
                }
                else if (isPackageInstalled("com.mxtech.videoplayer.ad",getApplicationContext().getPackageManager()))
                {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setPackage("com.mxtech.videoplayer.ad");
                    intent.setDataAndType(Uri.parse(item.getUrl()), "application/*");
                    startActivity(intent);
                }
                break;
        }
    }

    private boolean isPackageInstalled(String packageName, PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
package com.misterj.appofsecrets.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.widget.ImageView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThumbnailExtract {

    private final String videoUrl;
    private final ImageView mThumbnail;
    private final boolean mIsVideo;
    private MediaMetadataRetriever mmr;

    public ThumbnailExtract(String videoLocalUrl, ImageView thumbnail, boolean isVideo) {
        this.videoUrl = videoLocalUrl;
        mThumbnail = thumbnail;
        mIsVideo = isVideo;
        if (!isVideo) {
            mmr = new MediaMetadataRetriever();
        }
    }
/*
    public Bitmap retriveVideoFrameFromVideo(String videoPath) throws Throwable
    {
        Bitmap bitmap = null;
        MediaMetadataRetriever mediaMetadataRetriever = null;
        try
        {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            if (Build.VERSION.SDK_INT >= 14)
                mediaMetadataRetriever.setDataSource(videoPath, new HashMap<String, String>());
            else
                mediaMetadataRetriever.setDataSource(videoPath);
            //   mediaMetadataRetriever.setDataSource(videoPath);
            bitmap = mediaMetadataRetriever.getFrameAtTime();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Throwable("Exception in retriveVideoFrameFromVideo(String videoPath)" + e.getMessage());

        } finally {
            if (mediaMetadataRetriever != null) {
                mediaMetadataRetriever.release();
            }
        }
        return bitmap;
    }

 */

    private Bitmap getBitmap(String fileUrl) {
        mmr.setDataSource(fileUrl);
        byte[] data = mmr.getEmbeddedPicture();
        Bitmap bitmap = null;
        // convert the byte array to a bitmap
        if (data != null) {
            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

        }
        return bitmap != null ? ScalingUtilities.createScaledBitmap(bitmap, 40, 40, ScalingUtilities.ScalingLogic.FIT) : bitmap;
    }

    public void SetThumbnail()
    {
        final Bitmap[] thumb = {null};
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                //Background work here
                if (!mIsVideo) {
                    thumb[0] = getBitmap(videoUrl);
                } else {
                    thumb[0] = ThumbnailUtils.createVideoThumbnail(videoUrl,
                            MediaStore.Images.Thumbnails.MINI_KIND);
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //UI Thread work here
                        if (thumb[0] != null) {
                            mThumbnail.setImageBitmap(thumb[0]);
                        }
                    }
                });
            }
        });

    }
}

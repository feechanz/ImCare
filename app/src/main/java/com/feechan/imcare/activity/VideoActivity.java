package com.feechan.imcare.activity;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.MediaController;
import android.widget.VideoView;

import com.feechan.imcare.R;
import com.feechan.imcare.entity.Video;


public class VideoActivity extends AppCompatActivity {



    Video video;
    ProgressDialog pDialog;
    VideoView penyakitVideoView;
    String VideoURL = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_video);
        penyakitVideoView = (VideoView) findViewById(R.id.penyakitVideoView);
        penyakitVideoView.setZOrderOnTop(true);


        video = (Video)getIntent().getSerializableExtra("video");
        VideoURL = video.getUrlvideo();
        Log.d("care","Video URL >>> "+VideoURL);
        bufferVideo();
    }



    private void bufferVideo(){

        // Create a progressbar
        pDialog = new ProgressDialog(VideoActivity.this);
        // Set progressbar title
        pDialog.setTitle("Android Video Streaming Tutorial");
        // Set progressbar message
        pDialog.setMessage("Buffering...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        // Show progressbar
        pDialog.show();
        try {
            // Start the MediaController
            MediaController mediacontroller = new MediaController(VideoActivity.this);
            mediacontroller.setAnchorView(penyakitVideoView);
            // Get the URL from String VideoURL
            Uri video = Uri.parse(VideoURL);
            penyakitVideoView.setMediaController(mediacontroller);
            penyakitVideoView.setVideoURI(video);

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        penyakitVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                pDialog.dismiss();
                penyakitVideoView.start();
            }
        });
        penyakitVideoView.requestFocus();
    }
}

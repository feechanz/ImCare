package com.feechan.imcare;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    ProgressDialog pDialog;
    VideoView penyakitVideoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_video);
        penyakitVideoView = (VideoView) findViewById(R.id.penyakitVideoView);
        penyakitVideoView.setZOrderOnTop(true);

        bufferVideo();
    }

    String VideoURL = "http://192.168.43.22/ImCareProject/videos/wecantstop.mp4";

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

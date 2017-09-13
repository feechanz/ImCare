package com.feechan.imcare.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.feechan.imcare.R;
import com.feechan.imcare.adapter.VideoListAdapter;
import com.feechan.imcare.entity.Penyakit;
import com.feechan.imcare.entity.Video;
import com.feechan.imcare.global.AppHelper;
import com.feechan.imcare.global.DatabaseHelper;
import com.feechan.imcare.services.VideoService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class ListVideoActivity extends AppCompatActivity {

    DatabaseHelper db;
    RecyclerView videoRecyclerView;
    VideoListAdapter mAdapter;
    List<Video> videos;
    Penyakit penyakit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_video);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        penyakit = (Penyakit) getIntent().getSerializableExtra("penyakit");

        db = new DatabaseHelper(this);

        videoRecyclerView = (RecyclerView) findViewById(R.id.videoRecyclerView);
        videos = new ArrayList<Video>();

        mAdapter = new VideoListAdapter(videos,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        videoRecyclerView.setLayoutManager(mLayoutManager);
        videoRecyclerView.setItemAnimator(new DefaultItemAnimator());
        videoRecyclerView.setAdapter(mAdapter);

        requestVideo();

        //load from SQLite
        loadFromDB();
    }

    private void requestVideo(){
        if(penyakit != null) {
            final Response.ErrorListener onPostsError = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (error.networkResponse != null) {
                        Log.e("care", "Error >>> " + error.networkResponse.statusCode + "");
                    } else {
                        Log.e("care", "Error >>> " + error);
                    }

                }
            };
            final Response.Listener<String> onPostsLoaded = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("care", response);
                    //ubah response ke list penyakit
                    synchronizedVideo(response);
                }
            };

            VideoService.getAllVideo(this, penyakit.getKdpenyakit(), onPostsLoaded, onPostsError);
        }
    }

    private void synchronizedVideo(String response){
        Gson gson = AppHelper.getGson();
        List<Video> tmpVideos = null;
        boolean error = false;
        try {
            //parsing json to list video
            tmpVideos = gson.fromJson(response, new TypeToken<List<Video>>() {
            }.getType());
        }
        catch (Exception e){
            //set error, dont continue syncronize
            error = true;
            Log.d("care","error happen "+e.getMessage());
        }
        if(!error) {
            //check if any update
            boolean update = false;

            //syncronize list
            //check removed item or update item
            for(Video item : db.getAllVideo(penyakit.getKdpenyakit())){

                Video search = null;
                //search in response
                for(Video itemResponse : tmpVideos){
                    if(item.getNovideo() == itemResponse.getNovideo()){
                        search = itemResponse;
                        break;
                    }
                }

                if(search == null){
                    //not found
                    //delete item
                    db.removeVideo(item.getNovideo());
                    update = true;
                } else {
                    //update item
                    this.updateVideo(item,search);
                    update = true;
                }
            }

            //check new item
            for(Video item : tmpVideos){
                Video search = db.getVideo(item.getNovideo());
                if(search == null){
                    //not found
                    //add new item
                    db.addVideo(item);
                    update = true;
                }
            }
            if(update) {
                loadFromDB();
            }
        }
    }

    private void updateVideo(Video before, Video after){
        boolean update = false;
        if(!before.getJudulvideo().equals(after.getJudulvideo())){
            Log.d("care","update judulvideo "+before.getJudulvideo() +" to "+after.getJudulvideo());
            update = true;
        }
        if(!before.getUrlvideo().equals(after.getUrlvideo())){
            Log.d("care","update urlvideo "+before.getUrlvideo() +" to "+after.getUrlvideo());
            update = true;
        }
        if(before.getKdpenyakit() != after.getKdpenyakit()){
            Log.d("care","update kdpenyakit "+before.getKdpenyakit() +" to "+after.getKdpenyakit());
            update = true;
        }

        if(update) {
            db.updateVideo(after);
        }
    }

    private void loadFromDB(){
        if(penyakit != null) {
            List<Video> tmp = db.getAllVideo(penyakit.getKdpenyakit());
            videos.clear();
            for (Video item : tmp) {
                videos.add(item);
            }
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }
}


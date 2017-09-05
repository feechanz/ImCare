package com.feechan.imcare.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.feechan.imcare.R;
import com.feechan.imcare.adapter.VideoListAdapter;
import com.feechan.imcare.entity.Video;

import java.util.ArrayList;
import java.util.List;

public class ListVideoActivity extends AppCompatActivity {

    RecyclerView videoRecyclerView;
    VideoListAdapter mAdapter;
    List<Video> videos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_video);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        db = new DatabaseHelper(this);

        videoRecyclerView = (RecyclerView) findViewById(R.id.videoRecyclerView);
        videos = new ArrayList<Video>();
        videos.add(new Video(1,"Gejala Artikel","url artikel 1",1));
        videos.add(new Video(2,"Diagnosa Artikel","url artikel 1",1));
        videos.add(new Video(3,"Penyembuhan Artikel","url artikel 1",1));
        videos.add(new Video(4,"Obat Artikel","url artikel 1",1));


        mAdapter = new VideoListAdapter(videos,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        videoRecyclerView.setLayoutManager(mLayoutManager);
        videoRecyclerView.setItemAnimator(new DefaultItemAnimator());
        videoRecyclerView.setAdapter(mAdapter);

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


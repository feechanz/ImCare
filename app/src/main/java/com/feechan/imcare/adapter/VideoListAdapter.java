package com.feechan.imcare.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.feechan.imcare.R;
import com.feechan.imcare.activity.VideoActivity;
import com.feechan.imcare.entity.Artikel;
import com.feechan.imcare.entity.Video;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Feechan on 8/21/2017.
 */

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.MyViewHolder> {

    private Activity activity;
    private List<Video> datas;

    public VideoListAdapter(List<Video> datas, Activity activity){
        this.datas = datas;
        this.activity = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public Video video;
        public TextView judulVideoTextView;


        public MyViewHolder(View view) {
            super(view);
            judulVideoTextView = (TextView) view.findViewById(R.id.judulVideoTextView);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("care","item tapped! "+video.getJudulvideo());
                    Intent intent = new Intent(activity, VideoActivity.class);
                    intent.putExtra("video", video);
                    activity.startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Video tmpData = datas.get(position);
        holder.judulVideoTextView.setText(tmpData.getJudulvideo());
        holder.video = tmpData;
//        AppHelper.setImageFromUrl(holder.penyakitImageView,tmpData.getImage_url(),activity);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_video, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}

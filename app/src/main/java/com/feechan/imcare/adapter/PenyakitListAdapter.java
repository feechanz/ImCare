package com.feechan.imcare.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.feechan.imcare.R;
import com.feechan.imcare.activity.TitleDeseaseActivity;
import com.feechan.imcare.entity.Penyakit;
import com.feechan.imcare.global.AppHelper;

import java.util.List;

/**
 * Created by Feechan on 8/21/2017.
 */

public class PenyakitListAdapter extends RecyclerView.Adapter<PenyakitListAdapter.MyViewHolder> {

    private Activity activity;
    private List<Penyakit> datas;

    public PenyakitListAdapter(List<Penyakit> datas,Activity activity){
        this.datas = datas;
        this.activity = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public Penyakit penyakit;
        public TextView penyakitTextView;
        public ImageView penyakitImageView;

        public MyViewHolder(View view) {
            super(view);
            penyakitTextView = (TextView) view.findViewById(R.id.penyakitTextView);
            penyakitImageView = (ImageView) view.findViewById(R.id.penyakitImageView);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("care","item tapped! "+penyakit.getNmpenyakit());
                    Intent intent = new Intent(activity, TitleDeseaseActivity.class);
                    intent.putExtra("penyakit",penyakit);
                    activity.startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Penyakit tmpData = datas.get(position);
        holder.penyakitTextView.setText(tmpData.getNmpenyakit());
        holder.penyakit = tmpData;
        AppHelper.setImageFromUrl(holder.penyakitImageView,tmpData.getImage_url(),activity);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_penyakit, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}

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
import com.feechan.imcare.entity.Artikel;
import com.feechan.imcare.entity.Penyakit;
import com.feechan.imcare.global.AppHelper;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Feechan on 8/21/2017.
 */

public class ArtikelListAdapter extends RecyclerView.Adapter<ArtikelListAdapter.MyViewHolder> {

    private Activity activity;
    private List<Artikel> datas;

    public ArtikelListAdapter(List<Artikel> datas, Activity activity){
        this.datas = datas;
        this.activity = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public Artikel artikel;
        public TextView judulTextView;


        public MyViewHolder(View view) {
            super(view);
            judulTextView = (TextView) view.findViewById(R.id.judulTextView);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("care","item tapped! "+artikel.getJudulartikel());
//                    Intent intent = new Intent(activity, TitleDeseaseActivity.class);
//                    intent.putExtra("artikel", (Serializable) artikel);
//                    activity.startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Artikel tmpData = datas.get(position);
        holder.judulTextView.setText(tmpData.getJudulartikel());
        holder.artikel = tmpData;
//        AppHelper.setImageFromUrl(holder.penyakitImageView,tmpData.getImage_url(),activity);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_artikel, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}

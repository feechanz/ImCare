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
import com.feechan.imcare.activity.DetailHospitalActivity;
import com.feechan.imcare.activity.TitleDeseaseActivity;
import com.feechan.imcare.entity.Rs;

import java.util.List;

/**
 * Created by Feechan on 9/12/2017.
 */

public class HospitalListAdapter extends RecyclerView.Adapter<HospitalListAdapter.MyViewHolder> {

    private Activity activity;
    private List<Rs> datas;

    public HospitalListAdapter(List<Rs> datas, Activity activity){
        this.datas = datas;
        this.activity = activity;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public Rs hospital;
        public TextView namaTextView;
        public TextView alamatTextView;
        public TextView noTelpTextView;

        public MyViewHolder(View view) {
            super(view);
            namaTextView = (TextView) view.findViewById(R.id.namaTextView);
            alamatTextView = (TextView) view.findViewById(R.id.alamatTextView);
            noTelpTextView = (TextView) view.findViewById(R.id.noTelpTextView);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("care","item tapped! "+ hospital.getNmrs());
                    Intent intent = new Intent(activity, DetailHospitalActivity.class);
                    intent.putExtra("hospital",hospital);
                    activity.startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(HospitalListAdapter.MyViewHolder holder, int position) {
        Rs tmpData = datas.get(position);
        holder.namaTextView.setText(tmpData.getNmrs());
        holder.alamatTextView.setText(tmpData.getAlmt());
        holder.noTelpTextView.setText(tmpData.getNmrs());
        holder.hospital = tmpData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_hospital, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}


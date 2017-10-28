package com.feechan.imcare.activity;

import android.app.ProgressDialog;
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
import com.feechan.imcare.adapter.HospitalListAdapter;
import com.feechan.imcare.entity.Rs;
import com.feechan.imcare.global.AppHelper;
import com.feechan.imcare.global.DatabaseHelper;
import com.feechan.imcare.services.RsService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import static com.feechan.imcare.R.id.hospitalRecyclerView;

public class ListHospitalDiseaseActivity extends AppCompatActivity {
    DatabaseHelper db;
    RecyclerView hospitalRecyclerView;
    List<Rs> hospitals;
    HospitalListAdapter mAdapter;
    String kota;
    int kdpenyakit;
    ProgressDialog pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hospital_disease);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        kdpenyakit = getIntent().getExtras().getInt("kdpenyakit",0);
        pb = new ProgressDialog(this);
        pb.setCancelable(false);
        pb.setTitle("Loading");

        db = new DatabaseHelper(this);

        kota = getIntent().getStringExtra("kota");
        hospitals = new ArrayList<Rs>();

        mAdapter = new HospitalListAdapter(hospitals,this);

        hospitalRecyclerView = (RecyclerView) findViewById(R.id.hospitalRecyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        hospitalRecyclerView.setLayoutManager(mLayoutManager);
        hospitalRecyclerView.setItemAnimator(new DefaultItemAnimator());
        hospitalRecyclerView.setAdapter(mAdapter);

        requestHospital();

        //load from SQLite
        //loadFromDB();
    }

    private void requestHospital(){
        final Response.ErrorListener onPostsError = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pb.dismiss();
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
                pb.dismiss();
                Log.i("care", "response >> "+ response);
                Gson gson = AppHelper.getGson();
                List<Rs> tmpRss = null;
                //ubah response ke list artikel
                boolean error = false;
                try {
                    //parsing json to list video
                    tmpRss = gson.fromJson(response, new TypeToken<List<Rs>>() {
                    }.getType());
                }
                catch (Exception e){
                    //set error, dont continue syncronize
                    error = true;
                    Log.d("care","error happen "+e.getMessage());
                }
                if(!error){
                    hospitals.clear();
                    for(Rs item : tmpRss){
                        hospitals.add(item);
                    }
                    mAdapter.notifyDataSetChanged();
                }
            }
        };
        pb.show();
        RsService.getAllRsPenyakit(this, kdpenyakit, onPostsLoaded, onPostsError);
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

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
import com.feechan.imcare.adapter.HospitalListAdapter;
import com.feechan.imcare.entity.Rs;
import com.feechan.imcare.global.AppHelper;
import com.feechan.imcare.global.DatabaseHelper;
import com.feechan.imcare.services.RsService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class ListHospitalActivity extends AppCompatActivity {

    DatabaseHelper db;
    RecyclerView hospitalRecyclerView;
    List<Rs> hospitals;
    HospitalListAdapter mAdapter;
    String kota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hospital);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        loadFromDB();
    }

    private void requestHospital(){
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
                Log.i("care", "response >> "+ response);
                //ubah response ke list artikel
                synchronizedRs(response);
            }
        };

        RsService.getAllRs(this, onPostsLoaded, onPostsError);
    }

    private void synchronizedRs(String response){
        Gson gson = AppHelper.getGson();
        List<Rs> tmpRss = null;
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
        if(!error) {
            //check if any update
            boolean update = false;

            //syncronize list
            //check removed item or update item
            for(Rs item : db.getAllRs()){

                Rs search = null;
                //search in response
                for(Rs itemResponse : tmpRss){
                    if(item.getIdrs() == itemResponse.getIdrs()){
                        search = itemResponse;
                        break;
                    }
                }

                if(search == null){
                    //not found
                    //delete item
                    db.removeRs(item.getIdrs());
                    update = true;
                } else {
                    //update item
                    this.updateRs(item,search);
                    update = true;
                }
            }

            //check new item
            for(Rs item : tmpRss){
                Rs search = db.getRs(item.getIdrs());
                if(search == null){
                    //not found
                    //add new item
                    Log.d("care","add rs >> "+item.getIdrs());
                    db.addRs(item);
                    update = true;
                }
            }
            if(update) {
                loadFromDB();
            }
        }
    }

    private void updateRs(Rs before, Rs after){
        boolean update = false;
        if(!before.getKdrs().equals(after.getKdrs())){//1
            Log.d("care","update kdrs "+before.getKdrs() +" to "+after.getKdrs());
            update = true;
        }
        if(!before.getNmrs().equals(after.getNmrs())){//2
            Log.d("care","update nmrs "+before.getNmrs() +" to "+after.getNmrs());
            update = true;
        }
        if(!before.getAlmt().equals(after.getAlmt())){//3
            Log.d("care","update almt "+before.getAlmt() +" to "+after.getAlmt());
            update = true;
        }
        if(!before.getKotars().equals(after.getKotars())){//4
            Log.d("care","update kotars "+before.getKotars() +" to "+after.getKotars());
            update = true;
        }
        if(!before.getKdposrs().equals(after.getKdposrs())){//5
            Log.d("care","update kdposrs "+before.getKdposrs() +" to "+after.getKdposrs());
            update = true;
        }
        if(!before.getKelurahanrs().equals(after.getKelurahanrs())){//6
            Log.d("care","update kelurahanrs "+before.getKelurahanrs() +" to "+after.getKelurahanrs());
            update = true;
        }
        if(!before.getKecamatanrs().equals(after.getKecamatanrs())){//7
            Log.d("care","update kecamatanrs "+before.getKecamatanrs() +" to "+after.getKecamatanrs());
            update = true;
        }
        if(!before.getTelprs().equals(after.getTelprs())){//8
            Log.d("care","update telprs "+before.getTelprs() +" to "+after.getTelprs());
            update = true;
        }
        if(!before.getFaxrs().equals(after.getFaxrs())){//9
            Log.d("care","update faxrs "+before.getFaxrs() +" to "+after.getFaxrs());
            update = true;
        }
        if(!before.getWebrs().equals(after.getWebrs())){//10
            Log.d("care","update webrs "+before.getWebrs() +" to "+after.getWebrs());
            update = true;
        }
        if(!before.getHumasrs().equals(after.getHumasrs())){//11
            Log.d("care","update humasrs "+before.getHumasrs() +" to "+after.getHumasrs());
            update = true;
        }
        if(before.getLatitude() != after.getLatitude()){//12
            Log.d("care","update latitude "+before.getLatitude() +" to "+after.getLatitude());
            update = true;
        }
        if(before.getLongitude() != after.getLongitude()){//13
            Log.d("care","update longitude "+before.getLongitude() +" to "+after.getLongitude());
            update = true;
        }
        if(before.getKdpenyakit() != after.getKdpenyakit()){//14
            Log.d("care","update kdpenyakit "+before.getKdpenyakit() +" to "+after.getKdpenyakit());
            update = true;
        }

        if(update) {
            db.updateRs(after);
        }
    }

    private void loadFromDB(){
        if(hospitals != null) {
            List<Rs> tmp = db.getAllRs();
            hospitals.clear();
            for (Rs item : tmp) {
                if(item.getKotars().toLowerCase().contains(kota.toLowerCase())) {
                    hospitals.add(item);
                }
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

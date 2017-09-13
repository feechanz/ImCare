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
import com.feechan.imcare.adapter.PenyakitListAdapter;
import com.feechan.imcare.entity.Penyakit;
import com.feechan.imcare.global.AppHelper;
import com.feechan.imcare.global.DatabaseHelper;
import com.feechan.imcare.services.PenyakitService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class DeseaseActivity extends AppCompatActivity {

    DatabaseHelper db;
    RecyclerView penyakitRecyclerView;
    PenyakitListAdapter mAdapter;
    List<Penyakit> penyakits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desease);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DatabaseHelper(this);

        penyakitRecyclerView = (RecyclerView) findViewById(R.id.penyakitRecyclerView);
        penyakits = new ArrayList<Penyakit>();


        mAdapter = new PenyakitListAdapter(penyakits,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        penyakitRecyclerView.setLayoutManager(mLayoutManager);
        penyakitRecyclerView.setItemAnimator(new DefaultItemAnimator());
        penyakitRecyclerView.setAdapter(mAdapter);

        requestPenyakit();

        //load from SQLite
        loadFromDB();
    }

    private void loadFromDB(){
        List<Penyakit> tmp = db.getPenyakit();
        penyakits.clear();
        for(Penyakit item : tmp){
            penyakits.add(item);
        }
        mAdapter.notifyDataSetChanged();
    }

    private void requestPenyakit(){
        final Response.ErrorListener onPostsError = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error.networkResponse != null) {
                    Log.e("care", "Error >>> " + error.networkResponse.statusCode + "");
                }else{
                    Log.e("care", "Error >>> " + error);
                }

            }
        };
        final Response.Listener<String> onPostsLoaded = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("care", response);
                //ubah response ke list penyakit
                synchronizeDesease(response);
            }
        };

        PenyakitService.getAllPenyakit(this,onPostsLoaded,onPostsError);
    }

    private void synchronizeDesease(String response){
        Gson gson = AppHelper.getGson();
        List<Penyakit> tmpPenyakits = null;
        boolean error = false;
        try {
            //parsing json to list penyakit
            tmpPenyakits = gson.fromJson(response, new TypeToken<List<Penyakit>>() {
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
            for(Penyakit item : db.getPenyakit()){

                Penyakit search = null;
                //search in response
                for(Penyakit itemResponse : tmpPenyakits){
                    if(item.getKdpenyakit() == itemResponse.getKdpenyakit()){
                        search = itemResponse;
                        break;
                    }
                }

                if(search == null){
                    //not found
                    //delete item
                    db.removePenyakit(item.getKdpenyakit());
                    update = true;
                } else {
                    //update item
                    this.updatePenyakit(item,search);
                    update = true;
                }
            }

            //check new item
            for(Penyakit item : tmpPenyakits){
                Penyakit search = db.getPenyakit(item.getKdpenyakit());
                if(search == null){
                    //not found
                    //add new item
                    db.addPenyakit(item);
                    update = true;
                }
            }
            if(update) {
                loadFromDB();
            }
        }
    }

    private void updatePenyakit(Penyakit before, Penyakit after){
        boolean update = false;
        if(!before.getNmpenyakit().equals(after.getNmpenyakit())){
            Log.d("care","update nmpenyakit "+before.getNmpenyakit() +" to "+after.getNmpenyakit());
            update = true;
        }
        if(!before.getDespenyakit().equals(after.getDespenyakit())){
            Log.d("care","update despenyakit "+before.getDespenyakit() +" to "+after.getDespenyakit());
            update = true;
        }
        if(!before.getFketurunan().equals(after.getFketurunan())){
            Log.d("care","update fketurunan "+before.getDespenyakit() +" to "+after.getDespenyakit());
            update = true;
        }
        if(!before.getMenular().equals(after.getMenular())){
            Log.d("care","update menular "+before.getMenular() +" to "+after.getMenular());
            update = true;
        }
        if(!before.getKronis().equals(after.getKronis())){
            Log.d("care","update kronis "+before.getKronis() +" to "+after.getKronis());
            update = true;
        }
        if(!before.getImage_url().equals(after.getImage_url())){
            Log.d("care","update imageurl "+before.getImage_url() +" to "+after.getImage_url());
            update = true;
        }
        if(!before.getVideo_url().equals(after.getVideo_url())){
            Log.d("care","update videourl "+before.getVideo_url() +" to "+after.getVideo_url());
            update = true;
        }
        if(update) {
            db.updatePenyakit(after);
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

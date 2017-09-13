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
import com.feechan.imcare.adapter.ArtikelListAdapter;
import com.feechan.imcare.entity.Artikel;
import com.feechan.imcare.entity.Penyakit;
import com.feechan.imcare.global.AppHelper;
import com.feechan.imcare.global.DatabaseHelper;
import com.feechan.imcare.services.ArtikelService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.util.ArrayList;
import java.util.List;

public class ListArtikelActivity extends AppCompatActivity {

    DatabaseHelper db;
    RecyclerView artikelRecyclerView;
    ArtikelListAdapter mAdapter;
    List<Artikel> artikels;
    Penyakit penyakit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_artikel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        penyakit = (Penyakit) getIntent().getSerializableExtra("penyakit");

        db = new DatabaseHelper(this);

        artikelRecyclerView = (RecyclerView) findViewById(R.id.artikelRecyclerView);
        artikels = new ArrayList<Artikel>();

        mAdapter = new ArtikelListAdapter(artikels,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        artikelRecyclerView.setLayoutManager(mLayoutManager);
        artikelRecyclerView.setItemAnimator(new DefaultItemAnimator());
        artikelRecyclerView.setAdapter(mAdapter);

        requestArtikel();

        //load from SQLite
        loadFromDB();
    }

    private void requestArtikel(){
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
                    Log.i("care", "response >> "+ response);
                    //ubah response ke list artikel
                    synchronizedArtikel(response);
                }
            };

            ArtikelService.getAllArtikel(this, penyakit.getKdpenyakit(), onPostsLoaded, onPostsError);
        }
    }

    private void synchronizedArtikel(String response){
        Gson gson = AppHelper.getGson();
        List<Artikel> tmpArtikels = null;
        boolean error = false;
        try {
            //parsing json to list video
            tmpArtikels = gson.fromJson(response, new TypeToken<List<Artikel>>() {
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
            for(Artikel item : db.getAllArtikel(penyakit.getKdpenyakit())){

                Artikel search = null;
                //search in response
                for(Artikel itemResponse : tmpArtikels){
                    if(item.getNoartikel() == itemResponse.getNoartikel()){
                        search = itemResponse;
                        break;
                    }
                }

                if(search == null){
                    //not found
                    //delete item
                    db.removeArtikel(item.getNoartikel());
                    update = true;
                } else {
                    //update item
                    this.updateArtikel(item,search);
                    update = true;
                }
            }

            //check new item
            for(Artikel item : tmpArtikels){
                Artikel search = db.getArtikel(item.getNoartikel());
                if(search == null){
                    //not found
                    //add new item
                    db.addArtikel(item);
                    update = true;
                }
            }
            if(update) {
                loadFromDB();
            }
        }
    }

    private void updateArtikel(Artikel before, Artikel after){
        boolean update = false;
        if(!before.getJudulartikel().equals(after.getJudulartikel())){
            Log.d("care","update judulartikel "+before.getJudulartikel() +" to "+after.getJudulartikel());
            update = true;
        }
        if(!before.getContentartikel().equals(after.getContentartikel())){
            Log.d("care","update contentartikel "+before.getContentartikel() +" to "+after.getContentartikel());
            update = true;
        }
        if(before.getKdpenyakit() != after.getKdpenyakit()){
            Log.d("care","update kdpenyakit "+before.getKdpenyakit() +" to "+after.getKdpenyakit());
            update = true;
        }

        if(update) {
            db.updateArtikel(after);
        }
    }

    private void loadFromDB(){
        if(penyakit != null) {
            List<Artikel> tmp = db.getAllArtikel(penyakit.getKdpenyakit());
            artikels.clear();
            for (Artikel item : tmp) {
                artikels.add(item);
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


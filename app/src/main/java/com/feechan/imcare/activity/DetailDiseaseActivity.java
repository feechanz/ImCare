package com.feechan.imcare.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.feechan.imcare.R;

public class DetailDiseaseActivity extends AppCompatActivity {

    int kdpenyakit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_disease);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        kdpenyakit = getIntent().getExtras().getInt("kdpenyakit",0);
        Log.d("care","detail kdpenyakit >> "+kdpenyakit);
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

package com.feechan.imcare.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import com.feechan.imcare.R;
import com.feechan.imcare.entity.Penyakit;
import com.feechan.imcare.global.AppHelper;

public class DetailDiseaseActivity extends AppCompatActivity {

    ImageView penyakitImageView;
    AppCompatTextView titleTextView, descriptionTextView;
    Penyakit penyakit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_disease);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        penyakit = (Penyakit) getIntent().getSerializableExtra("penyakit");
        Log.d("care","detail kdpenyakit >> "+penyakit.getKdpenyakit());

        penyakitImageView = (ImageView) findViewById(R.id.penyakitImageView);
        titleTextView = (AppCompatTextView) findViewById(R.id.titleTextView);
        descriptionTextView = (AppCompatTextView) findViewById(R.id.descriptionTextView);

        titleTextView.setText(penyakit.getNmpenyakit());
        descriptionTextView.setText(penyakit.getDespenyakit());
        AppHelper.setImageFromUrl(penyakitImageView,penyakit.getImage_url(),this);
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

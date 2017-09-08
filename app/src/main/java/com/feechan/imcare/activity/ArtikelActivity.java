package com.feechan.imcare.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.feechan.imcare.R;
import com.feechan.imcare.entity.Artikel;
import com.feechan.imcare.entity.Penyakit;

public class ArtikelActivity extends AppCompatActivity {

    Artikel artikel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        artikel = (Artikel) getIntent().getSerializableExtra("artikel");

        WebView wv = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wv.loadUrl(artikel.getContentartikel());
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

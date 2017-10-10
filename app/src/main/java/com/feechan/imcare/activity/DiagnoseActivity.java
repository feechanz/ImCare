package com.feechan.imcare.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.feechan.imcare.R;

public class DiagnoseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnose);
        WebView wv = (WebView) findViewById(R.id.webviewDiagnose);
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wv.loadUrl("https://imcare.000webhostapp.com/view/pertanyaandiagnosa.php");
    }
}

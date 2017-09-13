package com.feechan.imcare.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.feechan.imcare.R;

public class HospitalActivity extends AppCompatActivity {

    Button jakartaButton,bogorButton,depokButton,tangerangButton,bekasiButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        jakartaButton = (Button) findViewById(R.id.jakartaButton);
        bogorButton = (Button) findViewById(R.id.bogorButton);
        depokButton = (Button) findViewById(R.id.depokButton);
        tangerangButton = (Button) findViewById(R.id.tangerangButton);
        bekasiButton = (Button) findViewById(R.id.bekasiButton);

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kota="";
                switch (v.getId()){
                    case R.id.jakartaButton:
                        kota = "jakarta";
                        break;
                    case R.id.bogorButton:
                        kota = "bogor";
                        break;
                    case R.id.depokButton:
                        kota = "depok";
                        break;
                    case R.id.tangerangButton:
                        kota = "tangerang";
                        break;
                    case R.id.bekasiButton:
                        kota = "bekasi";
                        break;
                    default:
                        break;
                }
                clickAction(kota);
            }
        };

        jakartaButton.setOnClickListener(click);
        bogorButton.setOnClickListener(click);
        depokButton.setOnClickListener(click);
        tangerangButton.setOnClickListener(click);
        bekasiButton.setOnClickListener(click);
    }

    public void clickAction(String kota){
        Intent intent = new Intent(this, ListHospitalActivity.class);
        intent.putExtra("kota",kota);
        startActivity(intent);
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

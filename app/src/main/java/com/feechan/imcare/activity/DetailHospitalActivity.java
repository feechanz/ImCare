package com.feechan.imcare.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.MenuItem;
import android.view.View;

import com.feechan.imcare.R;
import com.feechan.imcare.entity.Rs;

public class DetailHospitalActivity extends AppCompatActivity {

    AppCompatTextView nameTextView, alamatTextView, kotaTextView, teleponTextView, faxTextView, webTextView, humasTextView;
    AppCompatButton mapsButton;

    Rs rs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hospital);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rs = (Rs) getIntent().getSerializableExtra("hospital");

        nameTextView = (AppCompatTextView) findViewById(R.id.nameTextView);
        alamatTextView = (AppCompatTextView) findViewById(R.id.alamatTextView);
        kotaTextView = (AppCompatTextView) findViewById(R.id.kotaTextView);
        teleponTextView = (AppCompatTextView) findViewById(R.id.teleponTextView);
        faxTextView = (AppCompatTextView) findViewById(R.id.faxTextView);
        webTextView = (AppCompatTextView) findViewById(R.id.webTextView);
        humasTextView = (AppCompatTextView) findViewById(R.id.humasTextView);
        mapsButton = (AppCompatButton) findViewById(R.id.mapsButton);


        if(rs!=null) {
            nameTextView.setText(rs.getNmrs());
            alamatTextView.setText(rs.getAlmt() + ", Kelurahan " + rs.getKelurahanrs() + ", Kecamatan " + rs.getKecamatanrs() + ", Kode Pos " + rs.getKdposrs() + ", Kota " + rs.getKotars());
            teleponTextView.setText("Telepon : " + rs.getTelprs());
            faxTextView.setText("Fax : " + rs.getFaxrs());
            webTextView.setText(rs.getWebrs());
            humasTextView.setText("Humas : " + rs.getHumasrs());

            mapsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openMapAction();
                }
            });
        }
    }

    private void openMapAction(){
        Intent intent = new Intent(this,DetailHospitalMapsActivity.class);
        intent.putExtra("hospital",rs);
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

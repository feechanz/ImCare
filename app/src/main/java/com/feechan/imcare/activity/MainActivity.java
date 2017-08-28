package com.feechan.imcare.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.feechan.imcare.R;

public class MainActivity extends AppCompatActivity {

    Button informasiPenyakitButton,informasiRumahSakitButton,diagnosaButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        informasiPenyakitButton = (Button) findViewById(R.id.informasiPenyakitButton);
        informasiRumahSakitButton = (Button) findViewById(R.id.informasiRumahSakitButton);
        diagnosaButton = (Button) findViewById(R.id.diagnosaButton);

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton((Button) v);
            }
        };
        informasiPenyakitButton.setOnClickListener(click);
        informasiRumahSakitButton.setOnClickListener(click);
        diagnosaButton.setOnClickListener(click);

    }

    public void clickButton(Button sender){
        switch(sender.getId()){
            case R.id.informasiPenyakitButton:
                Log.d("care","informasi penyakit klik");
                Intent i1 = new Intent(this, DeseaseActivity.class);
                startActivity(i1);
                break;
            case R.id.informasiRumahSakitButton:
                Log.d("care","informasi rumah sakit button klik");
                Intent i2 = new Intent(this, HospitalActivity.class);
                startActivity(i2);
                break;
            case R.id.diagnosaButton:
                Log.d("care","diagnosa button klik");
                break;
            default:
                Log.e("care","there's not found button");
                break;
        }
    }
}

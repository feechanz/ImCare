package com.feechan.imcare.activity;

import android.app.ActionBar;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.feechan.imcare.R;
import com.feechan.imcare.entity.Rs;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetailHospitalMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    float zoomLevel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar ab = getActionBar();
        if(ab!=null){
            ab.setDisplayHomeAsUpEnabled(true);
        }
        setContentView(R.layout.activity_detail_hospital_maps);

        rs = (Rs)getIntent().getSerializableExtra("hospital");
        zoomLevel = 17.0f;
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    Rs rs;
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // Add a marker in Sydney and move the camera
        if(rs != null) {
            LatLng currentPosition = new LatLng(rs.getLatitude(), rs.getLongitude());
            mMap.addMarker(new MarkerOptions().position(currentPosition).title(rs.getNmrs()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(currentPosition));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(currentPosition));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(zoomLevel));
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

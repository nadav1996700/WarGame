package com.example.wargame_v2;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        ArrayList<VictoryData> list = My_SP.getInstance().loadData();
        for(VictoryData data: list) {
            //LatLng location = new LatLng(60, 80);
            LatLng location = new LatLng(data.get_location().getLatitude(), data.get_location().getLongitude());
            googleMap.addMarker(new MarkerOptions().position(location).title(""));
        }

        LatLng sydney = new LatLng(-34, 151);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
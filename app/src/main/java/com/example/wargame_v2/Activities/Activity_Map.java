package com.example.wargame_v2.Activities;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.example.wargame_v2.Utils.My_SP;
import com.example.wargame_v2.R;
import com.example.wargame_v2.Utils.VictoryData;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Activity_Map extends FragmentActivity implements OnMapReadyCallback {

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
            if(data.get_location() != null) {
                LatLng location = new LatLng(data.get_location().getLatitude(), data.get_location().getLongitude());
                googleMap.addMarker(new MarkerOptions().position(location).title(""));
            }
        }
        if(list.size() >= 1 && list.get(0).get_location() != null) {
            LatLng location = new LatLng(list.get(0).get_location().getLatitude(), list.get(0).get_location().getLongitude());
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        } else {
            LatLng tel_aviv = new LatLng(32.109333, 34.855499);
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(tel_aviv));
        }
    }
}
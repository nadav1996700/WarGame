package com.example.wargame_v2.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wargame_v2.Utils.My_SP;
import com.example.wargame_v2.R;
import com.example.wargame_v2.Utils.VictoryData;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Fragment_Map extends Fragment implements OnMapReadyCallback {

    private GoogleMap mGoogleMap;
    private MapView mMapView;
    private View mView;

    public static Fragment_Map newInstance() {
        Fragment_Map fragment = new Fragment_Map();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_map, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMapView = mView.findViewById(R.id.map);
        if(mMapView != null) {
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        setDataOnMap(googleMap);
    }

    private void setDataOnMap(GoogleMap googleMap) {
        // clear map from previous markers
        googleMap.clear();
        // load data of victories to list
        ArrayList<VictoryData> list = My_SP.getInstance().loadData();
        // add marker for every winner
        for(VictoryData winner: list) {
            if(winner.get_location() != null) {
                LatLng location = new LatLng(winner.get_location().getLatitude(), winner.get_location().getLongitude());
                googleMap.addMarker(new MarkerOptions().position(location).title(winner.get_name()));
            }
        }
        // move camera to the highest score location
        if(list.size() >= 1 && list.get(0).get_location() != null) {
            LatLng location = new LatLng(list.get(0).get_location().getLatitude(), list.get(0).get_location().getLongitude());
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        }
    }
}
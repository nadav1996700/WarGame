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
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Fragment_Map extends Fragment {

    public static Fragment_Map newInstance() {
        Fragment_Map fragment = new Fragment_Map();
        return fragment;
    }

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        @Override
        public void onMapReady(GoogleMap googleMap) {
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
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}
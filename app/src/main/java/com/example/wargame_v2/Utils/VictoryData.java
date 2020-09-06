package com.example.wargame_v2.Utils;

import androidx.annotation.NonNull;
import com.google.android.gms.maps.model.LatLng;

public class VictoryData {

    private String name;
    private int attacks;
    private LatLng winnerLocation;

    public VictoryData(String name, int attacks, LatLng location) {
        set_name(name);
        set_attacks(attacks);
        set_location(location);
    }

    public int get_attacks() {
        return attacks;
    }

    public String get_name() {
        return name;
    }

    public void set_attacks(int new_attacks) {
        this.attacks = new_attacks;
    }

    public void set_name(String new_name) {
        this.name = new_name;
    }

    public LatLng get_location() {
        return winnerLocation;
    }

    public void set_location(LatLng location) {
        this.winnerLocation = location;
    }

    @Override
    @NonNull
    public String toString() {
        return name + ": " + attacks;
    }
}

package com.example.wargame_v2;

import android.location.Location;

public class VictoryData {
    private String name;
    private int victories;
    private Location winnerLocation;

    public VictoryData(String name, int victories, Location location) {
        set_name(name);
        set_victories(victories);
        set_location(location);
    }

    public int get_victories() {
        return victories;
    }

    public String get_name() {
        return name;
    }

    public void set_victories(int new_victories) {
        this.victories = new_victories;
    }

    public void set_name(String new_name) {
        this.name = new_name;
    }

    public Location get_location() {
        return winnerLocation;
    }

    public void set_location(Location location) {
        this.winnerLocation = location;
    }
}

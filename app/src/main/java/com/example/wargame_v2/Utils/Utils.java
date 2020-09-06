package com.example.wargame_v2.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.widget.ImageView;
// glide
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.Objects;

public class Utils {

    private static Utils instance;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private final int REQUEST_CODE = 101;
    private Location last_location;
    private Context context;
    private LocationManager locationManager;

    private Utils(Context context) {
        this.context = context;
    }

    public static Utils getInstance() {return instance;}

    public static Utils initHelper(Context context) {
        if(instance == null)
            instance = new Utils(context);
        return instance;
    }



    /* set images using glide library*/
    public void setImage(ImageView imageView, Drawable photo) {
        Glide.with(context)
                .load(photo)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
    }

    /*
    public Location getCurrentLocation() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        fetchLastLocation();
        return last_location;
    }

    private void fetchLastLocation() {
        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]
                    { Manifest.permission.ACCESS_FINE_LOCATION }, REQUEST_CODE);
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                last_location =  location;
            }
        });
    }
     */

    public Location getLocation(Activity activity) {
        locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        if(ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Objects.requireNonNull(activity),new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION }, REQUEST_CODE);
        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if(location != null)
                return location;
            else {
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if(location != null)
                    return location;
            }
        }
        return null;
    }
}

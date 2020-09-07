package com.example.wargame_v2.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;
import androidx.core.app.ActivityCompat;
// glide
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
// location
import android.location.Location;
import android.location.LocationManager;
import com.google.android.gms.maps.model.LatLng;

import java.util.Objects;

public class Utils {

    private static Utils instance;
    private static final int REQUEST_CODE = 101;
    private Context context;

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

    // get current location
    public LatLng getLocation(Activity activity) {
        LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        // check permissions
        if(ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Objects.requireNonNull(activity),new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION }, REQUEST_CODE);
        } else {
            // get last known position using network
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if(location != null) {
                Log.d("pttt", "Location Found By Network");
                return new LatLng(location.getLatitude(), location.getLongitude());
            }
            else {
                // get last known position using GPS
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if(location != null) {
                    Log.d("pttt", "Location Found By GPS");
                    return new LatLng(location.getLatitude(), location.getLongitude());
                }
            }
        }
        // can't find location
        Log.d("pttt","Null Location");
        return null;
    }
}

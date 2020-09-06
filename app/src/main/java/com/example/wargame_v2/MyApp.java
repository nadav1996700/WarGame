package com.example.wargame_v2;

import android.app.Application;

import com.example.wargame_v2.Utils.Utils;
import com.example.wargame_v2.Utils.My_SP;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        My_SP sp = My_SP.initHelper(this);
        Utils utils = Utils.initHelper(this);
    }
}

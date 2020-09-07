package src;

import android.app.Application;

import src.Utils.Utils;
import src.Utils.My_SP;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        My_SP sp = My_SP.initHelper(this);
        Utils utils = Utils.initHelper(this);
    }
}

package com.example.wargame_v2;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class My_SP {

    public  static final String KEY = "DATA";
    private static My_SP instance;
    private SharedPreferences prefs;

    public static My_SP getInstance() {return instance;}

    private My_SP(Context context) {
        prefs = context.getApplicationContext().getSharedPreferences("App_SP_DB", Context.MODE_PRIVATE);
    }

    public static My_SP initHelper(Context context) {
        if(instance == null)
            instance = new My_SP(context);
        return instance;
    }

    private void saveData(ArrayList<VictoryData> rank_list) {
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(rank_list);
        editor.putString(KEY, json);
        editor.apply();
    }

    private ArrayList<VictoryData> loadData() {
        Gson gson = new Gson();
        String json = prefs.getString(KEY, null);
        Type type = new TypeToken<ArrayList<VictoryData>>() {}.getType();
        ArrayList<VictoryData> rank_list = gson.fromJson(json, type);

        if(rank_list == null)
            return new ArrayList<>();
        else
            return rank_list;
    }
}

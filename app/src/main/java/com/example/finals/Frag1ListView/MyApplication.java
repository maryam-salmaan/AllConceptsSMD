package com.example.finals.Frag1ListView;

import android.app.Application;

import java.util.ArrayList;

public class MyApplication extends Application {

    static ArrayList<ListData> listdata;


    @Override
    public void onCreate() {
        super.onCreate();
        listdata = new ArrayList<>();

        listdata.add(new ListData("Maryam", 22));
        listdata.add(new ListData("Aneela", 21));
        listdata.add(new ListData("Fatima", 23));

    }
}

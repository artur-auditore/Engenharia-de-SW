package com.example.brendon.registrodealunosv1.dal;

import android.app.Application;

import com.example.brendon.registrodealunosv1.Models.MyObjectBox;

import io.objectbox.BoxStore;

public class App extends Application {

    public static final String TAG = "ObjectBoxExample";
    public static final boolean EXTERNAL_DIR = false;

    public BoxStore boxStore;

    @Override
    public void onCreate() {
        super.onCreate();
        boxStore = MyObjectBox.builder().androidContext(App.this).build();

    }

    public BoxStore getBoxStore() {
        return boxStore;
    }
}
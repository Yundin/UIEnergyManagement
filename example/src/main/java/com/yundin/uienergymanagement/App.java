package com.yundin.uienergymanagement;

import android.app.Application;

import com.yundin.hierarchy_checker.UIManager;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        UIManager.init(this);
    }
}

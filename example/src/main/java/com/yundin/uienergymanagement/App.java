package com.yundin.uienergymanagement;

import android.app.Application;

import com.yundin.hierarchy_checker.HierarchyChecker;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        HierarchyChecker.init(this);
    }
}

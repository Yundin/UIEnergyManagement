package com.yundin.hierarchy_checker;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

public class HierarchyChecker {

   private static String LOG_TAG = "HierarchyChecker";

   public static void init(Application application) {
       application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
          @Override
          public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                android.app.FragmentManager def = activity.getFragmentManager();
                def.registerFragmentLifecycleCallbacks(new android.app.FragmentManager.FragmentLifecycleCallbacks() {
                }, true);
                Log.d(LOG_TAG, "Simple FragmentManager's FragmentLifecycleCallbacks registered");
             } else {
                Log.w(LOG_TAG, "Simple FragmentManager not supporting FragmentLifecycleCallbacks until Android O");
             }
             if (activity instanceof FragmentActivity) {
                androidx.fragment.app.FragmentManager support = ((FragmentActivity) activity).getSupportFragmentManager();
                support.registerFragmentLifecycleCallbacks(new androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks() {
                }, true);
                Log.d(LOG_TAG, "SupportFragmentManager's FragmentLifecycleCallbacks registered");
             } else {
                Log.w(LOG_TAG, "Only FragmentActivities support SupportFragmentManager");
             }
          }

          @Override
          public void onActivityStarted(@NonNull Activity activity) {}

          @Override
          public void onActivityResumed(@NonNull Activity activity) {
             View root = activity.getWindow().getDecorView().getRootView();
          }

          @Override
          public void onActivityPaused(@NonNull Activity activity) {}

          @Override
          public void onActivityStopped(@NonNull Activity activity) {}

          @Override
          public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {}

          @Override
          public void onActivityDestroyed(@NonNull Activity activity) {}
       });
   }
}

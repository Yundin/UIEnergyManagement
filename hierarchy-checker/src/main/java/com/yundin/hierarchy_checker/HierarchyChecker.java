package com.yundin.hierarchy_checker;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

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
                 //noinspection deprecation
                 def.registerFragmentLifecycleCallbacks(new android.app.FragmentManager.FragmentLifecycleCallbacks() {
                    @Override
                    public void onFragmentViewCreated(android.app.FragmentManager fm, android.app.Fragment f, View v, Bundle savedInstanceState) {
                       analyzeHierarchy(v);
                    }
                }, true);
                Log.d(LOG_TAG, "Simple FragmentManager's FragmentLifecycleCallbacks registered");
             } else {
                Log.w(LOG_TAG, "Simple FragmentManager not supporting FragmentLifecycleCallbacks until Android O");
             }


             if (activity instanceof FragmentActivity) {
                androidx.fragment.app.FragmentManager support = ((FragmentActivity) activity).getSupportFragmentManager();
                support.registerFragmentLifecycleCallbacks(new androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks() {
                   @Override
                   public void onFragmentViewCreated(@NonNull androidx.fragment.app.FragmentManager fm, @NonNull androidx.fragment.app.Fragment f, @NonNull View v, @Nullable Bundle savedInstanceState) {
                      analyzeHierarchy(v);
                   }
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
//             View root = ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
             View root = activity.getWindow().getDecorView().getRootView();
             analyzeHierarchy(root);
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

   private static void analyzeHierarchy(View root) {
      proceedView(root);
      if (root instanceof ViewGroup) {
         ViewGroup viewGroup = (ViewGroup) root;
         for (int i = 0; i < viewGroup.getChildCount(); i++) {
            analyzeHierarchy(viewGroup.getChildAt(i));
         }
      }
   }

   private static void proceedView(View view) {
       // Temp implementation
      StringBuilder sb = new StringBuilder(view.getClass().getName());
      ViewParent parent = view.getParent();
      while (parent != null) {
         sb.append('/');
         sb.append(parent.getClass().toString());
         parent = parent.getParent();
      }
      Log.d(LOG_TAG, sb.toString());
   }
}

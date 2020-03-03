package com.yundin.hierarchy_checker;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;


public class HierarchyChecker {

   private static String LOG_TAG = "HierarchyChecker";
   private static final ViewGroup.OnHierarchyChangeListener HIERARCHY_CHANGE_LISTENER = new ViewGroup.OnHierarchyChangeListener() {
      @Override
      public void onChildViewAdded(View parent, View child) {
         analyzeHierarchy(child);
      }

      @Override
      public void onChildViewRemoved(View parent, View child) {}
   };

   public static void init(@NotNull Application application) {
       application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {

          @Override
          public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {}

          @Override
          public void onActivityStarted(@NonNull Activity activity) {}

          @Override
          public void onActivityResumed(@NonNull Activity activity) {
             View root = getActivityRoot(activity);
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

   private static View getActivityRoot(Activity activity) {
      return activity.getWindow().getDecorView().getRootView();
   }

   private static View getActivityContentRoot(Activity activity) {
      return ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
   }

   private static void analyzeHierarchy(@NotNull View root) {
      proceedView(root);
      if (root instanceof ViewGroup) {
         ViewGroup viewGroup = (ViewGroup) root;
         viewGroup.setOnHierarchyChangeListener(HIERARCHY_CHANGE_LISTENER);

         for (int i = 0; i < viewGroup.getChildCount(); i++) {
            analyzeHierarchy(viewGroup.getChildAt(i));
         }
      }
   }

   private static void proceedView(@NotNull View view) {
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

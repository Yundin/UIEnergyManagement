package com.yundin.hierarchy_checker.analyzer;

import android.view.View;
import android.view.ViewGroup;

import com.yundin.hierarchy_checker.adviser.Adviser;
import com.yundin.hierarchy_checker.outputter.RecommendationOutputter;

import org.jetbrains.annotations.NotNull;


public class ConcreteHierarchyAnalyzer implements HierarchyAnalyzer {

   private RecommendationOutputter outputter;
   private ViewGroup.OnHierarchyChangeListener hierarchyChangeListener;
   private Adviser adviser;

   public ConcreteHierarchyAnalyzer(RecommendationOutputter outputter, Adviser adviser) {
      this.outputter = outputter;
      this.hierarchyChangeListener = new HierarchyChangeListener();
      this.adviser = adviser;
   }

   @Override
   public void analyzeDynamicHierarchy(@NotNull View root) {
      proceedView(root);
      if (root instanceof ViewGroup) {
         ViewGroup viewGroup = (ViewGroup) root;
         viewGroup.setOnHierarchyChangeListener(hierarchyChangeListener);

         for (int i = 0; i < viewGroup.getChildCount(); i++) {
            analyzeDynamicHierarchy(viewGroup.getChildAt(i));
         }
      }
   }

   private void proceedView(@NotNull View view) {
      String alternative = adviser.findAlternative(view.getClass().getSimpleName());
      if (alternative != null) {
         outputter.output(view, alternative);
      }
   }

   class HierarchyChangeListener implements ViewGroup.OnHierarchyChangeListener {
      @Override
      public void onChildViewAdded(View parent, View child) {
         analyzeDynamicHierarchy(child);
      }

      @Override
      public void onChildViewRemoved(View parent, View child) {}
   }
}

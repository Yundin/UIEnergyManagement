package com.yundin.hierarchy_checker;

import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;


public class ConcreteHierarchyAnalyzer implements HierarchyAnalyzer {

   private RecommendationOutputter outputter;
   private ViewGroup.OnHierarchyChangeListener hierarchyChangeListener;

   public ConcreteHierarchyAnalyzer(RecommendationOutputter outputter) {
      this.outputter = outputter;
      this.hierarchyChangeListener = new HierarchyChangeListener();
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
      outputter.output(view, "TextView");
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

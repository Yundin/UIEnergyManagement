package com.yundin.hierarchy_checker.analyzer

import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.OnHierarchyChangeListener
import com.yundin.hierarchy_checker.adviser.Adviser
import com.yundin.hierarchy_checker.outputter.RecommendationOutputter

class ConcreteHierarchyAnalyzer(
    adviser: Adviser,
    outputter: RecommendationOutputter
) : HierarchyAnalyzer(adviser, outputter) {

    private val hierarchyChangeListener: OnHierarchyChangeListener = HierarchyChangeListener()

    override fun analyzeDynamicHierarchy(root: View) {
        proceedView(root)
        if (root is ViewGroup) {
            root.setOnHierarchyChangeListener(hierarchyChangeListener)
            for (i in 0 until root.childCount) {
                analyzeDynamicHierarchy(root.getChildAt(i))
            }
        }
    }

    private fun proceedView(view: View) {
        adviser.findAlternativeAsync(view.javaClass.simpleName) { alternative ->
            outputter.output(view, alternative)
        }
    }

    internal inner class HierarchyChangeListener : OnHierarchyChangeListener {

        override fun onChildViewAdded(parent: View, child: View) {
            analyzeDynamicHierarchy(child)
        }

        override fun onChildViewRemoved(parent: View, child: View) {}
    }
}
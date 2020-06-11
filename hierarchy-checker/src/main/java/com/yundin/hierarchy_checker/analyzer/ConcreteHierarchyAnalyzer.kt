package com.yundin.hierarchy_checker.analyzer

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.OnHierarchyChangeListener
import androidx.core.view.ViewCompat
import com.yundin.hierarchy_checker.adviser.Adviser
import com.yundin.hierarchy_checker.outputter.RecommendationOutputter
import java.util.*

class ConcreteHierarchyAnalyzer(
    adviser: Adviser,
    outputter: RecommendationOutputter
) : HierarchyAnalyzer(adviser, outputter) {

    private val hierarchyChangeListener: OnHierarchyChangeListener = HierarchyChangeListener()
    private val known = hashSetOf<Int>()

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
        if (view.id == -1) {
            view.id = ViewCompat.generateViewId()
        }
        if (!known.contains(view.id)) {
            known.add(view.id)
            adviser.findAlternativeAsync(view.javaClass.simpleName) { alternative ->
                outputter.output(view, alternative)
            }
        }
    }

    internal inner class HierarchyChangeListener : OnHierarchyChangeListener {

        override fun onChildViewAdded(parent: View, child: View) {
            analyzeDynamicHierarchy(child)
        }

        override fun onChildViewRemoved(parent: View, child: View) {}
    }
}
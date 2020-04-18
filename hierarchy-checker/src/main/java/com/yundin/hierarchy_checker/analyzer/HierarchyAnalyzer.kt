package com.yundin.hierarchy_checker.analyzer

import android.view.View
import com.yundin.hierarchy_checker.adviser.Adviser
import com.yundin.hierarchy_checker.outputter.RecommendationOutputter

abstract class HierarchyAnalyzer(
    protected val adviser: Adviser,
    protected val outputter: RecommendationOutputter
) {

    abstract fun analyzeDynamicHierarchy(root: View)
}

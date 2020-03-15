package com.yundin.hierarchy_checker.analyzer

import android.view.View

interface HierarchyAnalyzer {

    fun analyzeDynamicHierarchy(root: View)
}
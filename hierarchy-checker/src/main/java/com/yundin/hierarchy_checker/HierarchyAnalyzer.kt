package com.yundin.hierarchy_checker

import android.view.View

interface HierarchyAnalyzer {

    fun analyzeDynamicHierarchy(root: View)
}
package com.yundin.hierarchy_checker

import android.view.View

interface RecommendationOutputter {

    fun output(original: View, optimal: String)
}
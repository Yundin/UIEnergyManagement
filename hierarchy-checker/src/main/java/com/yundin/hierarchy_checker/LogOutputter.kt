package com.yundin.hierarchy_checker

import android.util.Log
import android.view.View
import android.view.ViewParent
import java.util.*

class LogOutputter : RecommendationOutputter {

    companion object {
        private const val LOG_TAG = "HierarchyChecker"
    }

    override fun output(original: View, optimal: String) {

        val sb = StringBuilder(original.javaClass.name)
        sb.append(" located in ")

        val stack = Stack<String>()

        var parent: ViewParent? = original.parent
        while (parent != null) {
            stack.push(parent.javaClass.toString())
            parent = parent.parent
        }

        while (!stack.empty()) {
            sb.append('/')
            sb.append(stack.pop())
        }

        sb.append(" could be changed to $optimal")

        Log.i(LOG_TAG, sb.toString())
    }
}
package com.yundin.hierarchy_checker.outputter

import android.content.res.Resources
import android.util.Log
import android.view.View
import android.view.ViewParent
import java.util.*

class LogOutputter : RecommendationOutputter {

    companion object {
        private const val LOG_TAG = "HierarchyChecker"
    }

    override fun output(original: View, advice: String) {

        val sb = StringBuilder("Advice for ")
        sb.append(original.javaClass.simpleName)
        try {
            val id = original.resources.getResourceEntryName(original.id)
            sb.append(" with id $id")
        } catch (e: Resources.NotFoundException) {
            // id was generated or not present
            sb.append(" located in ")

            val stack = Stack<String>()

            var parent: ViewParent? = original.parent
            while (parent != null) {
                stack.push(parent.javaClass.canonicalName)
                parent = parent.parent
            }

            while (!stack.empty()) {
                sb.append('/')
                sb.append(stack.pop())
            }
        }

        sb.append(" : $advice")

        Log.i(LOG_TAG, sb.toString())
    }
}
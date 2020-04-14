package com.yundin.estimation

import android.R
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup

fun replaceView(activity: Activity, viewWrapper: ViewWrapper) {
    activity.runOnUiThread {
        val constructor = viewWrapper.viewClass.getConstructor(Context::class.java)
        val view = constructor.newInstance(activity) as View
        val activityContainer = getActivityContainer(activity)
        activityContainer.removeAllViews()
        viewWrapper.beforeAdd(view)
        activityContainer.addView(view)
        viewWrapper.afterAdd(view)
    }
}

private fun getActivityContainer(activity: Activity): ViewGroup {
    return activity.findViewById<View>(R.id.content) as ViewGroup
}
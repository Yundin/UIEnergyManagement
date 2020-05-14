package com.yundin.estimation

import android.app.Activity
import android.content.Context
import android.view.View

fun replaceView(activity: Activity, viewWrapper: ViewWrapper) {
        val constructor = viewWrapper.viewClass.getConstructor(Context::class.java)
        val view = constructor.newInstance(activity) as View
        viewWrapper.beforeAdd(view)
        activity.setContentView(view)
        viewWrapper.afterAdd(view)
}

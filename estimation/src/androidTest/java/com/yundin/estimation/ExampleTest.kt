package com.yundin.estimation

import android.R
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextClock
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleTest : TestCase() {

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun exampleTest() {
        run {
            step("Open Main") {
                activityTestRule.launchActivity(null)
                val activity = activityTestRule.activity
                val views = listOf<Class<*>>(Button::class.java, ProgressBar::class.java, TextClock::class.java)
                for (view in views) {
                    replaceView(activity, view)
                    Thread.sleep(5000)
                }
            }
        }
    }

    fun replaceView(activity: Activity, viewClass: Class<*>) {
        activity.runOnUiThread {
            val constructor = viewClass.getConstructor(Context::class.java)
            val view = constructor.newInstance(activity) as View
            val activityRoot = getActivityContentRoot(activity)
            val rootParent = activityRoot?.parent as ViewGroup
            rootParent.removeAllViews()
            rootParent.addView(view)
        }
    }

    private fun getActivityContentRoot(activity: Activity): View? {
        return (activity.findViewById<View>(R.id.content) as ViewGroup).getChildAt(0)
    }
}

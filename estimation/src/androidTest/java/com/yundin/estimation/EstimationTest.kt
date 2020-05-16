package com.yundin.estimation

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EstimationTest : TestCase() {

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun estimationTest() {
        run {
            step("Open Main") {
                activityTestRule.launchActivity(null)
            }
            step("Start views testing") {
                val activity = activityTestRule.activity
                val views = getWrappers(activity)
                for (view in views) {
                    replaceView(activity, view)
                    activity.runOnUiThread {
                        activity.setTitle(view.viewClass.simpleName)
                    }
                    Thread.sleep(5000)
                }
            }
        }
    }

    @Test
    fun singleViewTest() {
        run {
            step("Open Main") {
                activityTestRule.launchActivity(null)
            }
            step("Start view testing") {
                val activity = activityTestRule.activity
                val view = VideoViewWrapper(activity)
                replaceView(activity, view)
                activity.runOnUiThread {
                    activity.setTitle(view.viewClass.simpleName)
                }
                Thread.sleep(10000)
            }
        }
    }

    @Test
    fun testViewByIndex() {
        run {
            val instrumentation: MyTestRunner = InstrumentationRegistry.getInstrumentation() as MyTestRunner
            val index = instrumentation.testIndex
            println("Index: $index")
            val activity = activityTestRule.launchActivity(null)
            val wrapper = getWrappers(activity)[index]

            activity.runOnUiThread {
                replaceView(activity, wrapper)
                activity.title = wrapper.viewClass.simpleName
            }
            Thread.sleep(instrumentation.testingTime.toLong())
        }
    }
}

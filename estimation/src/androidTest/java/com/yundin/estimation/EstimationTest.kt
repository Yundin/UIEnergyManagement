package com.yundin.estimation

import android.content.Intent
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
    fun testViewByIndex() {
        run {
            val instrumentation: MyTestRunner = InstrumentationRegistry.getInstrumentation() as MyTestRunner
            val index = instrumentation.testIndex
            val delay = instrumentation.testingTime

            val intent = Intent()
            intent.putExtra("index", index)
            intent.putExtra("delay", delay)

            activityTestRule.launchActivity(intent)

            Thread.sleep(delay.toLong())
        }
    }
}
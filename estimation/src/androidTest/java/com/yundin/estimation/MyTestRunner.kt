package com.yundin.estimation

import android.os.Bundle
import androidx.test.runner.AndroidJUnitRunner

class MyTestRunner : AndroidJUnitRunner() {

    var testIndex: Int = 0
    var testingTime: Int = 1000

    override fun onCreate(arguments: Bundle?) {
        super.onCreate(arguments)

        if (arguments != null) {
            testIndex = arguments.getString("index")?.toInt() ?: 0
            testingTime = arguments.getString("time")?.toInt() ?: 0
        }
    }
}
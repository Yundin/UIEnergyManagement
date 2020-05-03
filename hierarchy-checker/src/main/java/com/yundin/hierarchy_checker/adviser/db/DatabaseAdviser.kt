package com.yundin.hierarchy_checker.adviser.db

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.yundin.hierarchy_checker.adviser.Adviser
import java.util.concurrent.ExecutorService
import java.util.concurrent.SynchronousQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class DatabaseAdviser(context: Context) : Adviser {

    private val helper: AdviceHelper = AdviceHelper(context)
    private val executorService: ExecutorService = ThreadPoolExecutor(
        0,
        Integer.MAX_VALUE,
        30,
        TimeUnit.SECONDS,
        SynchronousQueue()
    )
    private val mainHandler = Handler(Looper.getMainLooper())

    override fun findAlternativeAsync(viewName: String, callback: (String) -> Unit) {
        executorService.execute {
            val advices = helper.getAdvices(viewName)
            mainHandler.post {
                advices.forEach(callback)
            }
        }
    }
}
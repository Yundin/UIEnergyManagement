package com.yundin.hierarchy_checker.adviser.db

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.yundin.hierarchy_checker.adviser.Adviser
import java.lang.StringBuilder
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
            var name = if (viewName.startsWith("AppCompat")) viewName.drop(9) else viewName
            var appendix = ""
            val sb = StringBuilder()
            var count = 0
            do {
                val advice = helper.getAdvice(name)
                if (advice != null) {
                    if (count != 0) {
                        sb.append(", ")
                    }
                    sb.append("$advice$appendix")
                    val split = advice.split(' ')
                    name = split[0]
                    if (split.size > 1) {
                        appendix = split.drop(1).joinToString(" ", prefix = " ")
                    }
                    count++
                }
            } while (advice != null)
            if (count >= 1) {
                if (count > 1) {
                    sb.insert(0, "$viewName could be replaced by (less effective to more effective): ")
                } else {
                    sb.insert(0, "$viewName could be replaced by ")
                }
                mainHandler.post {
                    callback(sb.toString())
                }
            }
        }
    }
}
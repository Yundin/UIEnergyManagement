package com.yundin.hierarchy_checker.adviser.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Handler
import android.os.Looper
import com.yundin.hierarchy_checker.adviser.Adviser
import java.lang.ref.WeakReference
import java.util.concurrent.ExecutorService
import java.util.concurrent.SynchronousQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class DatabaseAdviser(context: Context) : Adviser {

    private val helper: AdviceHelper = AdviceHelper(context)
    private var database: WeakReference<SQLiteDatabase?> = WeakReference(null)
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
            var databaseLocal = database.get()
            if (databaseLocal == null) {
                synchronized(this) {
                    databaseLocal = database.get()
                    if (databaseLocal == null) {
                        databaseLocal = helper.readableDatabase
                        database = WeakReference(databaseLocal)
                    }
                }
            }
            val advices = helper.getAdvices(databaseLocal!!, viewName)
            mainHandler.post {
                advices.forEach(callback)
            }
        }
    }
}
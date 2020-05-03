package com.yundin.hierarchy_checker.adviser.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.FileOutputStream

class AdviceHelper(private val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Advices.db"
    }

    init {
        if(!checkIfExists()) {
            copyFromAssets()
        }
    }

    fun getAdvices(database: SQLiteDatabase, viewName: String): List<String> {
        val projection = arrayOf(AdviceContract.AdviceEntry.COLUMN_NAME_ADVICE)

        val selection = "${AdviceContract.AdviceEntry.COLUMN_NAME_VIEW} = ?"
        val selectionArgs = arrayOf(viewName)

        val cursor = database.query(
            AdviceContract.AdviceEntry.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        val res = mutableListOf<String>()
        with(cursor) {
            while (moveToNext()) {
                val adviceIndex = getColumnIndex(AdviceContract.AdviceEntry.COLUMN_NAME_ADVICE)
                val advice = getString(adviceIndex)
                res.add(advice)
            }
        }

        return res
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // not creating db
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    private fun checkIfExists() : Boolean {
        return context.getDatabasePath(DATABASE_NAME).exists()
    }

    private fun copyFromAssets() {
        val inputStream = context.assets.open(DATABASE_NAME)
        val outFile = context.getDatabasePath(DATABASE_NAME)
        val outStream = FileOutputStream(outFile)

        inputStream.copyTo(outStream)

        inputStream.close()
        outStream.close()
    }
}
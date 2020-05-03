package com.yundin.hierarchy_checker.adviser.db

import android.provider.BaseColumns

object AdviceContract {

    object AdviceEntry : BaseColumns {
        const val TABLE_NAME = "advices"
        const val COLUMN_NAME_VIEW = "view"
        const val COLUMN_NAME_ADVICE = "advice"
    }
}
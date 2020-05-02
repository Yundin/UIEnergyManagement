package com.yundin.hierarchy_checker.adviser

interface Adviser {

    fun findAlternativeAsync(viewName: String, callback: (String) -> Unit)
}
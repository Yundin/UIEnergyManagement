package com.yundin.hierarchy_checker.adviser

interface Adviser {

    fun findAlternative(viewName: String): String?
}
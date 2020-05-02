package com.yundin.hierarchy_checker.adviser

class RandomAdviser : Adviser {

    private val views = listOf("TextView", "EditText", "Button", "Spinner", "Switch", "ProgressBar")

    override fun findAlternativeAsync(viewName: String, callback: (String) -> Unit) {
        if (!viewName.endsWith("Layout")) {
            callback(views.random())
        }
    }
}
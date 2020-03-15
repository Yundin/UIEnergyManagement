package com.yundin.hierarchy_checker.adviser

class RandomAdviser : Adviser {

    private val views = listOf("TextView", "EditText", "Button", "Spinner", "Switch", "ProgressBar")

    override fun findAlternative(viewName: String): String? {
        return if (viewName.endsWith("Layout")) {
            null
        } else {
            views.random()
        }
    }
}
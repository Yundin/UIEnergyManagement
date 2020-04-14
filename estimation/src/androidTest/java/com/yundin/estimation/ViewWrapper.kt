package com.yundin.estimation

import android.view.View
import android.widget.TextView

open class ViewWrapper(val viewClass: Class<*>) {

    open fun beforeAdd(view: View) {}
    open fun afterAdd(view: View) {}
}

class TextViewWrapper(className: Class<*>): ViewWrapper(className) {

    override fun beforeAdd(view: View) {
        (view as TextView).text = "Sample text"
    }
}
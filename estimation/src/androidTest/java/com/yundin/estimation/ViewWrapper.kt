package com.yundin.estimation

import android.view.View
import android.widget.CheckedTextView
import android.widget.ImageView
import android.widget.TextView

open class ViewWrapper(val viewClass: Class<*>) {

    open fun beforeAdd(view: View) {}
    open fun afterAdd(view: View) {}
}

open class TextViewWrapper(className: Class<*>): ViewWrapper(className) {

    override fun beforeAdd(view: View) {
        (view as TextView).text = "Sample text"
    }
}

class ImageViewWrapper(className: Class<*>): ViewWrapper(className) {

    override fun beforeAdd(view: View) {
        (view as ImageView).setImageResource(android.R.drawable.ic_input_add)
    }
}

class CheckedTextViewWrapper(private val setCheckMark: Boolean = false): TextViewWrapper(CheckedTextView::class.java) {

    override fun beforeAdd(view: View) {
        super.beforeAdd(view)
        if (setCheckMark) {
            view as CheckedTextView
            view.setCheckMarkDrawable(android.R.drawable.checkbox_on_background)
            view.setOnClickListener {
                view.toggle()
                if (view.isChecked) {
                    view.setCheckMarkDrawable(android.R.drawable.checkbox_off_background)
                } else {
                    view.setCheckMarkDrawable(android.R.drawable.checkbox_on_background)
                }
            }
        }
    }
}
package com.yundin.estimation

import android.content.Context
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.*

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

class NumberPickerWrapper: ViewWrapper(NumberPicker::class.java) {

    override fun beforeAdd(view: View) {
        (view as NumberPicker).maxValue = 10
    }
}

abstract class ViewSwitcherWrapper(className: Class<*>): ViewWrapper(className), ViewSwitcher.ViewFactory {

    override fun beforeAdd(view: View) {
        view as ViewSwitcher
        view.setFactory(this)
        view.setContent()
    }

    abstract fun View.setContent()
}

class ImageSwitcherWrapper(private val context: Context): ViewSwitcherWrapper(ImageSwitcher::class.java) {

    override fun View.setContent() {
        (this as ImageSwitcher).setImageResource(android.R.drawable.ic_input_add)
    }

    override fun makeView(): View {
        return ImageView(context)
    }
}

class TextSwitcherWrapper(private val context: Context): ViewSwitcherWrapper(TextSwitcher::class.java) {

    override fun View.setContent() {
        (this as TextSwitcher).setText("Sample text")
    }

    override fun makeView(): View {
        return TextView(context)
    }
}

class RatingBarWrapper: ViewWrapper(RatingBar::class.java) {

    override fun beforeAdd(view: View) {
        val lp = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        view.layoutParams = lp
    }
}

class VideoViewWrapper(private val context: Context): ViewWrapper(VideoView::class.java) {

    override fun beforeAdd(view: View) {
        val uri = Uri.parse("android.resource://" + context.packageName + "/" + R.raw.sample)
        (view as VideoView).setVideoURI(uri)
        view.start()
    }
}
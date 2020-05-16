package com.yundin.estimation

import android.R
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.*

fun replaceView(activity: Activity, viewWrapper: ViewWrapper) {
    val constructor = viewWrapper.viewClass.getConstructor(Context::class.java)
    val view = constructor.newInstance(activity) as View
    val activityContainer = getActivityContainer(activity)
    activityContainer.removeAllViews()
    viewWrapper.beforeAdd(view)
    activityContainer.addView(view)
    viewWrapper.afterAdd(view)
}

private fun getActivityContainer(activity: Activity): ViewGroup {
    return activity.findViewById<View>(R.id.content) as ViewGroup
}

fun getWrappers(activity: Activity): List<ViewWrapper> = listOf(
    TextViewWrapper(AutoCompleteTextView::class.java),
    TextViewWrapper(Button::class.java),
    ViewWrapper(CalendarView::class.java),
    TextViewWrapper(CheckBox::class.java),
    CheckedTextViewWrapper(),
    ViewWrapper(Chronometer::class.java),
    ViewWrapper(DatePicker::class.java),
    TextViewWrapper(EditText::class.java),
    ImageViewWrapper(ImageButton::class.java),
    ImageSwitcherWrapper(activity),
    ImageViewWrapper(ImageView::class.java),
    TextViewWrapper(MultiAutoCompleteTextView::class.java),
    NumberPickerWrapper(),
    ViewWrapper(ProgressBar::class.java),
    TextViewWrapper(RadioButton::class.java),
    RatingBarWrapper(),
    ViewWrapper(SearchView::class.java),
    ViewWrapper(SeekBar::class.java),
    ViewWrapper(Space::class.java),
    TextViewWrapper(Switch::class.java),
    ViewWrapper(TextClock::class.java),
    TextSwitcherWrapper(activity),
    TextViewWrapper(TextView::class.java),
    ViewWrapper(TimePicker::class.java),
    TextViewWrapper(ToggleButton::class.java),
    VideoViewWrapper(activity),
    ViewWrapper(View::class.java)
)
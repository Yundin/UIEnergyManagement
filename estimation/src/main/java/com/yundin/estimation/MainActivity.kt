package com.yundin.estimation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val views = listOf(
        TextViewWrapper(AutoCompleteTextView::class.java),
        TextViewWrapper(Button::class.java),
        ViewWrapper(CalendarView::class.java),
        TextViewWrapper(CheckBox::class.java),
        CheckedTextViewWrapper(),
        ViewWrapper(Chronometer::class.java),
        ViewWrapper(DatePicker::class.java),
        TextViewWrapper(EditText::class.java),
        ImageViewWrapper(ImageButton::class.java),
        ImageSwitcherWrapper(this),
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
        TextSwitcherWrapper(this),
        TextViewWrapper(TextView::class.java),
        ViewWrapper(TimePicker::class.java),
        TextViewWrapper(ToggleButton::class.java),
        VideoViewWrapper(this),
        ViewWrapper(View::class.java)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val handler = Handler(Looper.getMainLooper())
        val index = intent.extras?.getInt("index") ?: return
        val delay = intent.extras?.getInt("delay") ?: 0

        val view = views[index]
        replaceView(this, view)
        title = view.viewClass.simpleName

        handler.postDelayed({ finish() }, delay.toLong())
    }
}

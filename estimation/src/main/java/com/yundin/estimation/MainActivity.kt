package com.yundin.estimation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val views = listOf(
        TextViewWrapper(AutoCompleteTextView::class.java), // 0
        TextViewWrapper(Button::class.java), // 1
        ViewWrapper(CalendarView::class.java), // 2
        TextViewWrapper(CheckBox::class.java), // 3
        CheckedTextViewWrapper(), // 4
        ViewWrapper(Chronometer::class.java), // 5
        ViewWrapper(DatePicker::class.java), // 6
        TextViewWrapper(EditText::class.java), // 7
        ImageViewWrapper(ImageButton::class.java), // 8
        ImageSwitcherWrapper(this), // 9
        ImageViewWrapper(ImageView::class.java), // 10
        TextViewWrapper(MultiAutoCompleteTextView::class.java), // 11
        NumberPickerWrapper(), // 12
        ViewWrapper(ProgressBar::class.java), // 13
        TextViewWrapper(RadioButton::class.java), // 14
        RatingBarWrapper(), // 15
        ViewWrapper(SearchView::class.java), // 16
        ViewWrapper(SeekBar::class.java), // 17
        ViewWrapper(Space::class.java), // 18
        TextViewWrapper(Switch::class.java), // 19
        ViewWrapper(TextClock::class.java), // 20
        TextSwitcherWrapper(this), // 21
        TextViewWrapper(TextView::class.java), // 22
        ViewWrapper(TimePicker::class.java), // 23
        TextViewWrapper(ToggleButton::class.java), // 24
        VideoViewWrapper(this), // 25
        ViewWrapper(View::class.java) // 26
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val handler = Handler(Looper.getMainLooper())
        val index = intent.extras?.getInt("index") ?: 0
        val delay = intent.extras?.getInt("delay") ?: 180000

        showView(index)

        handler.postDelayed({ finish() }, delay.toLong())
    }

    private fun showView(index: Int) {
        val view = views[index]
        replaceView(this, view)
        title = view.viewClass.simpleName
    }
}

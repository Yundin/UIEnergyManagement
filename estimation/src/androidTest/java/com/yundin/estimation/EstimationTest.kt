package com.yundin.estimation

import android.view.View
import android.widget.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EstimationTest : TestCase() {

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun exampleTest() {
        run {
            step("Open Main") {
                activityTestRule.launchActivity(null)
            }
            step("Start views testing") {
                val activity = activityTestRule.activity
                val views = listOf(
                    TextViewWrapper(AutoCompleteTextView::class.java),
                    TextViewWrapper(Button::class.java),
                    ViewWrapper(CalendarView::class.java),
                    TextViewWrapper(CheckBox::class.java),
                    TextViewWrapper(CheckedTextView::class.java), // needs attention
                    ViewWrapper(Chronometer::class.java),
                    ViewWrapper(DatePicker::class.java),
                    TextViewWrapper(EditText::class.java),
                    ImageViewWrapper(ImageButton::class.java),
                    //ViewWrapper(ImageSwitcher::class.java), // not having content for now
                    ImageViewWrapper(ImageView::class.java),
                    TextViewWrapper(MultiAutoCompleteTextView::class.java),
                    ViewWrapper(NumberPicker::class.java), // needs attention
                    ViewWrapper(ProgressBar::class.java),
                    //ViewWrapper(QuickContactBadge::class.java), // not having content for now
                    TextViewWrapper(RadioButton::class.java),
                    //ViewWrapper(RatingBar::class.java), // not having content for now
                    ViewWrapper(SearchView::class.java),
                    ViewWrapper(SeekBar::class.java), // needs attention
                    ViewWrapper(Space::class.java),
                    TextViewWrapper(Switch::class.java),
                    ViewWrapper(TextClock::class.java),
                    //ViewWrapper(TextSwitcher::class.java), // not having content for now
                    TextViewWrapper(TextView::class.java),
                    ViewWrapper(TimePicker::class.java),
                    TextViewWrapper(ToggleButton::class.java),
                    //ViewWrapper(VideoView::class.java), // not having content for now
                    ViewWrapper(View::class.java)
                )
                for (view in views) {
                    replaceView(activity, view)
                    activity.runOnUiThread {
                        activity.setTitle(view.viewClass.simpleName)
                    }
                    Thread.sleep(5000)
                }
            }
        }
    }
}

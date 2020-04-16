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
    fun estimationTest() {
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
                    //ViewWrapper(QuickContactBadge::class.java), // not having content for now
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

    @Test
    fun singleViewTest() {
        run {
            step("Open Main") {
                activityTestRule.launchActivity(null)
            }
            step("Start view testing") {
                val activity = activityTestRule.activity
                val view = VideoViewWrapper(activity)
                replaceView(activity, view)
                activity.runOnUiThread {
                    activity.setTitle(view.viewClass.simpleName)
                }
                Thread.sleep(10000)
            }
        }
    }
}

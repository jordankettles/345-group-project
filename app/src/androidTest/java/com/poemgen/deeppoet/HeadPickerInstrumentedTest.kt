package com.poemgen.deeppoet

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HeadPickerInstrumentedTest {

    @Test
    fun clickShowMainButton() {
        val scenario = launchActivity<HeadPickerActivity>()
        onView(withId(R.id.showMain_HeadPicker)).perform(click())
        android.os.SystemClock.sleep(1000)
        assert(scenario.state == Lifecycle.State.DESTROYED)
    }
}
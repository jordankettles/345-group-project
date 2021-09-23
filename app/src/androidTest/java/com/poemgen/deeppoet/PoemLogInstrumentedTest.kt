package com.poemgen.deeppoet

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Test for Poem Log screen. Runs on emulator.
 */
@RunWith(AndroidJUnit4::class)
class PoemLogInstrumentedTest {

    /**
     * Tests button to return to Main Activity from Pog Log Activity.
     */
    @Test
    fun clickShowMainButton() {
        val scenario = launchActivity<LogDisplayActivity>()
        onView(withId(R.id.showMain)).perform(click())
        android.os.SystemClock.sleep(1000)
        assert(scenario.state == Lifecycle.State.DESTROYED)
    }
}
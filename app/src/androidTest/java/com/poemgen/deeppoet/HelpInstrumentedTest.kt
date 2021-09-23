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
 * Test for help screen. Runs on emulator.
 */
@RunWith(AndroidJUnit4::class)
class HelpInstrumentedTest {

    /**
     * Tests button to return from help screen to main screen.
     */
    @Test
    fun testShowMainButton() {
        val scenario = launchActivity<HelpActivity>()
        android.os.SystemClock.sleep(1000)
        onView(withId(R.id.showMainHelp)).perform(click())
        android.os.SystemClock.sleep(2000)
        assert(scenario.state == Lifecycle.State.DESTROYED)
    }
}
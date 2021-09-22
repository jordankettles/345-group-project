package com.poemgen.deeppoet

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Test for Poem Log screen. Runs on emulator.
 */
@RunWith(AndroidJUnit4::class)
class PoemLogInstrumentedTest {

    @Before
    fun before() {
        Intents.init()
    }

    @After
    fun after() {
        Intents.release()
    }


    /**
     * Tests button to return to Main Activity from Pog Log Activity.
     */
    @Test
    fun clickShowMainButton() {
        launchActivity<LogDisplayActivity>()
        Espresso.onView(ViewMatchers.withId(R.id.showMain)).perform(ViewActions.click())
        android.os.SystemClock.sleep(3000)
        Intents.intended(IntentMatchers.hasComponent(MainActivity::class.java.getName()))
    }
}
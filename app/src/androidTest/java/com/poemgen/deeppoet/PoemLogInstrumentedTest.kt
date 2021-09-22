package com.poemgen.deeppoet

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
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

    /**
     * Run before each PoemLog-Instrumented Test:
     *  Initialise Intents.
     */
    @Before
    fun before() {
//        Intents.init()
    }

    /**
     * Run after each PoemLog-Instrumented Test:
     *  Release Intents.
     */
    @After
    fun after() {
//        Intents.release()
    }


    /**
     * Tests button to return to Main Activity from Pog Log Activity.
     */
    @Test
    fun clickShowMainButton() {
        launchActivity<MainActivity>()
        onView(withId(R.id.hamburgerButton)).perform(click())
        onView(withId(R.id.showLogButton)).perform(click())
        android.os.SystemClock.sleep(2500)

        // Hamburger button should not be visible while on help screen.
        onView(withId(R.id.hamburgerButton)).check(doesNotExist())

        android.os.SystemClock.sleep(1000)
        onView(withId(R.id.showMain)).perform(click())

        // Can't check if intent is launched, because no intent is launched.
        // Check if view is visible instead, as a proxy.
        onView(withId(R.id.hamburgerButton)).check(matches(isDisplayed()))

    }
}
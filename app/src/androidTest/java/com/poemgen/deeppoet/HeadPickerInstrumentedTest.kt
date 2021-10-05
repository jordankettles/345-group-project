package com.poemgen.deeppoet

import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.poemgen.deeppoet.util.HeadCollection
import org.hamcrest.EasyMock2Matchers.equalTo
import org.hamcrest.Matcher
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Tests for Head Picker screen and functions.
 */
@RunWith(AndroidJUnit4::class)
class HeadPickerInstrumentedTest {

    /**
     * Tests the button that moves from headpicker to main screen.
     */
    @Test
    fun clickShowMainButton() {
        val scenario = launchActivity<HeadPickerActivity>()
        onView(withId(R.id.showMain_HeadPicker)).perform(click())
        android.os.SystemClock.sleep(1000)
        assert(scenario.state == Lifecycle.State.DESTROYED)
    }

    /**
     * Tests head picker.
     * Clicks on the first entry, checks that selected head has been changed.
     * Subsequently does the same with the second head down.
     */
    @Test
    fun testHeadPickerInventory() {
        launchActivity<MainActivity>()
        android.os.SystemClock.sleep(3000)

        onView(withId(R.id.hamburgerButton)).perform(click())
        android.os.SystemClock.sleep(1000)

        onView(withId(R.id.headPickerButton)).perform(click())
        android.os.SystemClock.sleep(1000)

        onView(withId(R.id.rvHeadPickerList)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        android.os.SystemClock.sleep(1000)
        assert(HeadCollection.currentHeadIndex == 0)

        onView(withId(R.id.rvHeadPickerList)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        android.os.SystemClock.sleep(1000)
        assert(HeadCollection.currentHeadIndex == 1)

        onView(withId(R.id.rvHeadPickerList)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
        android.os.SystemClock.sleep(1000)
        assert(HeadCollection.currentHeadIndex == 2)

        onView(withId(R.id.rvHeadPickerList)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(3, scrollTo()), actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
        android.os.SystemClock.sleep(1000)
        assert(HeadCollection.currentHeadIndex == 3)

        onView(withId(R.id.rvHeadPickerList)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(4, scrollTo()), actionOnItemAtPosition<RecyclerView.ViewHolder>(4, click()))
        android.os.SystemClock.sleep(1000)
        assert(HeadCollection.currentHeadIndex == 4)

        onView(withId(R.id.rvHeadPickerList)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(5, scrollTo()), actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))
        android.os.SystemClock.sleep(1000)
        assert(HeadCollection.currentHeadIndex == 5)
    }

}
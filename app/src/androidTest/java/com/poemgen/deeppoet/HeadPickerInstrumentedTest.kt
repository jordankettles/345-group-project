package com.poemgen.deeppoet

import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.poemgen.deeppoet.util.HeadCollection
import org.hamcrest.EasyMock2Matchers.equalTo
import org.hamcrest.Matcher
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

    @Test
    fun testHeadPickerInventory() {
        launchActivity<MainActivity>()
        android.os.SystemClock.sleep(3000)

        onView(withId(R.id.hamburgerButton)).perform(click())
        android.os.SystemClock.sleep(1000)

        onView(withId(R.id.headPickerButton)).perform(click())
        android.os.SystemClock.sleep(1000)

        onView(withId(R.id.rvHeadPickerList)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        android.os.SystemClock.sleep(1000)
        assert(HeadCollection.currentHeadIndex == 0)

        onView(withId(R.id.rvHeadPickerList)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        android.os.SystemClock.sleep(1000)
        assert(HeadCollection.currentHeadIndex == 1)
    }

}
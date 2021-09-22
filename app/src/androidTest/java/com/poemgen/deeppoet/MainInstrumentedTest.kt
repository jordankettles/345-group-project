package com.poemgen.deeppoet

import android.content.Intent
import android.view.View
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*

import org.junit.Test
import org.junit.runner.RunWith

import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import androidx.test.espresso.ViewAction

import androidx.test.espresso.UiController
import com.google.android.material.textfield.TextInputEditText
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.Matchers.*


/**
 * Main Activity Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MainInstrumentedTest {


    /**
     * getText Gets text from a textview.
     * Thank you to user haffax on Stack overflow for this function.
     * @param matcher Matcher for the textview.
     * @return String the string in the textview.
     */
    fun getText(matcher: Matcher<View?>?): String? {
        val stringHolder = arrayOf<String?>(null)
        onView(matcher).perform(object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(TextView::class.java)
            }

            override fun getDescription(): String {
                return "getting text from a TextView"
            }

            override fun perform(uiController: UiController?, view: View) {
                val tv = view as TextView //Save, because of check in getConstraints()
                stringHolder[0] = tv.text.toString()
            }
        })
        return stringHolder[0]
    }

    /**
     * getTextfromEditText Gets text from a TextInputEditText.
     * Modified from user haffax's function on Stack overflow for this function.
     * @param matcher Matcher for the TextInputEditText.
     * @return String the string in the TextInputEditText.
     */
    fun getTextfromEditText(matcher: Matcher<View?>?): String? {
        val stringHolder = arrayOf<String?>(null)
        onView(matcher).perform(object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(TextInputEditText::class.java)
            }

            override fun getDescription(): String {
                return "getting text from a TextView"
            }

            override fun perform(uiController: UiController?, view: View) {
                val tv = view as TextInputEditText //Save, because of check in getConstraints()
                stringHolder[0] = tv.text.toString()
            }
        })
        return stringHolder[0]
    }

    /**
     * chooser Creates a custom matcher for our share poem intent.
     * Thank you to user Skizo-ozᴉʞS on Stack overflow for this function.
     * @param matcher Matcher for the intent.
     * @return matcher The matcher wrapped in an ACTION_CHOOSER intent matcher.
     */
    fun chooser(matcher: Matcher<Intent>): Matcher<Intent> {
        return allOf(
            hasAction(Intent.ACTION_CHOOSER),
            hasExtra(`is`(Intent.EXTRA_INTENT), matcher))
    }

    /**
     * Test that the Generate button and the share button works.
     */
    @Test
    fun testGenerateButton() {
        launchActivity<MainActivity>()
        android.os.SystemClock.sleep(5000)
        onView(withId(R.id.promptField)).perform(typeText("Hello, I am a poet"))
        Intents.init()
        onView(withId(R.id.submit_prompt_button)).perform(click())
        onView(withId(R.id.poemTextView)).check(matches(withText("Hello, I am a poet")))
        android.os.SystemClock.sleep(50000)
        val generatedTxt = getText(withId(R.id.poemTextView))
        onView(withId(R.id.share_button)).perform(click())
        val expectedIntent = anyOf(
            hasAction(Intent.ACTION_SEND),
            hasExtra(Intent.EXTRA_TEXT, generatedTxt),
            hasExtra(Intent.EXTRA_SUBJECT, "Subject Here"),
            hasExtra(Intent.EXTRA_TITLE, "Share Via"),
            hasType("text/plain")
        )
        intended(chooser(expectedIntent))
        Intents.release()
    }

    /**
     * Test that the Poem Log stores poems correctly.
     */
    @Test
    fun testPoemStorage() {
        launchActivity<MainActivity>()
        android.os.SystemClock.sleep(5000)
        onView(withId(R.id.promptField)).perform(typeText("Hello, I am a poet"))
        Intents.init()
        onView(withId(R.id.submit_prompt_button)).perform(click())
        onView(withId(R.id.poemTextView)).check(matches(withText("Hello, I am a poet")))
        android.os.SystemClock.sleep(50000)
        val generatedTxt = getText(withId(R.id.poemTextView))
        onView(withId(R.id.hamburgerButton)).perform(click())
        onView(withId(R.id.showLogButton)).perform(click())
        val pTitle = getText(withId(R.id.poem_title))
        val pText = getText(withId(R.id.poem_text))
        assert((pTitle + pText) == generatedTxt)
        intended(hasComponent(LogDisplayActivity::class.java.getName()))
        Intents.release()
    }

    /**
     * Test that the Hamburger button works.
     */
    @Test
    fun testHamburgerButton() {
        launchActivity<MainActivity>()
        onView(withId(R.id.hamburgerButton)).perform(click())
        onView(withId(R.id.showLogButton)).check(matches(isDisplayed()))
        onView(withId(R.id.helpButton)).check(matches(isDisplayed()))
        onView(withId(R.id.headPickerButton)).check(matches(isDisplayed()))
        onView(withId(R.id.hamburgerButton)).perform(click())
        onView(withId(R.id.showLogButton)).check(matches(not(isDisplayed())))
        onView(withId(R.id.helpButton)).check(matches(not(isDisplayed())))
        onView(withId(R.id.headPickerButton)).check(matches(not(isDisplayed())))
    }

    /**
     * Test that the Help button works.
     */
    @Test
    fun testHelpButton() {
        launchActivity<MainActivity>()
        onView(withId(R.id.hamburgerButton)).perform(click())
        onView(withId(R.id.helpButton)).check(matches(isDisplayed()))
        Intents.init()
        android.os.SystemClock.sleep(2000)
        onView(withId(R.id.helpButton)).perform(click())
        intended(hasComponent(HelpActivity::class.java.getName()))
        Intents.release()
    }

    /**
     *  Test that the random prompt button works.
     *
     */
    @Test
    fun testRandomPromptButton(){
        launchActivity<MainActivity>()
        android.os.SystemClock.sleep(2400)
        onView(withId(R.id.random_prompt_button)).perform(click())
        val r_text = getTextfromEditText(withId(R.id.promptField))
        if(r_text != null){
            assert(r_text.isNotEmpty())
        }

    }

    /**
     * Test that the slur filter works.
     */
    @Test
    fun testBadWords() {
        launchActivity<MainActivity>()
        onView(withId(R.id.promptField)).perform(typeText("abo"))
        onView(withId(R.id.submit_prompt_button)).perform(click())
        onView(withId(R.id.poemTextView)).check(matches(withText("")))
    }

}


package com.poemgen.deeppoet

import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import android.transition.AutoTransition
import android.util.Log
import android.view.animation.AccelerateInterpolator
import com.google.android.material.textfield.TextInputEditText
import com.poemgen.deeppoet.databinding.ActivityMainBinding
import com.poemgen.deeppoet.poemgenerator.record.Garden
import com.poemgen.deeppoet.util.Head
import com.poemgen.deeppoet.util.HeadCollection
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Main activity class. Defines functions triggered by UI action.
 */
class MainActivity : AppCompatActivity() {
    /**
     * @property gpt2 poem generator. Also feeds data straight to UI through viewModels.
     */
    private val gpt2: com.poemgen.deeppoet.ml.GPT2Client by viewModels()

    /**
     * @property randomPrompts Suggested prompts that are filled in when using the random button.
     * Strings are read from prompts.txt in the 'assets' folder.
     */
    lateinit var randomPrompts: List<String>

    /**
     * @property ready Whether generator is ready to accept new prompt.
     * Disables button when set to false.
     */
    private val _ready = MutableLiveData(true)
    val ready: LiveData<Boolean> = _ready

    // Share button
    private lateinit var buttonShare: Button

    //Continue Button
    private lateinit var buttonContinue: Button

    // Hamburger menu
    private lateinit var buttonLog: Button
    private lateinit var buttonHeadPicker: Button
    private lateinit var buttonHelp: Button
    private lateinit var buttonHamburgerMenu: Button

    private lateinit var labelLog: TextView
    private lateinit var labelHeadPicker: TextView
    private lateinit var labelHelp: TextView

    lateinit var imageHead: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        Garden.loadGarden(this)

        // Head setup head
        HeadCollection.initializeHeads()
        imageHead = findViewById(R.id.imageHead) as ImageView
        imageHead.scaleType = ImageView.ScaleType.FIT_START
        switchHeadAnimation(toTalking = false)

        // GPT2 client related things
        randomPrompts = readPrompts()

        val promptField = findViewById<TextInputEditText>(R.id.promptField)

        disableButtons()
        gpt2.mainActivity = this
        binding.vm = gpt2
        binding.mainActivity = this
        binding.lifecycleOwner = this

        // Hamburger related buttons
        labelLog = findViewById(R.id.showLogLabel) as TextView
        labelHelp = findViewById(R.id.helpLabel) as TextView
        labelHeadPicker = findViewById(R.id.headPickerLabel) as TextView

        val buttonGenerate = findViewById<Button>(R.id.submit_prompt_button)
        buttonGenerate.setOnClickListener{
            if (!promptField.getText().toString().equals("")) {
                gpt2.setPrompt(promptField.getText().toString())

                hideKeyboard()
            } else {
                Toast.makeText(this, "Please enter a prompt.", Toast.LENGTH_SHORT).show()
            }

            if(firstTime && !promptField.getText().toString().equals("")) {
                stowPromptLayout()
                firstTime = false
            }
        }

        buttonLog = findViewById<Button>(R.id.showLogButton)
        buttonLog.setOnClickListener{
            Garden.loadGarden(this)
//            gpt2.closeGenerator()
            val intent = Intent(this, LogDisplayActivity::class.java)
            startActivity(intent)
//            finish()
        }

        buttonHelp = findViewById<Button>(R.id.helpButton)
        buttonHelp.setOnClickListener{
//            gpt2.closeGenerator()
            val intent = Intent(this, HelpActivity::class.java)
            startActivity(intent)
//            finish()
        }

        buttonHeadPicker = findViewById<Button>(R.id.headPickerButton)
        buttonHeadPicker.setOnClickListener{
            val intent =Intent(this, HeadPickerActivity::class.java)
            startActivity(intent)
        }

        buttonHamburgerMenu = findViewById(R.id.hamburgerButton)
        initHamburgerMenu()

        buttonShare = findViewById<Button>(R.id.share_button)
        buttonShare.setOnClickListener {
//            gpt2.closeGenerator()
            val result = findViewById(R.id.poemTextView) as TextView
            val text: String = result.getText().toString()
            shareTextOnly(text)
        }

        buttonContinue = findViewById<Button>(R.id.continue_button)
        buttonContinue.setOnClickListener{
            if (poemTextView.text != "") {
                gpt2.setPrompt(poemTextView.text.toString())
                hideKeyboard()
            } else {
                Toast.makeText(this, "Current Poem is blank.", Toast.LENGTH_SHORT).show()
            }
        }

        val buttonRandom = findViewById<Button>(R.id.random_prompt_button)
        buttonRandom.setOnClickListener{
            promptField.setText(randomPrompts.random())
            hideKeyboard()
        }

    }

    override fun onResume() {
        super.onResume()
        switchHeadAnimation(false)
    }

    var firstTime = true

    fun disableButtons() {
        _ready.value = false
        switchHeadAnimation(true)

    }

    fun enableButtons() {
        _ready.value = true
        switchHeadAnimation(false)
//        Log.d("buttonShare: ", buttonShare.y.toString())
        if (buttonShare.visibility == View.GONE && buttonContinue.visibility == View.GONE) {
            if (!firstTime) {
                fadeInShareContinueButton()
            }
        }
    }

    private fun switchHeadAnimation(toTalking: Boolean) {
        if (toTalking) {
            imageHead.setImageResource(HeadCollection.getSelectedHead().getRandomTalking())
        } else {
            imageHead.setImageResource(HeadCollection.getSelectedHead().getRandomIdle())
        }

        (imageHead.drawable as AnimationDrawable).start()
    }

    private fun stowPromptLayout() {
        val constraintLayout = findViewById(R.id.main_layout) as ConstraintLayout

        val constraintSet = ConstraintSet()
        constraintSet.clone(constraintLayout)

        constraintSet.setVerticalBias(R.id.prompt_layout, 0.05f)

        val transition = AutoTransition()
        transition.duration = 1000
        transition.interpolator = AccelerateDecelerateInterpolator()

        TransitionManager.beginDelayedTransition(constraintLayout, transition)
        constraintSet.applyTo(constraintLayout)
    }

    private fun initHamburgerMenu() {
        var menuOpen = false

        val xDist = -130F
        val yDist = -350F
        val duration = 300.0

        buttonHamburgerMenu.setOnClickListener {
            val menuOpenConst = if(menuOpen) 0 else 1

            showAllUtilityButtonsLabels()

            buttonHamburgerMenu.animate()
                .setDuration(duration.toLong())
                .rotation(360F * menuOpenConst)
                .withEndAction { menuOpen = !menuOpen; Log.d("menuOpen: ", menuOpen.toString())
                                    if(!menuOpen) {
                                        hideAllUtilityButtonsLabels()
                                    }}
                .start()

            buttonLog.animate()
                .setDuration(duration.toLong())
                .translationY(yDist * menuOpenConst)
                .setInterpolator(AccelerateInterpolator())
                .start()

            buttonHeadPicker.animate()
                .setDuration(duration.toLong()*2/3)
                .translationY(yDist * menuOpenConst*2/3)
                .setInterpolator(AccelerateInterpolator())
                .start()

            buttonHelp.animate()
                .setDuration(duration.toLong()/3)
                .translationY(yDist * menuOpenConst/3)
                .setInterpolator(AccelerateInterpolator())
                .start()

            labelLog.animate()
                .setDuration(duration.toLong())
                .translationY(yDist * menuOpenConst)
                .translationX(xDist * menuOpenConst)
                .alpha(1F * menuOpenConst)
                .setInterpolator(AccelerateInterpolator())
                .start()

            labelHeadPicker.animate()
                .setDuration(duration.toLong()*2/3)
                .translationY(yDist * menuOpenConst*2/3)
                .translationX(xDist * menuOpenConst)
                .alpha(1F * menuOpenConst)
                .setInterpolator(AccelerateInterpolator())
                .start()

            labelHelp.animate()
                .setDuration(duration.toLong()/3)
                .translationY(yDist * menuOpenConst/3)
                .translationX(xDist * menuOpenConst)
                .alpha(1F * menuOpenConst)
                .setInterpolator(AccelerateInterpolator())
                .start()
        }
    }

    private fun fadeInShareContinueButton() {
        buttonShare.visibility = View.VISIBLE
        buttonContinue.visibility = View.VISIBLE

        buttonShare.animate()
            .setDuration(800L)
            .translationY(0f)
            .alpha(1F)
            .start()

        buttonContinue.animate()
            .setDuration(800L)
            .translationY(0f)
            .alpha(1F)
            .start()
    }

    private fun showAllUtilityButtonsLabels() {
        buttonLog.visibility = View.VISIBLE
        buttonHeadPicker.visibility = View.VISIBLE
        buttonHelp.visibility = View.VISIBLE

        labelLog.visibility = View.VISIBLE
        labelHeadPicker.visibility = View.VISIBLE
        labelHelp.visibility = View.VISIBLE
    }

    private fun hideAllUtilityButtonsLabels() {
        buttonLog.visibility = View.GONE
        buttonHeadPicker.visibility = View.GONE
        buttonHelp.visibility = View.GONE

        labelLog.visibility = View.GONE
        labelHeadPicker.visibility = View.GONE
        labelHelp.visibility = View.GONE
    }

    /**
     * Reads prompt.txt from 'assets' folder.
     * @return List<String> of each line in prompt.txt
     */
    fun readPrompts(): List<String> {
        val fileName = "prompts.txt"
        val bufferedReader = application.assets.open(fileName).bufferedReader()
        return bufferedReader.readLines()
    }

    /**
     * Hides keyboard. Call when prompt entered.
     */
    fun hideKeyboard() {
        try {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        } catch(e: KotlinNullPointerException) {
            //Do nothing.
            return
        }
    }

    /**
     * Allows the share button to share poems.
     * @param title The input string to be shared.
     */
    fun shareTextOnly(title: String) {
        val intentt = Intent(Intent.ACTION_SEND)
        intentt.type = "text/plain"
        intentt.putExtra(Intent.EXTRA_SUBJECT, "Subject Here")
        intentt.putExtra(Intent.EXTRA_TEXT, title)
        startActivity(Intent.createChooser(intentt, "Share Via"))
    }

}




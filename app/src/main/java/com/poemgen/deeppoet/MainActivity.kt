package com.poemgen.deeppoet

import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.textfield.TextInputEditText
import com.poemgen.deeppoet.databinding.ActivityMainBinding
import com.poemgen.deeppoet.util.Head

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

    private lateinit var buttonLog: Button
    private lateinit var buttonHelp: Button
    private lateinit var buttonHeadPicker: Button

    private lateinit var labelLog: TextView
    private lateinit var labelHelp: TextView
    private lateinit var labelHeadPicker: TextView

    // Headtype, Idle/Talk, variations
    private var selectedHeadIndex = 0;
    private var animationList = mutableListOf<Head>()

    lateinit var imageHead: ImageView

    fun initAnimation() {
        animationList.add(Head(mutableListOf(R.drawable.anim_placeholder_dino_01),
                                mutableListOf(R.drawable.anim_placeholder_dino_02)))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Head setup head
        initAnimation()
        imageHead = findViewById(R.id.imageHead) as ImageView
        switchHeadAnimation(toTalking = false)

        // Hamburger related buttons


        randomPrompts = readPrompts()

        val promptField = findViewById<TextInputEditText>(R.id.promptField)

        disableButtons()
        gpt2.mainActivity = this
        binding.vm = gpt2
        binding.mainActivity = this
        binding.lifecycleOwner = this

        val buttonGenerate = findViewById<Button>(R.id.submit_prompt_button)
        buttonGenerate.setOnClickListener{
            if (!promptField.getText().toString().equals("")) {
                gpt2.setPrompt(promptField.getText().toString())

                hideKeyboard()
            } else {
                Toast.makeText(this, "Please enter a prompt.", Toast.LENGTH_SHORT).show()
            }

        }

        buttonLog = findViewById<Button>(R.id.showLogButton)
        buttonLog.setOnClickListener{
            gpt2.closeGenerator()
            val intent = Intent(this, LogDisplayActivity::class.java)
            startActivity(intent)
        }

        buttonHelp = findViewById<Button>(R.id.helpButton)
        buttonHelp.setOnClickListener{
            gpt2.closeGenerator()
            val intent = Intent(this, HelpActivity::class.java)
            startActivity(intent)
        }

        buttonHeadPicker = findViewById<Button>(R.id.headPickerButton)

        val buttonShare = findViewById<Button>(R.id.share_button)
        buttonShare.setOnClickListener {
            gpt2.closeGenerator()
            val result = findViewById(R.id.poemTextView) as TextView
            val text: String = result.getText().toString()
            shareTextOnly(text)
        }

        val buttonRandom = findViewById<Button>(R.id.random_prompt_button)
        buttonRandom.setOnClickListener{
            promptField.setText(randomPrompts.random())
            hideKeyboard()
        }

    }



    fun disableButtons() {
        _ready.value = false
        switchHeadAnimation(true)
    }

    fun enableButtons() {
        _ready.value = true
        switchHeadAnimation(false)
    }

    fun switchHeadAnimation(toTalking: Boolean) {
        if (toTalking) {
            imageHead.setBackgroundResource(animationList[selectedHeadIndex].getRandomTalking())
        } else {
            imageHead.setBackgroundResource(animationList[selectedHeadIndex].getRandomIdle())
        }

        (imageHead.background as AnimationDrawable).start()
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




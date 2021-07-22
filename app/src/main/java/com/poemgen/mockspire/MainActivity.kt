package com.poemgen.mockspire

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.compose.ui.text.input.TextFieldValue
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.textfield.TextInputEditText
import com.poemgen.mockspire.databinding.ActivityMainBinding
import com.poemgen.mockspire.poemgenerator.record.Garden
import java.util.*
import kotlin.concurrent.schedule

/**
 * Main activity class. Defines functions triggered by UI action.
 */
class MainActivity : AppCompatActivity() {
    /**
     * @property gpt2 poem generator. Also feeds data straight to UI through viewModels.
     */
    private val gpt2: com.poemgen.mockspire.ml.GPT2Client by viewModels()

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        randomPrompts = readPrompts()

        disableButtons()
        gpt2.mainActivity = this
        binding.vm = gpt2
        binding.mainActivity = this
        binding.lifecycleOwner = this

        val promptField = findViewById<TextInputEditText>(R.id.promptField)

        val visiblePoemTextView = findViewById<TextView>(R.id.visibleTextPoemView)

        val buttonGenerate = findViewById<Button>(R.id.submit_prompt_button)
        buttonGenerate.setOnClickListener{
            if (!promptField.getText().toString().equals("")) {
                gpt2.setPrompt(promptField.getText().toString())

                Timer().schedule(1000) {
                    visiblePoemTextView.setText("Still running, man.")

                    if(ready.value == true) {
                        visiblePoemTextView.setText("Stopped, man")
                        Timer().cancel()
                    }
                }

                hideKeyboard()
            } else {
                Toast.makeText(this, "Please enter a prompt.", Toast.LENGTH_SHORT).show()
            }

        }

        val buttonLog = findViewById<Button>(R.id.showLogButton)
        buttonLog.setOnClickListener{
            gpt2.closeGenerator()
            val intent = Intent(this, LogDisplayActivity::class.java)
            startActivity(intent)
        }

        val buttonHelp = findViewById<Button>(R.id.help_button)
        buttonHelp.setOnClickListener{
            gpt2.closeGenerator()
            val intent = Intent(this, HelpActivity::class.java)
            startActivity(intent)
        }

        val buttonRandom = findViewById<Button>(R.id.random_prompt_button)
        buttonRandom.setOnClickListener{
            promptField.setText(randomPrompts.random())
            hideKeyboard()
        }


    }

    fun disableButtons() {
        _ready.value = false
    }

    fun enableButtons() {
        _ready.value = true
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

}
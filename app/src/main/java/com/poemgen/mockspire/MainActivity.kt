package com.poemgen.mockspire

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.compose.ui.text.input.TextFieldValue
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.textfield.TextInputEditText
import com.poemgen.mockspire.databinding.ActivityMainBinding
import com.poemgen.mockspire.poemgenerator.record.Garden

class MainActivity : AppCompatActivity() {
    private val gpt2: com.poemgen.mockspire.ml.GPT2Client by viewModels()

    private val _ready = MutableLiveData(true)
    val ready: LiveData<Boolean> = _ready

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        gpt2.mainActivity = this
        binding.vm = gpt2
        binding.mainActivity = this
        binding.lifecycleOwner = this

        val promptField = findViewById<TextInputEditText>(R.id.promptField)

        val buttonGenerate = findViewById<Button>(R.id.submit_prompt_button)
        buttonGenerate.setOnClickListener{
            gpt2.setPrompt(promptField.getText().toString())


            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
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
    }

    fun disableButtons() {
        _ready.value = false
    }

    fun enableButtons() {
        _ready.value = true
    }
}
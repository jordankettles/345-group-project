package com.poemgen.mockspire

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.poemgen.mockspire.databinding.ActivityMainBinding
import com.poemgen.mockspire.poemgenerator.record.Garden

class MainActivity : AppCompatActivity() {
    private val gpt2: com.poemgen.mockspire.ml.GPT2Client by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.vm = gpt2

        binding.lifecycleOwner = this

        val buttonHelp = findViewById<Button>(R.id.showLogButton)
        buttonHelp.setOnClickListener{
            val intent = Intent(this, LogDisplayActivity::class.java)
            startActivity(intent)
        }

        val buttonLog = findViewById<Button>(R.id.help_button)
        buttonLog.setOnClickListener{
            val intent = Intent(this, HelpActivity::class.java)
            startActivity(intent)
        }
    }
}
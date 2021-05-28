package com.poemgen.mockspire

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.poemgen.mockspire.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val gpt2: com.poemgen.mockspire.ml.GPT2Client by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.vm = gpt2

        binding.lifecycleOwner = this
    }




}
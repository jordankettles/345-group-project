package com.poemgen.deeppoet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HeadPickerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_head_picker)

        val button = findViewById<Button>(R.id.showMain_HeadPicker)
        button.setOnClickListener{
            finish()
        }
    }
}
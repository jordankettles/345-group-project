package com.poemgen.deeppoet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.poemgen.deeppoet.poemgenerator.record.Garden

/**
 * Activity showing content of Garden/the poem log.
 */
class LogDisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_display)

        val button = findViewById<Button>(R.id.showMain)
        button.setOnClickListener{
            Garden.saveGarden(this)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
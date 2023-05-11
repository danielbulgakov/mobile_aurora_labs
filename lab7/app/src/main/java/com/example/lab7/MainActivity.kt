package com.example.lab7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textStackView)
        val stackClass = StackUpdateClass(this, textView)
    }

    fun onTrafficLightClicked(view: View) {
        Intent(this, LightsActivity::class.java).also { intent ->
            startActivity(intent)
        }
    }

    fun onTextClicked(view: View) {
        Intent(this, TextActivity::class.java).also { intent ->
            startActivity(intent)
        }
    }

    fun onStopWatch(view: View) {
        Intent(this, StopwatchActivity::class.java).also { intent ->
            startActivity(intent)
        }
    }

    fun onCustomContainer(view: View) {
        Intent(this, CustomContainerActivity::class.java).also { intent ->
            startActivity(intent)
        }
    }
}
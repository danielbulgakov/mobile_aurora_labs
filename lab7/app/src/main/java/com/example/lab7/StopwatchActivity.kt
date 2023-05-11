package com.example.lab7

import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import java.util.*


class StopwatchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stopwatch)
        textView = findViewById<TextView>(R.id.textView_timer)
    }


    private var isRunning = false
    private var startTime: Long = 0
    private var elapsedTime: Long = 0
    private lateinit var textView: TextView

    private fun runStopwatch() {
        isRunning = true
        startTime = SystemClock.elapsedRealtime()
        GlobalScope.launch {
            while (isRunning) {
                elapsedTime = SystemClock.elapsedRealtime() - startTime
                withContext(Dispatchers.Main) {
                    textView.text = formatTime(elapsedTime)
                }
                delay(1000L)
            }
        }
    }

    private fun formatTime(timeMillis: Long): String? {
        var seconds = (timeMillis / 1000).toInt()
        var minutes = seconds / 60
        val hours = minutes / 60
        seconds %= 60
        minutes %= 60
        return java.lang.String.format(
            Locale.getDefault(),
            "%02d:%02d:%02d",
            hours,
            minutes,
            seconds
        )
    }

    fun onStart(view: View) {
        runStopwatch()
    }
    fun onStop(view: View) {
        isRunning = false
    }
    fun onReset(view: View) {
        isRunning = false
        elapsedTime = 0
        textView.setText(formatTime(elapsedTime))
    }

}
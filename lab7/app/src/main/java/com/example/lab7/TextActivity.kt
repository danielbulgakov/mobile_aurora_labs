package com.example.lab7

import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class TextActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text)

        val text = findViewById<TextView>(R.id.textView)

        val animatorSet = AnimatorSet()

        window.decorView.rootView.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val translationY = ObjectAnimator.ofFloat(text, "translationY", 0f, 1500f)
                translationY.duration = 1000

                val rotation = ObjectAnimator.ofFloat(text, "rotation", 0f, 180f)
                rotation.duration = 1000

                val colorAnim: ObjectAnimator =
                    ObjectAnimator.ofInt(text, "textColor",Color.WHITE, Color.RED)
                colorAnim.setEvaluator(ArgbEvaluator())
                colorAnim.duration = 1000



                animatorSet.playSequentially(translationY, rotation, colorAnim)
                animatorSet.start()


            } else if (event.action == MotionEvent.ACTION_UP) {
                animatorSet.reverse()
            }
            true
        }

    }
}
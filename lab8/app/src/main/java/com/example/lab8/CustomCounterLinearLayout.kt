package com.example.lab8

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import org.w3c.dom.Text

class CustomCounterLinearLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var initValue : Int = 0
    private val counterClass : CounterClass
    private val container = findViewById<LinearLayout>(R.id.container)

    init {
        orientation = VERTICAL

        val a = context.obtainStyledAttributes(attrs, R.styleable.CustomCounterLinearLayout)
        initValue = a.getString(R.styleable.CustomCounterLinearLayout_customIntegerValue)!!.toInt()
        a.recycle()

        counterClass = CounterClass(initValue)

        // Init TextView
        val textView = TextView(context)
        textView.text = counterClass.getValue().toString()
        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER

        // Init Buttons
        // Increment
        val buttonIncrement = Button(context)
        buttonIncrement.text = "Увеличить"
        buttonIncrement.setOnClickListener {
            counterClass.incrementValue()
            textView.text = (counterClass.getValue()).toString()
        }
        // Reset
        val buttonReset = Button(context)
        buttonReset.text = "Сбросить"
        buttonReset.setOnClickListener {
            counterClass.resetValue()
            textView.text = (counterClass.getValue()).toString()
        }

        // Add objects to view
        container.addView(textView)
        container.addView(buttonIncrement)
        container.addView(buttonReset)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()


    }

    // Add any necessary methods to manipulate the child views.
}
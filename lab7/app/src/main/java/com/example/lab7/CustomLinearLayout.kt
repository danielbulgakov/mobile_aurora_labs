package com.example.lab7

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView


class CustomLinearLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private var textColorAttr : String = ""

    init {
        orientation = VERTICAL

        val a = context.obtainStyledAttributes(attrs, R.styleable.CustomLinearLayout)
        textColorAttr = a.getString(R.styleable.CustomLinearLayout_buttonColor).toString()
        a.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        // Measure the size of the view.
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        // Position the child views within the layout.
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        val container = findViewById<LinearLayout>(R.id.container)

        if (childCount > 0) {
            val child = getChildAt(0)
            var childText = ""
            if (child is TextView ) {
                childText = (child as TextView).text.toString()
            }
            val button = Button(context)
            button.text = childText
            button.setBackgroundColor(Color.parseColor(textColorAttr))
            container.addView(button)
        }

    }

    // Add any necessary methods to manipulate the child views.
}
package com.example.lab8

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class CustomListLinearLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private val listClass : StringListClass
    private val container = findViewById<LinearLayout>(R.id.container_list)

    init {
        orientation = VERTICAL

        listClass = StringListClass()

        // Init EditText
        val textEdit = EditText(context)
        textEdit.hint = "Введите текст"
        textEdit.textAlignment = View.TEXT_ALIGNMENT_CENTER

        // Init TextView
        val textView = TextView(context)
        textView.text = "Пустой массив"
        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER

        // Init Buttons
        // Increment
        val buttonIncrement = Button(context)
        buttonIncrement.text = "Добавить слово"
        buttonIncrement.setOnClickListener {
            listClass.addWord(textEdit.text.toString())
            textView.text = listClass.toString()
        }
        // Reset
        val buttonReset = Button(context)
        buttonReset.text = "Удалить слово"
        buttonReset.setOnClickListener {
            listClass.removeWord()
            textView.text = listClass.toString()
        }



        // Add objects to view
        container.addView(textEdit)
        container.addView(buttonIncrement)
        container.addView(buttonReset)
        container.addView(textView)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()


    }

    // Add any necessary methods to manipulate the child views.
}
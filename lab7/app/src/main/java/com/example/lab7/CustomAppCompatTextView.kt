package com.example.lab7

import android.content.Context
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.AbsoluteSizeSpan
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView


class CustomAppCompatTextView : AppCompatTextView {

    private var typeface: Typeface? = null
    private val time = "00:00:00"
    private val secSize = 100
    private val minSize = 90
    private val hourSize = 100

    override fun setText(text: CharSequence?, type: BufferType?) {
        super.setText(text, type)
        val time = text.toString();
        val spannable = SpannableString(time);

        // Set the text size for hours
        spannable.setSpan(AbsoluteSizeSpan(hourSize), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Set the text size for minutes
        spannable.setSpan(AbsoluteSizeSpan(minSize), 3, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Set the text size for seconds
        spannable.setSpan(AbsoluteSizeSpan(secSize), 6, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        super.setText(spannable, type);
    }

    constructor(context: Context?) : super(context!!) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!, attrs, defStyle
    ) {
        init()
    }

    private fun init() {
        val spannable = SpannableString(time);

        // Set the text size for hours
        spannable.setSpan(AbsoluteSizeSpan(hourSize), 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Set the text size for minutes
        spannable.setSpan(AbsoluteSizeSpan(minSize), 3, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Set the text size for seconds
        spannable.setSpan(AbsoluteSizeSpan(secSize), 6, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        text = spannable
    }
}
package com.example.lab4

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener

class SwipeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe)

        val refreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)
        val textField = findViewById<TextView>(R.id.textUpdate)

        refreshLayout.setOnRefreshListener(OnRefreshListener {
            textField.text = "Обновление страницы"
            Handler(Looper.getMainLooper()).postDelayed({
                textField.text = "Готово"
                 refreshLayout.isRefreshing = false
            }, 3000)
        })
    }
}
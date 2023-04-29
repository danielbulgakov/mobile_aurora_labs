package com.example.lab6

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.lab6.Activities.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onTask1And3(view: View) {
        Intent(this, RectangleListActivity::class.java).also { intent ->
            startActivity(intent)
        }
    }

    fun onTask2(view: View) {
        Intent(this, RectangleRemoveActivity::class.java).also { intent ->
            startActivity(intent)
        }
    }

    fun onTask4(view: View) {
        Intent(this, MoneyActivity::class.java).also { intent ->
            startActivity(intent)
        }
    }

    fun onTask5(view: View) {
        Intent(this, MoneyReqActivity::class.java).also { intent ->
            startActivity(intent)
        }
    }

    fun onTask6(view: View) {
        Intent(this, DataBaseActivity::class.java).also { intent ->
            startActivity(intent)
        }
    }
}
package com.example.lab4

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat


class MainActivity : AppCompatActivity() {

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            stackSizeUpdate()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startService(Intent(this, NotificationHandle::class.java).also { intent ->
            intent.putExtra("ACTION", 0)
        })

        stackSizeUpdate()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        stackSizeUpdate()
    }

    private fun stackSizeUpdate() {
        val tv : TextView = findViewById<TextView>(R.id.textViewValue)
        val activityManager : ActivityManager = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        val tasks = activityManager.appTasks
        tv.text = tasks[tasks.size - 1].taskInfo.numActivities.toString()
    }

    fun onAddPage(view: View) {
        Intent(this, NewPageActivity::class.java).also { intent ->
//            intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
            intent.putExtra("destroy", false)
            startForResult.launch(intent)
        }
    }


    fun onDeletePage(view: View) {
        Intent(this, NewPageActivity::class.java).also { intent ->
            intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
            intent.putExtra("destroy", true)
            startForResult.launch(intent)
        }
    }

    fun onTaskPageAdd(view: View) {
        Intent(this, TaskListActivity::class.java).also { intent ->
            startActivity(intent)
        }
    }

    fun onStackUpdate(view: View) {
        stackSizeUpdate()
    }

    fun onWeb(view: View) {
        val alert: AlertDialog.Builder = AlertDialog.Builder(this)
        alert.setTitle("Гугль")

        val wv = WebView(this)
        wv.loadUrl("https:\\www.google.com")
        wv.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }

        alert.setView(wv)
        alert.setNegativeButton("Close",
            DialogInterface.OnClickListener { dialog, id -> dialog.dismiss() })
        alert.show()
    }

    fun onSwipe(view: View) {
        Intent(this, SwipeActivity::class.java).also { intent ->
            startActivity(intent)
        }
    }
    fun onMenu(view: View) {
        Intent(this, ContextMenuActivity::class.java).also { intent ->
            startActivity(intent)
        }
    }
}
package com.example.lab4

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout

class TaskTabsActivity : AppCompatActivity() {

    private val adapter = TaskTabsAdapter()

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        setContentView(R.layout.activity_task_tabs)

        val viewPager = findViewById<ViewPager2>(R.id.pager)
        viewPager.adapter = adapter
    }

    fun onTaskAdd(view: View) {
        val intent = Intent(this, TaskAddActivity::class.java)
        startForResult.launch(intent)
    }

    fun onViewChanged(view: View) {
        finish()
        val intent = Intent(this, TaskListActivity::class.java)
        startActivity(intent)
    }
}
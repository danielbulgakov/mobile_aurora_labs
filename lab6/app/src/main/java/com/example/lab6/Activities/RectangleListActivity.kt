package com.example.lab6.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab6.Adapters.RectangleListAdapter
import com.example.lab6.R
import com.example.lab6.SimpleRecyclerList.RectangleListBuilder

class RectangleListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rectangle_list)

        val list = RectangleListBuilder.build()
        list.getList().clear()

        list.addRectangle()
        list.addRectangle()
        list.addRectangle()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView);
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = RectangleListAdapter()
    }
}
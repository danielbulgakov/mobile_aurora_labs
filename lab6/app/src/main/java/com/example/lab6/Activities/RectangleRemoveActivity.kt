package com.example.lab6.Activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab6.Adapters.RectangleRemoveAdapter
import com.example.lab6.R
import com.example.lab6.SimpleRecyclerList.ItemRectangle
import com.example.lab6.SimpleRecyclerList.RectangleListBuilder

class RectangleRemoveActivity : AppCompatActivity() {
    private var counter : Int = 0
    private var adapter = RectangleRemoveAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rectangle_remove)

        val list = RectangleListBuilder.build()
        list.getList().clear()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView);
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
    }

    fun onAdd(view: View) {
        val list = RectangleListBuilder.build()
        list.addRectangle(ItemRectangle(Color.BLACK, Color.WHITE, "Элемент ${counter++}"))
        adapter.notifyDataSetChanged()
    }
}
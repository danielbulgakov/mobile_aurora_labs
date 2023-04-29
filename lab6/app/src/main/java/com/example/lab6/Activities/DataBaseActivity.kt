package com.example.lab6.Activities

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab6.Adapters.RectangleRemoveDBAdapter
import com.example.lab6.DataBase.AppDataBase
import com.example.lab6.DataBase.DataBaseBuilder
import com.example.lab6.DataBase.EntityTask
import com.example.lab6.R
import com.example.lab6.SimpleRecyclerList.ItemRectangle
import com.example.lab6.SimpleRecyclerList.RectangleListBuilder

class DataBaseActivity : AppCompatActivity() {

    private lateinit var db : AppDataBase
    private lateinit var adapter : RectangleRemoveDBAdapter
    private val rlist = RectangleListBuilder.build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_base)

        db = DataBaseBuilder().build(this.applicationContext)!!
        adapter = RectangleRemoveDBAdapter()
//        db!!.taskDAO().deleteAll()
        val list = db!!.taskDAO().getAll()
        rlist.getList().clear()

        list.forEach {
            rlist.addRectangle(ItemRectangle(Color.BLACK, Color.WHITE, it.taskTitle.toString()))
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView);
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
    }

    fun onAdd(view: View) {
        val text = findViewById<EditText>(R.id.editTextInput).text.toString()
        db!!.taskDAO().insertAll(EntityTask(text))
        rlist.getList().clear()
        db!!.taskDAO().getAll().forEach {
            rlist.addRectangle(ItemRectangle(Color.BLACK, Color.WHITE, it.taskTitle.toString()))
        }
        adapter.notifyDataSetChanged()
    }
}
package com.example.lab6.Adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.lab6.DataBase.DataBaseBuilder
import com.example.lab6.R
import com.example.lab6.SimpleRecyclerList.ItemRectangle
import com.example.lab6.SimpleRecyclerList.RectangleListBuilder

class RectangleRemoveDBAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val rectangleList  = RectangleListBuilder.build()
    private val db = DataBaseBuilder().build(null)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_rectangle, viewGroup, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val rect = rectangleList.getRectangle(position)

        holder as MyViewHolder
        holder.title.text = rect.title
        holder.title.setTextColor(rect.titleColor)
        holder.layout.setBackgroundColor(rect.backgroundColor)

        holder.itemView.setOnClickListener {
            val task =  db!!.taskDAO().getAll()[position]
            db!!.taskDAO().delete(task)
            rectangleList.getList().clear()
            db!!.taskDAO().getAll().forEach {
                rectangleList.addRectangle(ItemRectangle(Color.BLACK, Color.WHITE, it.taskTitle.toString()))
            }
            this.notifyDataSetChanged()
        }

    }

    override fun getItemCount() = rectangleList.getSize()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var layout: ConstraintLayout
        var title: TextView

        private val rectangleList  = RectangleListBuilder.build()

        init {
            layout = itemView.findViewById<ConstraintLayout>(R.id.itemLayout)
            title = itemView.findViewById<TextView>(R.id.itemTitle)
        }

    }
}
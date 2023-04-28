package com.example.lab6

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.lab6.SimpleRecyclerList.ColorMap
import com.example.lab6.SimpleRecyclerList.RectangleListBuilder

class RectangleRemoveAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val rectangleList  = RectangleListBuilder.build()

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
            rectangleList.removeRectangle(position)
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
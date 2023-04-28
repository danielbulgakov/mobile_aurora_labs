package com.example.lab6

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.lab6.SimpleRecyclerList.ColorMap
import com.example.lab6.SimpleRecyclerList.RectangleListBuilder


class RectangleListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
            val builder: AlertDialog.Builder = AlertDialog.Builder(it.context)
            builder.setTitle("Выберите цвет фона")
            val options = ColorMap.colorMap.values.toTypedArray()

            builder.setItems(options
            ) { _, which ->
                rectangleList.updateRectangle(
                    holder.title.currentTextColor,
                    ColorMap.colorMap.filterValues { it == options[which] }.keys.first(),
                    position
                )
                this.notifyDataSetChanged()
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

        holder.itemView.setOnLongClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(it.context)
            builder.setTitle("Выберите цвет текста")
            val options = ColorMap.colorMap.values.toTypedArray()

            builder.setItems(options
            ) { _, which ->
                rectangleList.updateRectangle(
                    ColorMap.colorMap.filterValues { it == options[which] }.keys.first(),
                    rect.backgroundColor,
                    position
                )
                this.notifyDataSetChanged()
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
            return@setOnLongClickListener true
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
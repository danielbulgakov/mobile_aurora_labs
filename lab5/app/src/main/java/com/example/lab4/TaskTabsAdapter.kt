package com.example.lab4

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4.task_list.TaskArrayBuild


class TaskTabsAdapter : RecyclerView.Adapter<TaskTabsAdapter.MyPagerView>() {

    private val taskList  = TaskArrayBuild.taskArrayList

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyPagerView {
        val itemView: View = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.page_listing, viewGroup, false)
        return MyPagerView(itemView)
    }

    override fun onBindViewHolder(holder: MyPagerView, position: Int) {
        val dummyParentDataItem: ArrayList<String>? = taskList.getChildList(position)

        holder.textViewParentName!!.text = taskList.getParentsList()[position].toString()
        //
        //
        val noOfChildTextViews: Int = holder.linearLayoutChildItems!!.childCount
        for (index in 0 until noOfChildTextViews) {
            val currentTextView = holder.linearLayoutChildItems!!.getChildAt(index) as TextView
            currentTextView.visibility = View.VISIBLE
        }

        val noOfChild: Int = dummyParentDataItem!!.size
        if (noOfChild < noOfChildTextViews) {
            for (index in noOfChild until noOfChildTextViews) {
                val currentTextView = holder.linearLayoutChildItems!!.getChildAt(index) as TextView
                currentTextView.visibility = View.GONE
            }
        }
        for (textViewIndex in 0 until noOfChild) {
            val currentTextView =
                holder.linearLayoutChildItems!!.getChildAt(textViewIndex) as TextView
            currentTextView.text = dummyParentDataItem[textViewIndex]
        }
    }

    override fun getItemCount() = taskList.getParentsList().size

    class MyPagerView(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var context: Context? = null
        var textViewParentName: TextView? = null
        var linearLayoutChildItems: LinearLayout? = null
        private val taskList  = TaskArrayBuild.taskArrayList

        init {
            context = itemView.context
            textViewParentName = itemView.findViewById<TextView>(R.id.tv_parentName)
            linearLayoutChildItems = itemView.findViewById<LinearLayout>(R.id.ll_child_items)
            linearLayoutChildItems!!.visibility = View.VISIBLE
            var intMaxNoOfChild = 10
            for (index in 0 until taskList.getParentsList().size) {
                val intMaxSizeTemp: Int = taskList.getChildList(taskList.getParentsList()[index])!!.size
                if (intMaxSizeTemp > intMaxNoOfChild) intMaxNoOfChild = intMaxSizeTemp
            }
            for (indexView in 0 until intMaxNoOfChild) {
                val textView = TextView(context)
                textView.id = indexView
                textView.textSize = 16f
                textView.setPadding(0, 20, 0, 20)
                textView.gravity = Gravity.CENTER
                textView.background =
                    ContextCompat.getDrawable(context!!, R.drawable.background_sub_module_text)
                val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                linearLayoutChildItems!!.addView(textView, layoutParams)
            }
        }


    }
}


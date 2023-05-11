package com.example.lab7

import android.app.ActivityManager
import android.content.Context
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.w3c.dom.Text

class StackUpdateClass(context: Context, textView : TextView) : StackUpdateListener {

    private var deletedPages = 0
    private var openedPages = 0

    private val ctx : Context
    private var lastCount = 0
    private val tv : TextView

    val runningState = true

    init {
        ctx = context
        tv = textView
        runBackgroundCoroutine()
    }

    private fun runBackgroundCoroutine() {
        val scope = CoroutineScope(Dispatchers.Main)
        scope.launch {
            while (runningState) {
                val activityManager : ActivityManager = ctx.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
                val tasks = activityManager.appTasks
                val taskCount = tasks[tasks.size - 1].taskInfo.numActivities
                if (taskCount > lastCount) {
                    openedPages += taskCount - lastCount
                    onAddPageStack(openedPages)
                }
                else {
                    deletedPages += lastCount - taskCount
                    onDeletePageStack(deletedPages)
                }

                lastCount = taskCount
                delay(1000L)
            }
        }
    }

    override fun onAddPageStack(newValue: Int) {
        val updateText = "Евент открылась новая страница"
        tv.text = "Открыто ${openedPages}, закрыто ${deletedPages} \n ${updateText}"
    }

    override fun onDeletePageStack(newValue: Int) {
        val updateText = "Евент закрылась страница"
        tv.text = "Открыто ${openedPages}, закрыто ${deletedPages} \n ${updateText}"
    }
}
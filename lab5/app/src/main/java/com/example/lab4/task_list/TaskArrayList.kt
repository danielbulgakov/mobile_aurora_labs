package com.example.lab4.task_list

import com.example.lab4.task_list.item.AType
import com.example.lab4.task_list.item.ArrayItem
import com.example.lab4.task_list.item.ITaskItem
import java.lang.reflect.Type
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class TaskArrayList {
    private val taskHash: HashMap<LocalDate, ArrayList<String>> = HashMap()
    private val keysArray = ArrayList<LocalDate>()

    private val list = ArrayList<ArrayItem>()

    fun add(item : ITaskItem) {
        if (keysArray.contains(item.date)) taskHash[item.date]!!.add(item.item)
        else {
            val array = ArrayList<String>()
            array.add(item.item)
            taskHash[item.date] = array
            keysArray.add(item.date)
            keysArray.sort()
        }
        build()
    }

    fun clear() {
        taskHash.clear()
        keysArray.clear()
    }

    fun getSize() = list.size
    fun getList() = list

    fun getParentsList() = keysArray
    fun getChildList(parent : LocalDate): ArrayList<String>? {
        return taskHash[parent]
    }
    fun getChildList(pos : Int) : ArrayList<String>? {
        return taskHash[keysArray[pos]]
    }

    private fun build() {
        list.clear()
        keysArray.forEach {
            list.add(ArrayItem(it.toString(), AType.HEADER))
            taskHash[it]!!.forEach { it1 ->
                list.add(ArrayItem(it1, AType.TASK))
            }
        }
    }

}
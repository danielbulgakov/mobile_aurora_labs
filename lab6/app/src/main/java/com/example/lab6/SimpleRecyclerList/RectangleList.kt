package com.example.lab6.SimpleRecyclerList

import android.graphics.Color

class RectangleList {
    private val list = ArrayList<ItemRectangle>()

    fun getSize() = list.size
    fun getList() = list

    fun addRectangle(item : ItemRectangle = ItemRectangle()) {
        list.add(item)
    }
    fun addRectangle(item : ItemRectangle, pos : Int) {
        list.add(pos, item)
    }
    fun updateRectangle(textColor : Int = Color.BLACK, backColor : Int = Color.WHITE, pos: Int) {
        list[pos].titleColor = textColor
        list[pos].backgroundColor = backColor
        list[pos].title = ColorMap.colorMap[backColor]!!
    }
    fun getRectangle(pos: Int) : ItemRectangle {
        return list[pos]
    }
    fun removeRectangle(pos: Int) {
        list.removeAt(pos)
    }

}
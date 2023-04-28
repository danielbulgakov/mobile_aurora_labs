package com.example.lab6.SimpleRecyclerList

import android.graphics.Color

data class ItemRectangle(
    var titleColor      : Int = Color.BLACK,
    var backgroundColor : Int = Color.WHITE,
    var title           : String = ColorMap.colorMap[backgroundColor]!!,
    val metaData        : Int = 0
)

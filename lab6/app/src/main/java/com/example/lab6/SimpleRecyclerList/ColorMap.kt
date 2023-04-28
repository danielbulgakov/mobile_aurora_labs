package com.example.lab6.SimpleRecyclerList

import android.graphics.Color
import android.provider.CalendarContract.Colors

class ColorMap {
    companion object {
        val colorMap = hashMapOf(
            Color.WHITE to "Белый",
            Color.BLACK to "Черный",
            Color.RED to "Красный",
            Color.BLUE to "Синий",
            Color.MAGENTA to "Фиолетовый",
            Color.YELLOW to "Желтый",
            Color.DKGRAY to "Темн. Серый",
            Color.TRANSPARENT to "Прозрачный"
        )
    }
}
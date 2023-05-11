package com.example.lab8

import java.lang.StringBuilder

class StringListClass {
    private val list = arrayListOf<String>()

    fun getList() = list
    fun addWord(word : String) {
        list.add(word.lowercase())
    }
    fun removeWord() {
        if (list.size != 0) {
            list.removeAt(0)
        }
    }

    override fun toString(): String {
        var stringBuilder = StringBuilder()

        for (i in list.indices) {
            if (i == 0) stringBuilder.append(list[i].replaceFirstChar(Char::uppercase))
            else stringBuilder.append(list[i])

            if (i != list.size - 1)
                stringBuilder.append(", ")
        }

        return stringBuilder.toString()
    }
}
package com.example.lab4.task_list.item

data class ArrayItem(val item : String, val type : AType) {
    override fun toString(): String {
        return item
    }
}

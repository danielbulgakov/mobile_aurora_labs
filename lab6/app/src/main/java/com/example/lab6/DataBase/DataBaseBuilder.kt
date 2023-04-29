package com.example.lab6.DataBase

import android.content.Context
import androidx.room.Room

class DataBaseBuilder {
    companion object {
        private var dataBase : AppDataBase? = null
    }
    fun build(ctx: Context?) : AppDataBase? {
        if (dataBase == null) {
            dataBase = ctx?.let {
                Room.databaseBuilder(
                    it,
                    AppDataBase::class.java, "tasks"
                )
                    .allowMainThreadQueries()
                    .build()
            }
        }
        return dataBase
    }
}
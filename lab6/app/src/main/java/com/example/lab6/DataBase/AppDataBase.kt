package com.example.lab6.DataBase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [EntityTask::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun taskDAO(): IDataAccess
}
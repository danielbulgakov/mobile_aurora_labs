package com.example.lab6.DataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface IDataAccess {
    @Query("SELECT * FROM entitytask")
    fun getAll(): List<EntityTask>

    @Insert
    fun insertAll(vararg tasks: EntityTask)

    @Delete
    fun delete(task: EntityTask)

    @Query("DELETE FROM entitytask")
    fun deleteAll()
}
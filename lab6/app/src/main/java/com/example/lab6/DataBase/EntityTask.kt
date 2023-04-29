package com.example.lab6.DataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EntityTask(
    @ColumnInfo(name = "task_title") val taskTitle: String?,
    @PrimaryKey(autoGenerate = true) val uid: Int = 0
)
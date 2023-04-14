package com.example.lab4.task_list.item

import java.time.LocalDate
import java.util.Date

interface ITaskItem {
    val date : LocalDate
    val item : String
}
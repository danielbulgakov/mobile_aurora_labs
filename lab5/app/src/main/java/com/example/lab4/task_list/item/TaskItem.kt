package com.example.lab4.task_list.item

import java.time.LocalDate
import java.util.*

data class TaskItem(override val item: String, override val date: LocalDate) : ITaskItem
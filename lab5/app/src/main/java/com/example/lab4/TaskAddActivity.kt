package com.example.lab4

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lab4.task_list.TaskArrayBuild
import com.example.lab4.task_list.item.TaskItem
import java.sql.Time
import java.time.LocalDate
import java.util.*


class TaskAddActivity : AppCompatActivity() {

    private val list = TaskArrayBuild.taskArrayList

    private var date : LocalDate = LocalDate.MAX

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_add)
    }

    fun onPickDate(view: View) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            val tv : TextView = findViewById<TextView>(R.id.textViewDate)
            tv.text = "${"%02d".format(dayOfMonth)}.${"%02d".format(monthOfYear + 1)}.${"%04d".format(year)}"

            date = LocalDate.of(year, monthOfYear + 1, dayOfMonth)

        }, year, month, day)

        dpd.show()
    }
    fun onPickTime(view: View) {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.YEAR)
        val min = c.get(Calendar.MONTH)

        var dtd = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener() { _, hour, min ->
            val tv : TextView = findViewById<TextView>(R.id.textViewTime)
            tv.text = "${"%02d".format(hour)}:${"%02d".format(min)}"

        }, hour, min, true)

        dtd.show()
    }

    fun onSubmit(view: View) {
        val tv : TextView = findViewById<TextView>(R.id.editText)

        if (tv.text == "" || date == LocalDate.MAX) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Ошибка")
            builder.setMessage("Некоторые поля остались пустыми")
            builder.show()
        } else {
            list.add(TaskItem(tv.text.toString(), date))
            val intent = Intent()
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

    }
}
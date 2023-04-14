package com.example.lab4

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import java.time.LocalDate

class ContextMenuActivity : AppCompatActivity() {

    private val colorHashMap : HashMap<String, Int> = hashMapOf(
        "Желтый" to Color.YELLOW,
        "Красный" to Color.RED,
        "Голубой" to Color.CYAN,
        "Черный" to Color.BLACK,
        "Фиолетовый" to Color.MAGENTA,
        "Белый" to Color.WHITE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_context_menu)

        val textViewContext = findViewById<TextView>(R.id.textViewContext)

        registerForContextMenu(textViewContext)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)

        menu!!.setHeaderTitle("Выберите цвет")

        colorHashMap.forEach {
            menu.add(0, v!!.id, 0, it.key)
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val relativeLayout = findViewById<RelativeLayout>(R.id.relativeLayout)
        Log.d("STATE OF COLOR LIST", colorHashMap.keys.indexOf(item.title.toString()).toString())
        colorHashMap[item.title.toString()]?.let { relativeLayout.setBackgroundColor(it) }

        return super.onContextItemSelected(item)
    }
}
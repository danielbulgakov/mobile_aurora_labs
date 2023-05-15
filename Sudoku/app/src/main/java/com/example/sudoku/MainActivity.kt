package com.example.sudoku

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.sudoku.core.Difficulty
import com.example.sudoku.wrappers.SudokuFieldBuilder
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private val paths = arrayOf("Легко", "Средне", "Сложно (Experimental)")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner = findViewById<View>(R.id.spinner) as Spinner
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this@MainActivity,
            android.R.layout.simple_spinner_item, paths
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.setAdapter(adapter)
        spinner.setOnItemSelectedListener(this)
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        GlobalScope.launch {
            when (p2) {
                0 -> {SudokuFieldBuilder.rebuild(Difficulty.EASY)}
                1 -> {SudokuFieldBuilder.rebuild(Difficulty.MIDDLE)}
                2 -> {SudokuFieldBuilder.rebuild(Difficulty.HARD)}
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    fun onGameStart(view: View) {
        Intent(this, GameFieldActivity::class.java).also { intent ->
            startActivity(intent)
        }
    }
}



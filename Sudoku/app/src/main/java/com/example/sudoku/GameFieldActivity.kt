package com.example.sudoku

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import androidx.appcompat.app.AlertDialog
import androidx.core.view.children
import com.example.sudoku.view.SudokuFieldView
import com.example.sudoku.wrappers.GameEndListener
import com.example.sudoku.wrappers.SudokuFieldBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameFieldActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_field)

        CoroutineScope(Dispatchers.Main).launch {
            val sudokuField = SudokuFieldBuilder.build()

            sudokuField.registerGameEndListener(object : GameEndListener {
                override fun onGameEnd() {
                    showWinDialog()
                }
            })


            val tableView = findViewById<TableLayout>(R.id.tableLayout)
            val sudoku = findViewById<SudokuFieldView>(R.id.sudokuLayout)

            for (row in tableView.children) {
                row as TableRow
                for (child in row.children) {
                    if (child.id != R.id.btn_end) {
                        child as Button
                        child.setOnClickListener {
                            sudokuField.trySetAtFocusedCell(child.text.toString().toInt())
                            sudoku.invalidate()
                        }
                    } else {
                        child as Button
                        child.setOnClickListener {
                            sudokuField.trySetAtFocusedCell(0)
                            sudoku.invalidate()
                        }
                    }
                }
            }
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
        SudokuFieldBuilder.reset()
    }

    @SuppressLint("ResourceType")
    fun showWinDialog() {
        val inflater = LayoutInflater.from(this)
        val view = inflater.inflate(R.drawable.dialog_layout, null)

        val dialog = AlertDialog.Builder(this)
            .setView(view)
            .create()

        view.findViewById<Button>(R.id.dialog_dismiss).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
            dialog.dismiss()
        }

        dialog.show()
    }

}
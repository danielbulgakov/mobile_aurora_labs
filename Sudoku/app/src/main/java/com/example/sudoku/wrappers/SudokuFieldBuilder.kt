package com.example.sudoku.wrappers

import com.example.sudoku.core.Difficulty
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SudokuFieldBuilder {
    companion object {
        private var field : SudokuField? = null

        fun build() : SudokuField {
            while (field == null) {}
            return field as SudokuField
        }

        fun rebuild(difficulty: Difficulty) {
            GlobalScope.launch {
                field = SudokuField(difficulty)
                val a = 1
            }
        }

        fun reset() {
            field = null
        }
    }
}
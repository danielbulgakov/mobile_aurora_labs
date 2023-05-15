package com.example.sudoku.wrappers

import com.example.sudoku.core.Difficulty

class SudokuFieldBuilder {
    companion object {
        var field : SudokuField? = null

        fun build() : SudokuField? {
            return field
        }

        fun rebuild(difficulty: Difficulty) {
            field = SudokuField(difficulty)
        }
    }
}
package com.example.sudoku.wrappers

import com.example.sudoku.core.Difficulty
import com.example.sudoku.core.SudokuFieldCellRemover
import com.example.sudoku.core.SudokuFieldGenerator

class SudokuField (difficulty: Difficulty) {
    private var gridSolved = Array(9) { IntArray(9) {0} }
    private var gridToSolve = Array(9) { IntArray(9) {0} }

    init {
        gridSolved = SudokuFieldGenerator.generate()
        gridToSolve = SudokuFieldCellRemover.remove(gridSolved, difficulty)
    }

    fun trySetValueAt(row : Int, col : Int, number : Int) : SetState {
        if (gridSolved[row][col] != number) return SetState.WRONG
        else if (gridToSolve[row][col] == number) return SetState.ALREADY_FILLED
        else {
            gridToSolve[row][col] = number
            return SetState.SET
        }
    }

    fun getValueAt(row : Int, col : Int) = gridSolved[row][col]

}
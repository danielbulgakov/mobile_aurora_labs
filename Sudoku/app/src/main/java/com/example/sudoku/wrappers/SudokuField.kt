package com.example.sudoku.wrappers

import com.example.sudoku.core.Difficulty
import com.example.sudoku.core.SudokuFieldCellRemover
import com.example.sudoku.core.SudokuFieldGenerator

class SudokuField (difficulty: Difficulty) {
    private var gridSolved = Array(9) { IntArray(9) {0} }
    private var gridToSolve = Array(9) { IntArray(9) {0} }
    private var gridStart = Array(9) { IntArray(9) {0} }

    private var focRow : Int = -1
    private var focCol: Int = - 1;

    private var gameEndListener: GameEndListener? = null

    init {
        gridSolved = SudokuFieldGenerator.generate()
        gridToSolve = SudokuFieldCellRemover.remove(gridSolved, difficulty)
        gridStart = Array(gridToSolve.size) { row ->
            kotlin.IntArray(gridToSolve[row].size) { col ->
                gridToSolve[row][col]
            }
        }
    }

    fun setFocusedCell(row : Int, col : Int) {
        focRow = row
        focCol = col
    }

    fun getFocusedCell() : Pair<Int, Int> {
        return Pair(focRow, focCol)
    }

    fun trySetAtFocusedCell(number: Int) {
        trySetValueAt(this.focRow, this.focCol, number)
    }

    fun trySetValueAt(row : Int, col : Int, number : Int) {
        if (gridStart[row][col] != 0) return
        if (row !in (0..8) || col !in (0..8)) return
        gridToSolve[row][col] = -number
        if (gridSolved[row][col] != number) return
        else if (gridToSolve[row][col] == number) return
        else {
            gridToSolve[row][col] = number
        }
        checkIfGameEnds()
    }

    private fun checkIfGameEnds() {
        gridToSolve.forEach { it -> it.forEach {
            if (it <= 0) return
        }
        }
        gameEndListener?.onGameEnd()
    }

    fun getValueAt(row : Int, col : Int) = gridToSolve[row][col]
    fun getCellState(row : Int, col : Int) : SetState {
        if (gridStart[row][col] != 0) return SetState.STARTING
        return SetState.SET
    }

    fun registerGameEndListener(listener: GameEndListener) {
        gameEndListener = listener
    }

}
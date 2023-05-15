package com.example.sudoku.core

import kotlin.random.Random

class SudokuFieldCellRemover {
    companion object {

        private fun randInt(min: Int, max: Int) = Random.nextInt(min, max + 1)

        fun remove(sudokuGrid : Array<IntArray>, diff : Difficulty) :
            Array<IntArray>
        {
            var grid = sudokuGrid.clone()
            var digitsToRemove = 9 * 9 - diff.numberOfGivenCells

            while (digitsToRemove > 0) {
                val randRow = randInt(0, 8)
                val randCol = randInt(0, 8)

                if (grid[randRow][randCol] != 0) {
                    val digitToRemove = grid[randRow][randCol]
                    grid[randRow][randCol] = 0
                    if (!SudokuSolver.isSolvable(grid)) {
                        grid[randRow][randCol] = digitToRemove
                    } else {
                        digitsToRemove --
                    }
                }
            }
            return grid
        }


    }
}
package com.example.sudoku.core

class App {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var grid = Array(9) {IntArray(9)}
            grid = SudokuFieldGenerator.generate()
            printGrid(grid)
            println()
            grid = SudokuFieldCellRemover.remove(grid, Difficulty.HARD)
            printGrid(grid)
        }

        fun printGrid(grid : Array<IntArray>) {
            for (i in 0 until 9) {
                for (j in 0 until 9) {
                    print(grid[i][j].toString().plus(" "))
                }
                println()
            }
            println()
        }
    }
}
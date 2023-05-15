package com.example.sudoku.core

class SudokuSolver {
    companion object {

        private lateinit var grid: Array<IntArray>

        fun isSolvable(grid: Array<IntArray>) : Boolean {
            this.grid = grid.copy()

            return solve()
        }

        private fun Array<IntArray>.copy() = Array(size) { get(it).clone() }

        private fun solve() : Boolean {
            for (i in 0 until 9) {
                for (j in 0 until 9) {
                    if (grid[i][j] == 0) {
                        val availableDigits = getDigitsToUse(i, j)
                        for (k in availableDigits) {
                            grid[i][j] = k
                            if (solve()) {
                                return true
                            }
                            grid[i][j] = 0
                        }
                        return false
                    }
                }
            }
            return true
        }

        private fun getDigitsToUse(row: Int, column: Int) : Iterable<Int> {
            val digitsRange = 1..9
            var availableDigits = mutableSetOf<Int>()
            availableDigits.addAll(digitsRange)

            removeSameInRow(availableDigits, row)
            if (availableDigits.size > 1) {
                removeSameInColumn(availableDigits, column)
            }
            if (availableDigits.size > 1) {
                removeSameInBox(availableDigits, row, column)
            }

            return availableDigits.asIterable()
        }

        private fun removeSameInRow(availableDigits: MutableSet<Int>, row: Int) {
            for (i in 0..8) {
                if (grid[row][i] != 0) {
                    availableDigits.remove(grid[row][i])
                }
            }
        }

        private fun removeSameInColumn(availableDigits: MutableSet<Int>, column: Int) {
            for (i in 0..8) {
                if (grid[i][column] != 0) {
                    availableDigits.remove(grid[i][column])
                }
            }
        }

        private fun removeSameInBox(availableDigits: MutableSet<Int>, row: Int, column: Int) {
            val rowStart = findBoxStart(row)
            val rowEnd = findBoxEnd(rowStart)
            val columnStart = findBoxStart(column)
            val columnEnd = findBoxEnd(columnStart)

            for (i in rowStart until rowEnd) {
                for (j in columnStart until columnEnd) {
                    if (grid[i][j] != 0) {
                        availableDigits.remove(grid[i][j])
                    }
                }
            }
        }

        private fun findBoxStart(index: Int) = index - index % 3

        private fun findBoxEnd(index: Int) = index + 2

    }
}
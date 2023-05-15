package com.example.sudoku.core

import kotlin.random.Random

class SudokuFieldGenerator {
    companion object {
        private val grid = Array(9) { IntArray(9) {0} }

        fun generate() : Array<IntArray> {
            fillDiagonalBoxes()
            fillRemaining(0, 3)
            return grid
        }

        private fun fillDiagonalBoxes() {
            for (i in 0 until 9 step 3) {
                fillBox(i, i)
            }
        }

        private fun fillBox(row: Int, column: Int) {
            var generatedDigit: Int

            for (i in 0 until 3) {
                for (j in 0 until 3) {
                    do {
                        generatedDigit = randInt(1, 9)
                    } while (!isNotInBox(row, column, generatedDigit))

                    grid[row + i][column + j] = generatedDigit
                }
            }
        }

        private fun randInt(min: Int, max: Int) = Random.nextInt(min, max + 1)

        private fun fillRemaining(i: Int, j: Int) : Boolean {
            var i = i
            var j = j

            if (j >= 9 && i < 9 - 1) {
                i += 1
                j = 0
            }
            if (i >= 9 && j >= 9) {
                return true
            }
            if (i < 3) {
                if (j < 3) {
                    j = 3
                }
            } else if (i < 9 - 3) {
                if (j == (i / 3) * 3) {
                    j += 3
                }
            } else {
                if (j == 9 - 3) {
                    i += 1
                    j = 0
                    if (i >= 9) {
                        return true
                    }
                }
            }

            for (digit in 1..9) {
                if (isValid(i, j, digit)) {
                    grid[i][j] = digit
                    if (fillRemaining(i, j + 1)) {
                        return true
                    }
                    grid[i][j] = 0
                }
            }
            return false
        }

        private fun isValid(row: Int, column: Int, digit: Int) =
            isNotInBox(findBoxStart(row), findBoxStart(column), digit)
                    && isNotInRow(row, digit)
                    && isNotInColumn(column, digit)

        private fun findBoxStart(index: Int) = index - index % 3

        private fun isNotInRow(row: Int, digit: Int) : Boolean {
            for (i in 0 until 9) {
                if (grid[row][i] == digit) {
                    return false
                }
            }
            return true
        }

        private fun isNotInColumn(column: Int, digit: Int) : Boolean {
            for (i in 0 until 9) {
                if (grid[i][column] == digit) {
                    return false
                }
            }
            return true
        }


        private fun isNotInBox(rowStart: Int, columnStart: Int, digit: Int) : Boolean {
            for (i in 0 until 3) {
                for (j in 0 until 3) {
                    if (grid[rowStart + i][columnStart + j] == digit) {
                        return false
                    }
                }
            }
            return true
        }

    }
}
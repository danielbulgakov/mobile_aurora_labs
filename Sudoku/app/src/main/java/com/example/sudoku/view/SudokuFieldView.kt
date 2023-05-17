package com.example.sudoku.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.sudoku.R
import com.example.sudoku.core.SudokuSolver
import com.example.sudoku.wrappers.SetState
import com.example.sudoku.wrappers.SudokuField
import com.example.sudoku.wrappers.SudokuFieldBuilder
import kotlinx.coroutines.*
import kotlin.math.ceil


internal class SudokuFieldView(context: Context, attrs: AttributeSet?) :
    View(context, attrs) {
    private var boardColor = 0
    private var cellFillColor = 0
    private var cellsHighlightColor = 0
    private var letterColor = 0
    private var letterColorUser = 0
    private var letterColorError = 0
    private val boardColorPaint = Paint()
    private val cellFillColorPaint = Paint()
    private val cellsHighlightColorPaint = Paint()
    private val letterPaint = Paint()
    private val letterPaintBounds: Rect = Rect()
    private var cellSize = 0
    private var sudokuField : SudokuField? = null

    init {
        CoroutineScope(Dispatchers.Main).launch {
            fetchData()
        }
        val a = context.theme.obtainStyledAttributes(
            attrs, R.styleable.SudokuFieldView,
            0, 0
        )
        try {
            boardColor = a.getInteger(R.styleable.SudokuFieldView_boardColor, 0)
            cellFillColor = a.getInteger(R.styleable.SudokuFieldView_cellFillColor, 0)
            cellsHighlightColor = a.getInteger(R.styleable.SudokuFieldView_cellsHighlightColor, 0)
            letterColor = a.getInteger(R.styleable.SudokuFieldView_letterColor, 0)
            letterColorUser = a.getInteger(R.styleable.SudokuFieldView_letterColorUser, 0)
            letterColorError = a.getInteger(R.styleable.SudokuFieldView_letterColorError, 0)
        } finally {
            a.recycle()
        }
    }

    private suspend fun fetchData() = coroutineScope {
        val result = async {
            sudokuField = SudokuFieldBuilder.build()
            invalidate()
        }
        result.await()
    }
    override fun onMeasure(width: Int, height: Int) {
        super.onMeasure(width, height)
        val dimension = Math.min(this.measuredWidth, this.measuredHeight)
        cellSize = dimension / 9
        setMeasuredDimension(dimension, dimension)
    }

    override fun onDraw(canvas: Canvas) {
        if (sudokuField == null) return

        boardColorPaint.style = Paint.Style.STROKE
        boardColorPaint.strokeWidth = 16f
        boardColorPaint.color = boardColor
        boardColorPaint.isAntiAlias = true
        cellFillColorPaint.style = Paint.Style.FILL
        boardColorPaint.isAntiAlias = true
        cellFillColorPaint.color = cellFillColor
        cellsHighlightColorPaint.style = Paint.Style.FILL
        boardColorPaint.isAntiAlias = true
        cellsHighlightColorPaint.color = cellsHighlightColor
        letterPaint.style = Paint.Style.FILL
        letterPaint.isAntiAlias = true
        letterPaint.color = letterColor
        colorCell(canvas)
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), boardColorPaint)
        drawBoard(canvas)
        drawNumbers(canvas)

    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val isValid: Boolean
        val x = event.x
        val y = event.y
        val action = event.action
        isValid = if (action == MotionEvent.ACTION_DOWN) {
            sudokuField!!.setFocusedCell(
                ceil((y / cellSize).toDouble()).toInt() - 1,
                ceil((x / cellSize).toDouble()).toInt() - 1
            )
            true
        } else {
            false
        }
        return isValid
    }

    private fun drawNumbers(canvas: Canvas) {
        letterPaint.textSize = cellSize.toFloat()
        for (r in 0..8) {
            for (c in 0..8) {
                if (sudokuField!!.getValueAt(r, c) !== 0) {
                    var text: String
                    if (sudokuField!!.getValueAt(r, c) < 0) {
                        text = Integer.toString(sudokuField!!.getValueAt(r, c) * -1)
                        letterPaint.color = letterColorError
                    } else if (sudokuField!!.getCellState(r, c) == SetState.STARTING) {
                        letterPaint.color = letterColor
                        text = Integer.toString(sudokuField!!.getValueAt(r, c))
                    } else if (sudokuField!!.getCellState(r, c) == SetState.SET){
                        letterPaint.color = letterColorUser
                        text = Integer.toString(sudokuField!!.getValueAt(r, c))
                    } else { text = "Nan" }
                    var width: Float
                    var height: Float
                    letterPaint.getTextBounds(text, 0, text.length, letterPaintBounds)
                    width = letterPaint.measureText(text)
                    height = letterPaintBounds.height().toFloat()
                    canvas.drawText(
                        text, c * cellSize + (cellSize - width) / 2,
                        r * cellSize + cellSize - (cellSize - height) / 2,
                        letterPaint
                    )
                }
            }
        }
//        for (letter in solver.getEmptyBoxIndex()) {
//            val r = letter[0] as Int
//            val c = letter[1] as Int
//            val text = Integer.toString(sudokuField!!.getValueAt(r, c))
//            var width: Float
//            var height: Float
//            letterPaint.getTextBounds(text, 0, text.length, letterPaintBounds)
//            width = letterPaint.measureText(text)
//            height = letterPaintBounds.height().toFloat()
//            canvas.drawText(
//                text, c * cellSize + (cellSize - width) / 2,
//                r * cellSize + cellSize - (cellSize - height) / 2,
//                letterPaint
//            )
//        }
    }

    private fun colorCell(canvas: Canvas) {
        if (sudokuField!!.getFocusedCell().first != -1 &&
                sudokuField!!.getFocusedCell().second != -1) {

            val r = sudokuField!!.getFocusedCell().first + 1
            val c = sudokuField!!.getFocusedCell().second + 1
            canvas.drawRect(
                ((c - 1) * cellSize).toFloat(),
                0f,
                (c * cellSize).toFloat(),
                (cellSize * 9).toFloat(),
                cellsHighlightColorPaint
            )
            canvas.drawRect(
                0f,
                ((r - 1) * cellSize).toFloat(),
                (cellSize * 9).toFloat(),
                (r * cellSize).toFloat(),
                cellsHighlightColorPaint
            )
            canvas.drawRect(
                ((c - 1) * cellSize).toFloat(),
                ((r - 1) * cellSize).toFloat(),
                (c * cellSize).toFloat(),
                (r * cellSize).toFloat(),
                cellsHighlightColorPaint
            )
        }
        invalidate()
    }

    private fun drawThickLine() {
        boardColorPaint.style = Paint.Style.STROKE
        boardColorPaint.strokeWidth = 10f
        boardColorPaint.color = boardColor
    }

    private fun drawThinLine() {
        boardColorPaint.style = Paint.Style.STROKE
        boardColorPaint.strokeWidth = 4f
        boardColorPaint.color = boardColor
    }

    private fun drawBoard(canvas: Canvas) {
        for (c in 0..9) {
            if (c % 3 == 0) {
                drawThickLine()
            } else {
                drawThinLine()
            }
            canvas.drawLine(
                (cellSize * c).toFloat(), 0f,
                (
                        cellSize * c).toFloat(), width.toFloat(), boardColorPaint
            )
        }
        for (r in 0..9) {
            if (r % 3 == 0) {
                drawThickLine()
            } else {
                drawThinLine()
            }
            canvas.drawLine(
                0f, (cellSize * r).toFloat(),
                width.toFloat(), (cellSize * r).toFloat(), boardColorPaint
            )
        }
    }

}
package com.example.lab8

class CounterClass (startValue : Int = 0) {
    private var value = 0

    init {
        value = startValue
    }

    fun getValue() = value

    fun incrementValue() { value++ }

    fun resetValue() { value = 0 }
}
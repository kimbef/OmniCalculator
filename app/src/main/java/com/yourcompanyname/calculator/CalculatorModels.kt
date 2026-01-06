package com.yourcompanyname.calculator

data class CalculationHistoryItem(
    val expression: String,
    val result: String,
    val timestamp: Long = System.currentTimeMillis()
)

enum class CalculatorMode {
    BASIC,
    SCIENTIFIC
}

enum class NumberBase {
    DECIMAL,
    BINARY,
    HEXADECIMAL,
    OCTAL
}

sealed class CalculatorOperation {
    object Add : CalculatorOperation()
    object Subtract : CalculatorOperation()
    object Multiply : CalculatorOperation()
    object Divide : CalculatorOperation()
    object Power : CalculatorOperation()
    object Percent : CalculatorOperation()
    object None : CalculatorOperation()
}

sealed class ScientificFunction {
    object Sin : ScientificFunction()
    object Cos : ScientificFunction()
    object Tan : ScientificFunction()
    object Log : ScientificFunction()
    object Ln : ScientificFunction()
    object SquareRoot : ScientificFunction()
}

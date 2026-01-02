package com.yourcompanyname.calculator

import kotlin.math.*

class CalculatorEngine {
    private var currentValue: Double = 0.0
    private var previousValue: Double = 0.0
    private var currentOperation: CalculatorOperation = CalculatorOperation.None
    private var memory: Double = 0.0
    private var shouldResetDisplay: Boolean = false
    
    fun inputNumber(value: String, currentDisplay: String): String {
        return if (shouldResetDisplay) {
            shouldResetDisplay = false
            value
        } else {
            if (currentDisplay == "0") value else currentDisplay + value
        }
    }
    
    fun inputDecimal(currentDisplay: String): String {
        return if (shouldResetDisplay) {
            shouldResetDisplay = false
            "0."
        } else {
            if (!currentDisplay.contains(".")) currentDisplay + "." else currentDisplay
        }
    }
    
    fun setOperation(operation: CalculatorOperation, currentDisplay: String): String {
        previousValue = currentDisplay.toDoubleOrNull() ?: 0.0
        currentOperation = operation
        shouldResetDisplay = true
        return currentDisplay
    }
    
    fun calculate(currentDisplay: String): String {
        currentValue = currentDisplay.toDoubleOrNull() ?: 0.0
        
        val result = when (currentOperation) {
            is CalculatorOperation.Add -> previousValue + currentValue
            is CalculatorOperation.Subtract -> previousValue - currentValue
            is CalculatorOperation.Multiply -> previousValue * currentValue
            is CalculatorOperation.Divide -> {
                if (currentValue != 0.0) previousValue / currentValue
                else Double.NaN
            }
            is CalculatorOperation.Power -> previousValue.pow(currentValue)
            is CalculatorOperation.Percent -> previousValue * (currentValue / 100.0)
            is CalculatorOperation.None -> currentValue
        }
        
        currentOperation = CalculatorOperation.None
        shouldResetDisplay = true
        
        return if (result.isNaN() || result.isInfinite()) {
            "Error"
        } else {
            formatResult(result)
        }
    }
    
    fun calculateScientific(function: ScientificFunction, value: Double): Double {
        return when (function) {
            is ScientificFunction.Sin -> sin(Math.toRadians(value))
            is ScientificFunction.Cos -> cos(Math.toRadians(value))
            is ScientificFunction.Tan -> tan(Math.toRadians(value))
            is ScientificFunction.Log -> log10(value)
            is ScientificFunction.Ln -> ln(value)
            is ScientificFunction.SquareRoot -> sqrt(value)
        }
    }
    
    fun convertBase(value: String, fromBase: NumberBase, toBase: NumberBase): String {
        try {
            val decimalValue = when (fromBase) {
                NumberBase.DECIMAL -> value.toLongOrNull() ?: return "Error"
                NumberBase.BINARY -> value.toLongOrNull(2) ?: return "Error"
                NumberBase.HEXADECIMAL -> value.toLongOrNull(16) ?: return "Error"
                NumberBase.OCTAL -> value.toLongOrNull(8) ?: return "Error"
            }
            
            return when (toBase) {
                NumberBase.DECIMAL -> decimalValue.toString()
                NumberBase.BINARY -> decimalValue.toString(2)
                NumberBase.HEXADECIMAL -> decimalValue.toString(16).uppercase()
                NumberBase.OCTAL -> decimalValue.toString(8)
            }
        } catch (e: Exception) {
            return "Error"
        }
    }
    
    fun clear() {
        currentValue = 0.0
        previousValue = 0.0
        currentOperation = CalculatorOperation.None
        shouldResetDisplay = false
    }
    
    fun memoryAdd(value: Double) {
        memory += value
    }
    
    fun memorySubtract(value: Double) {
        memory -= value
    }
    
    fun memoryRecall(): Double {
        return memory
    }
    
    fun memoryClear() {
        memory = 0.0
    }
    
    private fun formatResult(value: Double): String {
        return if (value % 1.0 == 0.0) {
            value.toLong().toString()
        } else {
            String.format("%.8f", value).trimEnd('0').trimEnd('.')
        }
    }
}

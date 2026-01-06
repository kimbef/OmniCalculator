package com.yourcompanyname.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CalculatorViewModel : ViewModel() {
    private val engine = CalculatorEngine()
    
    var displayText by mutableStateOf("0")
        private set
    
    var calculatorMode by mutableStateOf(CalculatorMode.BASIC)
        private set
    
    var currentBase by mutableStateOf(NumberBase.DECIMAL)
        private set
    
    private val _history = MutableStateFlow<List<CalculationHistoryItem>>(emptyList())
    val history: StateFlow<List<CalculationHistoryItem>> = _history
    
    private var currentExpression = ""
    
    fun onNumberClick(number: String) {
        displayText = engine.inputNumber(number, displayText)
        currentExpression += number
    }
    
    fun onDecimalClick() {
        displayText = engine.inputDecimal(displayText)
        currentExpression += "."
    }
    
    fun onOperationClick(operation: CalculatorOperation) {
        displayText = engine.setOperation(operation, displayText)
        currentExpression += " ${getOperationSymbol(operation)} "
    }
    
    fun onEqualsClick() {
        val result = engine.calculate(displayText)
        addToHistory(currentExpression, result)
        displayText = result
        currentExpression = result
    }
    
    fun onClearClick() {
        engine.clear()
        displayText = "0"
        currentExpression = ""
    }
    
    fun onScientificFunctionClick(function: ScientificFunction) {
        val value = displayText.toDoubleOrNull() ?: 0.0
        val result = engine.calculateScientific(function, value)
        displayText = if (result.isNaN() || result.isInfinite()) {
            "Error"
        } else {
            formatResult(result)
        }
        currentExpression = "${getFunctionName(function)}($value)"
        addToHistory(currentExpression, displayText)
    }
    
    fun onMemoryAdd() {
        val value = displayText.toDoubleOrNull() ?: 0.0
        engine.memoryAdd(value)
    }
    
    fun onMemorySubtract() {
        val value = displayText.toDoubleOrNull() ?: 0.0
        engine.memorySubtract(value)
    }
    
    fun onMemoryRecall() {
        displayText = formatResult(engine.memoryRecall())
    }
    
    fun onMemoryClear() {
        engine.memoryClear()
    }
    
    fun switchMode(mode: CalculatorMode) {
        calculatorMode = mode
    }
    
    fun switchBase(base: NumberBase) {
        val converted = engine.convertBase(displayText, currentBase, base)
        displayText = converted
        currentBase = base
    }
    
    fun clearHistory() {
        viewModelScope.launch {
            _history.value = emptyList()
        }
    }
    
    fun loadHistoryItem(item: CalculationHistoryItem) {
        displayText = item.result
        currentExpression = item.result
    }
    
    private fun addToHistory(expression: String, result: String) {
        if (expression.isNotEmpty() && result != "Error") {
            viewModelScope.launch {
                val newHistory = _history.value.toMutableList()
                newHistory.add(0, CalculationHistoryItem(expression, result))
                if (newHistory.size > 50) {
                    newHistory.removeLast()
                }
                _history.value = newHistory
            }
        }
    }
    
    private fun getOperationSymbol(operation: CalculatorOperation): String {
        return when (operation) {
            is CalculatorOperation.Add -> "+"
            is CalculatorOperation.Subtract -> "-"
            is CalculatorOperation.Multiply -> "×"
            is CalculatorOperation.Divide -> "÷"
            is CalculatorOperation.Power -> "^"
            is CalculatorOperation.Percent -> "%"
            is CalculatorOperation.None -> ""
        }
    }
    
    private fun getFunctionName(function: ScientificFunction): String {
        return when (function) {
            is ScientificFunction.Sin -> "sin"
            is ScientificFunction.Cos -> "cos"
            is ScientificFunction.Tan -> "tan"
            is ScientificFunction.Log -> "log"
            is ScientificFunction.Ln -> "ln"
            is ScientificFunction.SquareRoot -> "√"
        }
    }
    
    private fun formatResult(value: Double): String {
        return if (value % 1.0 == 0.0) {
            value.toLong().toString()
        } else {
            String.format("%.8f", value).trimEnd('0').trimEnd('.')
        }
    }
}

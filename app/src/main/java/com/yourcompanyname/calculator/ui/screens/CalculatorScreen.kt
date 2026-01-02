package com.yourcompanyname.calculator.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yourcompanyname.calculator.CalculatorOperation
import com.yourcompanyname.calculator.ScientificFunction
import com.yourcompanyname.calculator.ui.theme.*

@Composable
fun CalculatorButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color? = null,
    textColor: Color = MaterialTheme.colorScheme.onSurface
) {
    val isDark = isSystemInDarkTheme()
    val defaultColor = if (isDark) NumberButtonDark else NumberButtonLight
    
    Button(
        onClick = onClick,
        modifier = modifier
            .aspectRatio(1f)
            .padding(4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor ?: defaultColor,
            contentColor = textColor
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text = text,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun BasicCalculatorLayout(
    displayText: String,
    onNumberClick: (String) -> Unit,
    onOperationClick: (CalculatorOperation) -> Unit,
    onEqualsClick: () -> Unit,
    onClearClick: () -> Unit,
    onDecimalClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isDark = isSystemInDarkTheme()
    val operatorColor = if (isDark) OperatorButtonDark else OperatorButtonLight
    val equalsColor = if (isDark) EqualsButtonDark else EqualsButtonLight
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        // Display
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.3f)
                .padding(vertical = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text = displayText,
                    fontSize = 48.sp,
                    textAlign = TextAlign.End,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2
                )
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Buttons
        Column(
            modifier = Modifier.weight(0.7f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Row 1: AC and operators
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CalculatorButton(
                    text = "AC",
                    onClick = onClearClick,
                    modifier = Modifier.weight(2f),
                    backgroundColor = Color(0xFFD32F2F),
                    textColor = Color.White
                )
                CalculatorButton(
                    text = "÷",
                    onClick = { onOperationClick(CalculatorOperation.Divide) },
                    modifier = Modifier.weight(1f),
                    backgroundColor = operatorColor,
                    textColor = Color.White
                )
                CalculatorButton(
                    text = "×",
                    onClick = { onOperationClick(CalculatorOperation.Multiply) },
                    modifier = Modifier.weight(1f),
                    backgroundColor = operatorColor,
                    textColor = Color.White
                )
            }
            
            // Row 2: 7, 8, 9, -
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CalculatorButton(text = "7", onClick = { onNumberClick("7") }, modifier = Modifier.weight(1f))
                CalculatorButton(text = "8", onClick = { onNumberClick("8") }, modifier = Modifier.weight(1f))
                CalculatorButton(text = "9", onClick = { onNumberClick("9") }, modifier = Modifier.weight(1f))
                CalculatorButton(
                    text = "-",
                    onClick = { onOperationClick(CalculatorOperation.Subtract) },
                    modifier = Modifier.weight(1f),
                    backgroundColor = operatorColor,
                    textColor = Color.White
                )
            }
            
            // Row 3: 4, 5, 6, +
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CalculatorButton(text = "4", onClick = { onNumberClick("4") }, modifier = Modifier.weight(1f))
                CalculatorButton(text = "5", onClick = { onNumberClick("5") }, modifier = Modifier.weight(1f))
                CalculatorButton(text = "6", onClick = { onNumberClick("6") }, modifier = Modifier.weight(1f))
                CalculatorButton(
                    text = "+",
                    onClick = { onOperationClick(CalculatorOperation.Add) },
                    modifier = Modifier.weight(1f),
                    backgroundColor = operatorColor,
                    textColor = Color.White
                )
            }
            
            // Row 4: 1, 2, 3
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CalculatorButton(text = "1", onClick = { onNumberClick("1") }, modifier = Modifier.weight(1f))
                CalculatorButton(text = "2", onClick = { onNumberClick("2") }, modifier = Modifier.weight(1f))
                CalculatorButton(text = "3", onClick = { onNumberClick("3") }, modifier = Modifier.weight(1f))
                CalculatorButton(
                    text = "=",
                    onClick = onEqualsClick,
                    modifier = Modifier.weight(1f),
                    backgroundColor = equalsColor,
                    textColor = Color.White
                )
            }
            
            // Row 5: 0, .
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CalculatorButton(text = "0", onClick = { onNumberClick("0") }, modifier = Modifier.weight(2f))
                CalculatorButton(text = ".", onClick = onDecimalClick, modifier = Modifier.weight(1f))
                CalculatorButton(
                    text = "%",
                    onClick = { onOperationClick(CalculatorOperation.Percent) },
                    modifier = Modifier.weight(1f),
                    backgroundColor = operatorColor,
                    textColor = Color.White
                )
            }
        }
    }
}

@Composable
fun ScientificCalculatorLayout(
    displayText: String,
    onNumberClick: (String) -> Unit,
    onOperationClick: (CalculatorOperation) -> Unit,
    onEqualsClick: () -> Unit,
    onClearClick: () -> Unit,
    onDecimalClick: () -> Unit,
    onScientificFunctionClick: (ScientificFunction) -> Unit,
    onMemoryAdd: () -> Unit,
    onMemorySubtract: () -> Unit,
    onMemoryRecall: () -> Unit,
    onMemoryClear: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isDark = isSystemInDarkTheme()
    val operatorColor = if (isDark) OperatorButtonDark else OperatorButtonLight
    val equalsColor = if (isDark) EqualsButtonDark else EqualsButtonLight
    val functionColor = Color(0xFF2196F3)
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)
    ) {
        // Display
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.2f)
                .padding(vertical = 4.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text = displayText,
                    fontSize = 36.sp,
                    textAlign = TextAlign.End,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2
                )
            }
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // Memory buttons row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Button(
                onClick = onMemoryAdd,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = functionColor)
            ) {
                Text("M+", fontSize = 14.sp)
            }
            Button(
                onClick = onMemorySubtract,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = functionColor)
            ) {
                Text("M-", fontSize = 14.sp)
            }
            Button(
                onClick = onMemoryRecall,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = functionColor)
            ) {
                Text("MR", fontSize = 14.sp)
            }
            Button(
                onClick = onMemoryClear,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = functionColor)
            ) {
                Text("MC", fontSize = 14.sp)
            }
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // Scientific functions row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Button(
                onClick = { onScientificFunctionClick(ScientificFunction.Sin) },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = functionColor)
            ) {
                Text("sin", fontSize = 14.sp)
            }
            Button(
                onClick = { onScientificFunctionClick(ScientificFunction.Cos) },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = functionColor)
            ) {
                Text("cos", fontSize = 14.sp)
            }
            Button(
                onClick = { onScientificFunctionClick(ScientificFunction.Tan) },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = functionColor)
            ) {
                Text("tan", fontSize = 14.sp)
            }
            Button(
                onClick = { onScientificFunctionClick(ScientificFunction.Log) },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = functionColor)
            ) {
                Text("log", fontSize = 14.sp)
            }
            Button(
                onClick = { onScientificFunctionClick(ScientificFunction.Ln) },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = functionColor)
            ) {
                Text("ln", fontSize = 14.sp)
            }
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // More functions row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Button(
                onClick = { onScientificFunctionClick(ScientificFunction.SquareRoot) },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = functionColor)
            ) {
                Text("√", fontSize = 18.sp)
            }
            Button(
                onClick = { onOperationClick(CalculatorOperation.Power) },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = functionColor)
            ) {
                Text("x^y", fontSize = 14.sp)
            }
            Button(
                onClick = onClearClick,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F))
            ) {
                Text("AC", fontSize = 14.sp)
            }
            Button(
                onClick = { onOperationClick(CalculatorOperation.Divide) },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = operatorColor)
            ) {
                Text("÷", fontSize = 18.sp)
            }
            Button(
                onClick = { onOperationClick(CalculatorOperation.Multiply) },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = operatorColor)
            ) {
                Text("×", fontSize = 18.sp)
            }
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // Number pad
        Column(
            modifier = Modifier.weight(0.6f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // Row: 7, 8, 9, -
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                CalculatorButton(text = "7", onClick = { onNumberClick("7") }, modifier = Modifier.weight(1f))
                CalculatorButton(text = "8", onClick = { onNumberClick("8") }, modifier = Modifier.weight(1f))
                CalculatorButton(text = "9", onClick = { onNumberClick("9") }, modifier = Modifier.weight(1f))
                CalculatorButton(
                    text = "-",
                    onClick = { onOperationClick(CalculatorOperation.Subtract) },
                    modifier = Modifier.weight(1f),
                    backgroundColor = operatorColor,
                    textColor = Color.White
                )
            }
            
            // Row: 4, 5, 6, +
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                CalculatorButton(text = "4", onClick = { onNumberClick("4") }, modifier = Modifier.weight(1f))
                CalculatorButton(text = "5", onClick = { onNumberClick("5") }, modifier = Modifier.weight(1f))
                CalculatorButton(text = "6", onClick = { onNumberClick("6") }, modifier = Modifier.weight(1f))
                CalculatorButton(
                    text = "+",
                    onClick = { onOperationClick(CalculatorOperation.Add) },
                    modifier = Modifier.weight(1f),
                    backgroundColor = operatorColor,
                    textColor = Color.White
                )
            }
            
            // Row: 1, 2, 3, =
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                CalculatorButton(text = "1", onClick = { onNumberClick("1") }, modifier = Modifier.weight(1f))
                CalculatorButton(text = "2", onClick = { onNumberClick("2") }, modifier = Modifier.weight(1f))
                CalculatorButton(text = "3", onClick = { onNumberClick("3") }, modifier = Modifier.weight(1f))
                CalculatorButton(
                    text = "=",
                    onClick = onEqualsClick,
                    modifier = Modifier.weight(1f),
                    backgroundColor = equalsColor,
                    textColor = Color.White
                )
            }
            
            // Row: 0, ., %
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                CalculatorButton(text = "0", onClick = { onNumberClick("0") }, modifier = Modifier.weight(2f))
                CalculatorButton(text = ".", onClick = onDecimalClick, modifier = Modifier.weight(1f))
                CalculatorButton(
                    text = "%",
                    onClick = { onOperationClick(CalculatorOperation.Percent) },
                    modifier = Modifier.weight(1f),
                    backgroundColor = operatorColor,
                    textColor = Color.White
                )
            }
        }
    }
}

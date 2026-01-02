package com.yourcompanyname.calculator.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yourcompanyname.calculator.NumberBase

@Composable
fun BaseConversionScreen(
    currentValue: String,
    currentBase: NumberBase,
    onBaseChange: (NumberBase) -> Unit,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Base Conversion",
            style = MaterialTheme.typography.headlineMedium
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Decimal
        BaseConversionCard(
            label = "Decimal (DEC)",
            value = if (currentBase == NumberBase.DECIMAL) currentValue else "Convert to see",
            isSelected = currentBase == NumberBase.DECIMAL,
            onClick = { onBaseChange(NumberBase.DECIMAL) }
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        // Binary
        BaseConversionCard(
            label = "Binary (BIN)",
            value = if (currentBase == NumberBase.BINARY) currentValue else "Convert to see",
            isSelected = currentBase == NumberBase.BINARY,
            onClick = { onBaseChange(NumberBase.BINARY) }
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        // Hexadecimal
        BaseConversionCard(
            label = "Hexadecimal (HEX)",
            value = if (currentBase == NumberBase.HEXADECIMAL) currentValue else "Convert to see",
            isSelected = currentBase == NumberBase.HEXADECIMAL,
            onClick = { onBaseChange(NumberBase.HEXADECIMAL) }
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        // Octal
        BaseConversionCard(
            label = "Octal (OCT)",
            value = if (currentBase == NumberBase.OCTAL) currentValue else "Convert to see",
            isSelected = currentBase == NumberBase.OCTAL,
            onClick = { onBaseChange(NumberBase.OCTAL) }
        )
    }
}

@Composable
fun BaseConversionCard(
    label: String,
    value: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) {
                MaterialTheme.colorScheme.primaryContainer
            } else {
                MaterialTheme.colorScheme.surface
            }
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium,
                color = if (isSelected) {
                    MaterialTheme.colorScheme.onPrimaryContainer
                } else {
                    MaterialTheme.colorScheme.onSurface
                }
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = value,
                style = MaterialTheme.typography.headlineSmall,
                color = if (isSelected) {
                    MaterialTheme.colorScheme.onPrimaryContainer
                } else {
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                }
            )
        }
    }
}

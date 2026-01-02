package com.yourcompanyname.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.yourcompanyname.calculator.ui.screens.BasicCalculatorLayout
import com.yourcompanyname.calculator.ui.theme.OmniCalculatorTheme

class FloatingCalculatorActivity : ComponentActivity() {
    private val viewModel: CalculatorViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OmniCalculatorTheme {
                BasicCalculatorLayout(
                    displayText = viewModel.displayText,
                    onNumberClick = viewModel::onNumberClick,
                    onOperationClick = viewModel::onOperationClick,
                    onEqualsClick = viewModel::onEqualsClick,
                    onClearClick = viewModel::onClearClick,
                    onDecimalClick = viewModel::onDecimalClick
                )
            }
        }
    }
}

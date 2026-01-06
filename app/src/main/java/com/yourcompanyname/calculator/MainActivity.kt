package com.yourcompanyname.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.yourcompanyname.calculator.ui.screens.*
import com.yourcompanyname.calculator.ui.theme.OmniCalculatorTheme
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val viewModel: CalculatorViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            val scope = rememberCoroutineScope()
            
            val darkThemeKey = booleanPreferencesKey("dark_theme")
            val isDarkTheme by remember {
                (context.applicationContext as CalculatorApplication).dataStore.data
                    .map { preferences -> preferences[darkThemeKey] ?: false }
            }.collectAsState(initial = false)
            
            OmniCalculatorTheme(darkTheme = isDarkTheme) {
                CalculatorApp(
                    viewModel = viewModel,
                    isDarkTheme = isDarkTheme,
                    onThemeChange = { newTheme ->
                        scope.launch {
                            (context.applicationContext as CalculatorApplication).dataStore.edit { preferences ->
                                preferences[darkThemeKey] = newTheme
                            }
                        }
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorApp(
    viewModel: CalculatorViewModel,
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit
) {
    var selectedScreen by remember { mutableStateOf(Screen.Calculator) }
    val history by viewModel.history.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("OmniCalculator") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Calculate, contentDescription = "Calculator") },
                    label = { Text("Calculator") },
                    selected = selectedScreen == Screen.Calculator,
                    onClick = { selectedScreen = Screen.Calculator }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.History, contentDescription = "History") },
                    label = { Text("History") },
                    selected = selectedScreen == Screen.History,
                    onClick = { selectedScreen = Screen.History }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Transform, contentDescription = "Base") },
                    label = { Text("Base") },
                    selected = selectedScreen == Screen.BaseConversion,
                    onClick = { selectedScreen = Screen.BaseConversion }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
                    label = { Text("Settings") },
                    selected = selectedScreen == Screen.Settings,
                    onClick = { selectedScreen = Screen.Settings }
                )
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (selectedScreen) {
                Screen.Calculator -> {
                    Column {
                        // Mode toggle
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            FilterChip(
                                selected = viewModel.calculatorMode == CalculatorMode.BASIC,
                                onClick = { viewModel.switchMode(CalculatorMode.BASIC) },
                                label = { Text("Basic") },
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            FilterChip(
                                selected = viewModel.calculatorMode == CalculatorMode.SCIENTIFIC,
                                onClick = { viewModel.switchMode(CalculatorMode.SCIENTIFIC) },
                                label = { Text("Scientific") }
                            )
                        }
                        
                        // Calculator layout
                        when (viewModel.calculatorMode) {
                            CalculatorMode.BASIC -> {
                                BasicCalculatorLayout(
                                    displayText = viewModel.displayText,
                                    onNumberClick = viewModel::onNumberClick,
                                    onOperationClick = viewModel::onOperationClick,
                                    onEqualsClick = viewModel::onEqualsClick,
                                    onClearClick = viewModel::onClearClick,
                                    onDecimalClick = viewModel::onDecimalClick
                                )
                            }
                            CalculatorMode.SCIENTIFIC -> {
                                ScientificCalculatorLayout(
                                    displayText = viewModel.displayText,
                                    onNumberClick = viewModel::onNumberClick,
                                    onOperationClick = viewModel::onOperationClick,
                                    onEqualsClick = viewModel::onEqualsClick,
                                    onClearClick = viewModel::onClearClick,
                                    onDecimalClick = viewModel::onDecimalClick,
                                    onScientificFunctionClick = viewModel::onScientificFunctionClick,
                                    onMemoryAdd = viewModel::onMemoryAdd,
                                    onMemorySubtract = viewModel::onMemorySubtract,
                                    onMemoryRecall = viewModel::onMemoryRecall,
                                    onMemoryClear = viewModel::onMemoryClear
                                )
                            }
                        }
                    }
                }
                Screen.History -> {
                    HistoryScreen(
                        history = history,
                        onHistoryItemClick = { item ->
                            viewModel.loadHistoryItem(item)
                            selectedScreen = Screen.Calculator
                        },
                        onClearHistory = viewModel::clearHistory
                    )
                }
                Screen.BaseConversion -> {
                    BaseConversionScreen(
                        currentValue = viewModel.displayText,
                        currentBase = viewModel.currentBase,
                        onBaseChange = viewModel::switchBase,
                        onValueChange = { /* Not needed for now */ }
                    )
                }
                Screen.Settings -> {
                    SettingsScreen(
                        isDarkTheme = isDarkTheme,
                        onThemeChange = onThemeChange
                    )
                }
            }
        }
    }
}

enum class Screen {
    Calculator,
    History,
    BaseConversion,
    Settings
}

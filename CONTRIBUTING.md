# Contributing to OmniCalculator

Thank you for your interest in contributing to OmniCalculator! This document provides guidelines and information for contributors.

## Table of Contents

1. [Code of Conduct](#code-of-conduct)
2. [Getting Started](#getting-started)
3. [Development Setup](#development-setup)
4. [Project Architecture](#project-architecture)
5. [Coding Standards](#coding-standards)
6. [Making Changes](#making-changes)
7. [Testing](#testing)
8. [Submitting Changes](#submitting-changes)
9. [Feature Requests](#feature-requests)

---

## Code of Conduct

### Our Pledge

We are committed to providing a welcoming and inclusive environment for all contributors, regardless of:
- Experience level
- Background
- Identity
- Geographic location

### Expected Behavior

- Be respectful and considerate
- Provide constructive feedback
- Focus on what is best for the community
- Show empathy towards other contributors

### Unacceptable Behavior

- Harassment or discriminatory language
- Trolling or inflammatory comments
- Publishing others' private information
- Any conduct that could be considered inappropriate in a professional setting

---

## Getting Started

### Prerequisites

Before you begin, ensure you have:
- Android Studio Electric Eel (2022.1.1) or newer
- JDK 8 or higher
- Git installed and configured
- A GitHub account
- Basic knowledge of Kotlin and Android development

### Understanding the Codebase

1. Read the [IMPLEMENTATION.md](IMPLEMENTATION.md) for architecture overview
2. Read the [README.md](README.md) for feature descriptions
3. Browse through the code to familiarize yourself with the structure

---

## Development Setup

### 1. Fork and Clone

```bash
# Fork the repository on GitHub first, then:
git clone https://github.com/YOUR_USERNAME/OmniCalculator.git
cd OmniCalculator
```

### 2. Set Up Remote

```bash
# Add the original repository as upstream
git remote add upstream https://github.com/kimbef/OmniCalculator.git

# Verify remotes
git remote -v
```

### 3. Open in Android Studio

1. Launch Android Studio
2. Click "Open an Existing Project"
3. Navigate to the cloned directory
4. Wait for Gradle sync to complete

### 4. Build and Run

```bash
# Build the project
./gradlew build

# Install on connected device
./gradlew installDebug
```

---

## Project Architecture

### MVVM Pattern

OmniCalculator follows the Model-View-ViewModel architecture:

```
┌─────────────────────────────────────────┐
│              View (UI)                  │
│     Jetpack Compose Screens             │
└─────────────────┬───────────────────────┘
                  │ observes state
                  ▼
┌─────────────────────────────────────────┐
│           ViewModel                     │
│    (CalculatorViewModel)                │
│   - Manages UI state                    │
│   - Handles user actions                │
└─────────────────┬───────────────────────┘
                  │ uses
                  ▼
┌─────────────────────────────────────────┐
│            Model                        │
│    (CalculatorEngine)                   │
│   - Business logic                      │
│   - Calculations                        │
└─────────────────────────────────────────┘
```

### Key Components

1. **UI Layer** (`ui/screens/`)
   - Composable functions for each screen
   - No business logic
   - Observes ViewModel state

2. **ViewModel Layer**
   - `CalculatorViewModel`: Single source of truth for UI state
   - Handles user interactions
   - Manages history and preferences

3. **Model Layer**
   - `CalculatorEngine`: Pure calculation logic
   - `CalculatorModels`: Data classes and enums
   - No Android dependencies

4. **Infrastructure**
   - `CalculatorApplication`: App-level initialization
   - Widgets and Services
   - Theme configuration

---

## Coding Standards

### Kotlin Style Guide

Follow the [official Kotlin coding conventions](https://kotlinlang.org/docs/coding-conventions.html):

#### Naming Conventions

```kotlin
// Classes and objects: PascalCase
class CalculatorEngine

// Functions and properties: camelCase
fun calculateResult()
val displayText: String

// Constants: UPPER_SNAKE_CASE
const val MAX_HISTORY_SIZE = 50

// Composables: PascalCase
@Composable
fun CalculatorButton()
```

#### Code Organization

```kotlin
// 1. Package declaration
package com.yourcompanyname.calculator

// 2. Imports (grouped and sorted)
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

// 3. Class/Interface declaration
class MyClass {
    // 4. Properties
    private val privateProperty = 0
    var publicProperty = 0
    
    // 5. Init blocks
    init {
        // initialization
    }
    
    // 6. Functions
    fun publicFunction() {}
    private fun privateFunction() {}
    
    // 7. Companion object (if needed)
    companion object {
        const val CONSTANT = "value"
    }
}
```

### Jetpack Compose Guidelines

#### State Management

```kotlin
// Good: State hoisting
@Composable
fun MyScreen(
    value: String,
    onValueChange: (String) -> Unit
) {
    // UI code
}

// Bad: Internal state management
@Composable
fun MyScreen() {
    var value by remember { mutableStateOf("") } // Don't do this for screen-level state
}
```

#### Composable Structure

```kotlin
@Composable
fun MyComposable(
    // Required parameters first
    requiredParam: String,
    // Optional parameters
    optionalParam: String = "default",
    // Modifier always last
    modifier: Modifier = Modifier
) {
    // Implementation
}
```

### Documentation

#### Class Documentation

```kotlin
/**
 * Manages calculator state and operations.
 *
 * This ViewModel handles:
 * - Display text updates
 * - Operation execution
 * - History management
 */
class CalculatorViewModel : ViewModel() {
    // ...
}
```

#### Function Documentation

```kotlin
/**
 * Converts a number from one base to another.
 *
 * @param value The number to convert as a string
 * @param fromBase The source base
 * @param toBase The target base
 * @return The converted number as a string, or "Error" if conversion fails
 */
fun convertBase(value: String, fromBase: NumberBase, toBase: NumberBase): String {
    // ...
}
```

---

## Making Changes

### 1. Create a Branch

```bash
# Update your fork
git checkout main
git pull upstream main

# Create a feature branch
git checkout -b feature/your-feature-name
# or
git checkout -b fix/bug-description
```

### Branch Naming

- `feature/` - New features (e.g., `feature/add-unit-converter`)
- `fix/` - Bug fixes (e.g., `fix/division-by-zero`)
- `refactor/` - Code refactoring (e.g., `refactor/calculator-engine`)
- `docs/` - Documentation updates (e.g., `docs/update-readme`)

### 2. Make Your Changes

- Keep commits small and focused
- Write clear commit messages
- Test your changes thoroughly

### 3. Commit Guidelines

```bash
# Good commit message format
git commit -m "Add: Currency converter feature"
git commit -m "Fix: Division by zero crashes app"
git commit -m "Refactor: Improve CalculatorEngine efficiency"
git commit -m "Docs: Add contribution guidelines"
```

Commit message format:
```
Type: Brief description (50 chars or less)

Detailed explanation if necessary (wrap at 72 characters).
Include:
- Why this change was made
- What problem it solves
- Any side effects
```

Types:
- `Add:` - New feature
- `Fix:` - Bug fix
- `Update:` - Updating existing feature
- `Refactor:` - Code restructuring
- `Docs:` - Documentation
- `Test:` - Adding/updating tests
- `Style:` - Code style changes (formatting, naming)

---

## Testing

### Running Tests

```bash
# Unit tests
./gradlew test

# Specific test class
./gradlew test --tests CalculatorEngineTest

# Instrumented tests (requires connected device/emulator)
./gradlew connectedAndroidTest

# With coverage
./gradlew testDebugUnitTest jacocoTestReport
```

### Writing Tests

#### Unit Test Example

```kotlin
class CalculatorEngineTest {
    private lateinit var engine: CalculatorEngine
    
    @Before
    fun setUp() {
        engine = CalculatorEngine()
    }
    
    @Test
    fun `addition returns correct result`() {
        engine.setOperation(CalculatorOperation.Add, "5")
        val result = engine.calculate("3")
        assertEquals("8", result)
    }
    
    @Test
    fun `division by zero returns error`() {
        engine.setOperation(CalculatorOperation.Divide, "10")
        val result = engine.calculate("0")
        assertEquals("Error", result)
    }
}
```

#### Compose UI Test Example

```kotlin
@RunWith(AndroidJUnit4::class)
class CalculatorScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    
    @Test
    fun calculatorButton_isClickable() {
        composeTestRule.setContent {
            CalculatorButton(
                text = "5",
                onClick = {}
            )
        }
        
        composeTestRule
            .onNodeWithText("5")
            .assertIsDisplayed()
            .performClick()
    }
}
```

### Test Coverage Goals

- **Minimum**: 60% code coverage
- **Target**: 80% code coverage
- **Focus areas**:
  - CalculatorEngine: 90%+ coverage
  - ViewModel logic: 80%+ coverage
  - UI critical paths: 70%+ coverage

---

## Submitting Changes

### 1. Push Your Changes

```bash
# Push to your fork
git push origin feature/your-feature-name
```

### 2. Create Pull Request

1. Go to the original repository on GitHub
2. Click "New Pull Request"
3. Select your fork and branch
4. Fill in the PR template:

```markdown
## Description
Brief description of changes

## Type of Change
- [ ] Bug fix
- [ ] New feature
- [ ] Breaking change
- [ ] Documentation update

## Testing
- [ ] Unit tests pass
- [ ] Instrumented tests pass
- [ ] Manual testing completed

## Checklist
- [ ] Code follows style guidelines
- [ ] Self-review completed
- [ ] Comments added for complex code
- [ ] Documentation updated
- [ ] No new warnings
```

### 3. PR Review Process

1. **Automated Checks**: CI will run tests automatically
2. **Code Review**: Maintainers will review your code
3. **Feedback**: Address any requested changes
4. **Approval**: Once approved, PR will be merged

### Tips for Faster PR Approval

- Keep PRs focused and small
- Include tests for new features
- Update documentation
- Respond promptly to feedback
- Be patient and respectful

---

## Feature Requests

### Proposing New Features

1. **Check Existing Issues**: Search for similar requests
2. **Create an Issue**: Use the feature request template
3. **Discuss**: Engage with maintainers and community
4. **Wait for Approval**: Before starting implementation

### Feature Request Template

```markdown
**Feature Description**
Clear description of the proposed feature

**Use Case**
Why is this feature needed? What problem does it solve?

**Proposed Solution**
How should this feature work?

**Alternatives Considered**
What other solutions did you consider?

**Additional Context**
Screenshots, mockups, or examples
```

---

## Current Priority Areas

Want to contribute but not sure where to start? These areas need attention:

### High Priority
1. **Graphing Calculator**: Implement function graphing
2. **Unit Tests**: Increase test coverage
3. **Accessibility**: Add TalkBack support
4. **Documentation**: More code examples

### Medium Priority
1. **Unit Converter**: Add common unit conversions
2. **Landscape Layouts**: Optimize for landscape mode
3. **Custom Themes**: More color schemes
4. **Keyboard Support**: Physical keyboard input

### Low Priority
1. **Complex Numbers**: Support for i
2. **Matrix Operations**: Basic matrix calculations
3. **Currency Converter**: With API integration
4. **Equation Solver**: Solve for x

---

## Resources

### Documentation
- [Kotlin Documentation](https://kotlinlang.org/docs/home.html)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Material Design 3](https://m3.material.io/)
- [Android Architecture](https://developer.android.com/topic/architecture)

### Tools
- [Android Studio](https://developer.android.com/studio)
- [Compose Preview](https://developer.android.com/jetpack/compose/tooling)
- [Layout Inspector](https://developer.android.com/studio/debug/layout-inspector)

---

## Questions?

- **GitHub Issues**: For bugs and features
- **Discussions**: For questions and ideas
- **Pull Requests**: For code contributions

---

## License

By contributing to OmniCalculator, you agree that your contributions will be licensed under the MIT License.

---

**Thank you for contributing to OmniCalculator!**

*Together, we're building a better calculator for everyone.* 🚀

# OmniCalculator

A comprehensive Android calculator app built with Kotlin and Jetpack Compose, offering basic arithmetic, scientific calculations, and advanced features.

## Features

### Core Features
- **Basic Calculator**: Perform addition, subtraction, multiplication, and division
- Clean interface with numeric keypad (0-9)
- Decimal point support
- AC (All Clear) button
- Real-time display of input and results

### Advanced Features

#### Scientific Calculator Mode
- Trigonometric functions (sin, cos, tan)
- Logarithmic functions (log, ln)
- Power functions (exponents, square roots)
- Percentage calculations

#### Base Conversions
- Convert between decimal, binary, hexadecimal, and octal number systems

#### Memory Functions
- M+ (Memory Add)
- M- (Memory Subtract)
- MR (Memory Recall)
- MC (Memory Clear)

#### Additional Features
- **Calculation History**: View and reuse previous calculations
- **Dark/Light Theme**: Switch between themes with persistent preferences
- **Lock-screen Widget**: Quick access to calculator from lock screen
- **Floating Calculator**: Always-on-top calculator widget

## Technical Stack

- **Language**: Kotlin
- **Framework**: Android (native)
- **UI Toolkit**: Jetpack Compose
- **Build Tool**: Gradle
- **Target API**: 34
- **Minimum API**: 21

## UI/UX

- Responsive design for various screen sizes and orientations
- Touch-friendly button layout
- Smooth animations and transitions
- Minimalistic and professional aesthetic
- Material Design 3

## Privacy & Performance

- **Privacy Compliant**: No data collection or sharing
- **Offline Usage**: Works completely offline
- **Optimized Performance**: Efficient battery usage
- **Package Name**: com.yourcompanyname.calculator

## Building the Project

1. Clone the repository:
```bash
git clone https://github.com/kimbef/OmniCalculator.git
cd OmniCalculator
```

2. Open the project in Android Studio (Electric Eel or newer)

3. Sync Gradle files

4. Run the app on an emulator or physical device

## Requirements

- Android Studio Electric Eel or newer
- Android SDK 34
- Minimum Android version: 5.0 (API 21)

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Architecture

The app follows modern Android development practices:
- MVVM architecture pattern
- Jetpack Compose for UI
- DataStore for preferences
- ViewModel for state management
- Material Design 3 theming

## Screens

1. **Calculator**: Main calculator interface with basic and scientific modes
2. **History**: View and reuse calculation history
3. **Base Conversion**: Convert numbers between different bases
4. **Settings**: Configure theme preferences

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

# MT5

A modern Android application built with cutting-edge technologies following Modern Android Development (MAD) practices.

## 📱 Tech Stack

### Core
- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Min SDK**: 28
- **Target SDK**: 36
- **Compile SDK**: 36
- **JVM Target**: 11

### Architecture
- **Pattern**: MVI (Model-View-Intent)
- **State Management**: Unidirectional Data Flow
- **Navigation**: Jetpack Navigation Compose (Type-Safe)
- **Dependency Injection**: Koin

### UI Components
- **Design System**: Material Design 3
- **Image Loading**: Coil
- **Adaptive UI**: WindowSizeClass

### Networking
- **Client**: Retrofit
- **HTTP Engine**: OkHttp
- **Serialization**: Kotlinx Serialization
- **Debug Tools**: Chucker

### Data & Persistence
- **Database**: Room (KSP)
- **Preferences**: Jetpack DataStore (Proto)

### Async & Reactive
- **Coroutines**: Kotlinx Coroutines
- **Lifecycle**: Lifecycle Runtime KTX

## 🏗️ Architecture

This project follows a strict feature-based MVI architecture with clean separation of concerns:

```
app/src/main/java/uz/anvar/mt5/
├── features/              # Feature modules
│   └── feature_name/
│       ├── FeatureNameRoute.kt
│       ├── FeatureNameScreen.kt
│       ├── FeatureNameViewModel.kt
│       ├── components/
│       └── state/
├── core/                  # Common utilities
├── di/                    # Koin modules
├── data/                  # Repositories & DAO
├── MainActivity.kt        # Entry point
└── App.kt                 # Application class
```

## 🚀 Getting Started

### Prerequisites
- Android Studio Ladybug or later
- JDK 11 or higher
- Android SDK 36

### Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/ONVETI/mt5.git
   cd mt5
   ```

2. **Open in Android Studio**
   - Open the project in Android Studio
   - Let Gradle sync complete

3. **Build the project**
   ```bash
   ./gradlew build
   ```

4. **Run the app**
   ```bash
   ./gradlew installDebug
   ```

## 🛠️ Build Commands

### Clean & Build
```bash
./gradlew clean
./gradlew build
```

### Run Tests
```bash
# Unit tests
./gradlew test

# Instrumented tests
./gradlew connectedAndroidTest
```

### Lint Check
```bash
./gradlew lint
```

### Create APK
```bash
./gradlew assembleDebug
./gradlew assembleRelease
```

## 📝 Creating New Features

Use the automated feature generation script:

```bash
./create_feature.sh feature_name
```

This ensures:
- Consistent file structure
- Proper package naming
- Boilerplate MVI setup (State, Action, ViewModel, Screen)
- Alignment with project architectural standards

## 🔧 Configuration

### Gradle Properties
- **Min SDK**: 28
- **Target SDK**: 36
- **Version Code**: 1
- **Version Name**: 1.0

### Build Types
- **Debug**: Development build with debugging enabled
- **Release**: Production build with minification and ProGuard

## 🧪 Testing Strategy

- **Unit Tests**: JUnit for ViewModels, UseCases
- **UI Tests**: Compose Test Rule for interaction testing
- **Integration Tests**: Room DAO, DI container tests

## 🔒 Security

- **Obfuscation**: R8 enabled in Release builds
- **Network Security**: Configured via `network_security_config.xml`
- **Storage**: EncryptedSharedPreferences for sensitive data

## 📦 Dependencies

Main dependencies managed via Version Catalog (`gradle/libs.versions.toml`):

| Library | Version |
|---------|---------|
| Core KTX | 1.18.0 |
| Lifecycle Runtime | 2.10.0 |
| Activity Compose | 1.13.0 |
| Compose BOM | 2026.02.01 |
| Navigation Compose | 2.9.7 |
| Kotlin | 2.2.10 |
| AGP | 9.1.0 |

## 📄 License

This project is proprietary. All rights reserved.

## 👥 Contributors

- **Package**: `uz.anvar.mt5`

## 🐛 Issues & Feature Requests

Please use GitHub Issues to report bugs or request features.

---

Built with ❤️ using Modern Android Development practices

# News Aggregator - Kotlin Multiplatform

A production-quality News Aggregator application built using Kotlin Multiplatform (KMP) and Compose Multiplatform.

## Tech Stack
- **UI**: Compose Multiplatform (Material 3)
- **Navigation**: Voyager
- **Dependency Injection**: Koin
- **Networking**: Ktor
- **Serialization**: kotlinx.serialization
- **Local Database**: SQLDelight
- **Image Loading**: Coil 3
- **Architecture**: MVVM + Clean Architecture

## Folder Structure
- `composeApp/commonMain`: Shared logic and UI
    - `data`: Repositories and Data Sources (Local/Remote)
    - `database`: SQLDelight database setup
    - `di`: Koin modules
    - `model`: Domain models
    - `ui`: Components, Screens, and Theme
    - `viewmodel`: ViewModels for state management
- `composeApp/androidMain`: Android-specific implementations (Drivers, Platform Actions)
- `composeApp/iosMain`: iOS-specific implementations

## Key Features
- **Splash Screen**: Animated entry.
- **Home**: Trending news list with pull-to-refresh and shimmer loading.
- **Search**: Debounced search (500ms) with persistent history.
- **Article Details**: Modal bottom sheet with platform-specific browser integration (Chrome Custom Tabs on Android).
- **Offline First**: Search history persists across app restarts.
- **Responsive UI**: Support for Dark Mode and various screen sizes.

## How to Run
### Android
1. Open the project in Android Studio.
2. Select the `composeApp` run configuration.
3. Run on an emulator or physical device.

### iOS
1. Open the `iosApp` folder in Xcode or use the `iosApp` run configuration in Android Studio.
2. Build and run on an iOS simulator or device.

## Future Improvements
- Add more news sources via API.
- Implement bookmarking functionality.
- Add push notifications for trending news.
- Improve animations and transitions.

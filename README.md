**Quiz App**
A modern Android quiz application built with Kotlin and Jetpack Compose. The app fetches a set of questions from a remote API, allows the user to answer them, and displays the final score and longest correct answer streak.

✨ Features
Remote Data Fetching: Seamlessly retrieves quiz questions from a remote API using Retrofit.

Intuitive UI: A clean and responsive user interface declarative in nature built entirely with Jetpack Compose.

Quiz Logic: Tracks the number of correct answers and calculates the longest correct streak.

Dependency Injection: Uses Hilt for easy management of dependencies across the app.

Scalable Architecture: Follows a Clean Architecture approach, ensuring separation of concerns and maintainable code.

🏗️ Architecture
This project is structured following the principles of Clean Architecture and the Model-View-ViewModel (MVVM) pattern. This approach provides a clear separation of concerns, making the codebase more testable, maintainable, and scalable.

The project is divided into logical layers:

data->remote: This layer is responsible for fetching data from the remote API. It contains the QuizModel data class, the QuizApi interface (for Retrofit), and the implementation of the repository. To extend this for support offline architecture, we can easily extend by adding a room database in the same data layer and perform local calls too!

repository->remote: This layer defines the contract for fetching quiz data via the IQuizRepository interface and provides its concrete implementation (QuizRepositoryImpl). It acts as a single source of truth for the data, abstracting the remote data source from the presentation layer. To extend this for support offline architecture, we can easily extend by adding a room database repository in the same repository layer.

presentation: This layer contains the UI logic and state management.

viewmodel: The MainViewModel manages the UI state, handles user interactions, and communicates with the repository to fetch data. It exposes data via a StateFlow to the UI.

composables: The UI is built using Jetpack Compose. This package includes composable functions for the main quiz screen (MainScreen.kt), the option buttons (Option.kt), and the final results screen (ResultScreen.kt).

🛠️ Tech Stack & Libraries
Kotlin: The primary language for Android development.

Jetpack Compose: Modern UI toolkit for building native Android UI.

Hilt: A dependency injection library for Android that reduces the boilerplate of manual dependency injection.

Retrofit: A type-safe HTTP client for Android and Java to handle API calls.

Material Theme: For a consistent and modern look and feel.

Coroutines: For asynchronous and concurrent programming, handling network requests without blocking the main thread.

JUnit: For writing unit tests.

📂 Project Structure
com.apps.quiz
├── data.remote
│   ├── models
│   │   └── QuizModel.kt      // Data model for API response
│   └── QuizApi.kt          // Retrofit API interface
├── di
│   └── QuizModule.kt       // Hilt module for dependency injection
├── presentation
│   ├── composables
│   │   ├── MainScreen.kt   // Main UI for the quiz
│   │   ├── Option.kt       // Composable for quiz options
│   │   └── ResultScreen.kt // Screen to display results
│   ├── models
│   │   └── OptionModel.kt
│   └── viewmodel
│       └── MainViewModel.kt  // ViewModel for managing UI state
├── repository.remote
│   ├── IQuizRepository.kt      // Interface for the data repository
│   └── QuizRepositoryImpl.kt     // Implementation of the repository
├── ui.theme
│   └── ...                 // Composable theme, colors, typography
└── utils
    ├── AppConstants.kt     // Application-wide constants
    └── DispatcherProvider.kt // Wrapper for coroutine dispatchers
🌐 API
The application fetches quiz questions from the following API endpoint: https://gist.githubusercontent.com

🚀 How to Run

Bash

git clone https://github.com/pranjal-glowingstar/QuizApp.git
Open the project in Android Studio.

Build and run the app on an emulator or a physical device.

👨‍💻 Author
Pranjal - pranjal-glowingstar

# Android Authentication App (Kotlin)

A modern Android authentication app built with Kotlin, Jetpack Compose, and Material Design 3.

## Features

- ✅ User Registration
- ✅ User Login with JWT Authentication
- ✅ Dashboard with User Profile
- ✅ Edit Profile
- ✅ Secure Token Storage with DataStore
- ✅ Modern UI with Jetpack Compose
- ✅ Material Design 3
- ✅ MVVM Architecture

## Tech Stack

- **Language:** Kotlin
- **UI:** Jetpack Compose & Material Design 3
- **Architecture:** MVVM (Model-View-ViewModel)
- **Networking:** Retrofit 2 + OkHttp
- **Local Storage:** DataStore (Preferences)
- **Navigation:** Jetpack Navigation Compose
- **Async:** Kotlin Coroutines & Flow

## Project Structure

```
app/src/main/java/com/auth/mobile/
├── data/
│   ├── local/
│   │   └── TokenManager.kt          # Manages tokens and user data
│   ├── model/
│   │   └── Models.kt                 # Data models
│   ├── remote/
│   │   ├── ApiClient.kt              # Retrofit configuration
│   │   └── AuthApi.kt                # API endpoints
│   └── repository/
│       └── AuthRepository.kt         # Data layer abstraction
├── ui/
│   ├── auth/
│   │   ├── LoginScreen.kt            # Login UI
│   │   ├── LoginViewModel.kt
│   │   ├── RegisterScreen.kt         # Registration UI
│   │   └── RegisterViewModel.kt
│   ├── dashboard/
│   │   ├── DashboardScreen.kt        # Dashboard UI
│   │   └── DashboardViewModel.kt
│   ├── profile/
│   │   ├── EditProfileScreen.kt      # Edit Profile UI
│   │   └── EditProfileViewModel.kt
│   └── theme/
│       ├── Color.kt                  # App colors
│       ├── Theme.kt                  # Material theme
│       └── Type.kt                   # Typography
├── navigation/
│   ├── Screen.kt                     # Screen routes
│   └── AppNavigation.kt              # Navigation setup
├── AuthApplication.kt                # Application class
└── MainActivity.kt                   # Main entry point
```

## Setup Instructions

### 1. Copy to Android Studio

1. Copy the entire `mobile` folder to your desired location
2. Open Android Studio
3. Click **File > Open** and select the `mobile` folder
4. Wait for Gradle sync to complete

### 2. Configure Backend Connection

**For Android Emulator:**
- The app is configured to connect to `http://10.0.2.2:8080/api/`
- This maps to `localhost:8080` on your computer

**For Physical Device:**
1. Find your computer's local IP address:
   - Windows: Run `ipconfig` in command prompt
   - Look for IPv4 Address (e.g., 192.168.1.xxx)

2. Update the BASE_URL in `ApiClient.kt`:
```kotlin
private const val BASE_URL = "http://YOUR_IP_ADDRESS:8080/api/"
// Example: "http://192.168.1.100:8080/api/"
```

3. Make sure your phone and computer are on the same WiFi network

### 3. Run the Backend

Make sure your Spring Boot backend is running:
1. Start XAMPP MySQL on port 3307
2. Run the Spring Boot application in IntelliJ
3. Backend should be running on `http://localhost:8080`

### 4. Run the App

1. Connect your Android device or start an emulator
2. Click the **Run** button (green play icon) in Android Studio
3. Select your device
4. Wait for the app to build and install

## Screens

### Login Screen
- Username and password authentication
- Password visibility toggle
- Navigate to registration
- Error handling

### Registration Screen
- Create new account with username, email, password
- Optional: full name and phone number
- Password confirmation
- Form validation
- Success message redirects to login

### Dashboard Screen
- Display user profile information
- Profile avatar with initials
- View username, email, full name, phone number
- Edit profile button
- Logout button

### Edit Profile Screen
- Update username, email, full name, phone number
- Form validation
- Success/error messages
- Save or cancel changes

## API Configuration

The app connects to the same Spring Boot backend as the web app:

**Endpoints:**
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - Login user
- `GET /api/auth/profile` - Get current user profile
- `PUT /api/auth/profile` - Update user profile
- `POST /api/auth/logout` - Logout user

## Database Note

As requested, the app does NOT connect directly to the database. It uses the same Spring Boot backend which handles all database operations through the REST API.

## Troubleshooting

### Cannot connect to backend
- Verify backend is running on port 8080
- Check the BASE_URL in ApiClient.kt
- For physical devices, ensure same WiFi network
- Try pinging your computer's IP from your phone

### Build errors
- Make sure you have JDK 17 installed
- File > Invalidate Caches / Restart
- Clean and rebuild project

### App crashes on startup
- Check Logcat in Android Studio for error messages
- Verify all dependencies are synced

## Gradle Version

- Gradle: 8.2
- Android Gradle Plugin: 8.2.0
- Kotlin: 1.9.20
- Minimum SDK: 24 (Android 7.0)
- Target SDK: 34 (Android 14)

## Dependencies

All dependencies are managed in `app/build.gradle.kts`:
- Jetpack Compose BOM 2024.01.00
- Material Design 3
- Navigation Compose
- Retrofit 2.9.0
- OkHttp 4.12.0
- DataStore Preferences
- Kotlin Coroutines

## License

This project is for educational purposes.

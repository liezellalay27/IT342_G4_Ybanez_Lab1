# Quick Start Guide

## Step 1: Open in Android Studio

1. Open Android Studio
2. Click "Open an Existing Project"
3. Navigate to and select the `mobile` folder
4. Wait for Gradle sync to complete (first time may take a few minutes)

## Step 2: Configure Backend Connection

### For Android Emulator (Recommended for Testing)
No changes needed! The app is pre-configured to connect to `10.0.2.2:8080` which maps to your computer's `localhost:8080`.

### For Physical Android Device
1. Connect your phone and computer to the same WiFi
2. Find your computer's IP address:
   - Open Command Prompt (Windows)
   - Type: `ipconfig`
   - Look for "IPv4 Address" under your WiFi adapter (e.g., 192.168.1.100)
3. Open `ApiClient.kt` in Android Studio:
   - Navigate to: `app/src/main/java/com/auth/mobile/data/remote/ApiClient.kt`
   - Change line 13 from:
     ```kotlin
     private const val BASE_URL = "http://10.0.2.2:8080/api/"
     ```
     To:
     ```kotlin
     private const val BASE_URL = "http://YOUR_IP_ADDRESS:8080/api/"
     ```
     Replace `YOUR_IP_ADDRESS` with your actual IP (e.g., `192.168.1.100`)

## Step 3: Start Backend

1. Start XAMPP MySQL (port 3307)
2. Run Spring Boot backend in IntelliJ
3. Verify backend is running at `http://localhost:8080`

## Step 4: Run the App

1. Click the green **Run** button (▶️) in Android Studio toolbar
2. Select your device (emulator or physical device)
3. Wait for build and installation
4. App will launch automatically

## Step 5: Test the App

1. **Register**: Create a new account
2. **Login**: Sign in with your credentials
3. **Dashboard**: View your profile
4. **Edit Profile**: Update your information
5. **Logout**: Sign out and return to login

## Troubleshooting

**Cannot connect to server:**
- Verify backend is running (check IntelliJ console)
- For physical device: Check IP address and WiFi connection
- Check Logcat in Android Studio for detailed errors

**Build fails:**
- Go to: File > Invalidate Caches > Invalidate and Restart
- Try: Build > Clean Project, then Build > Rebuild Project

**App crashes:**
- Check Logcat tab in Android Studio for error details
- Verify all Gradle dependencies synced successfully

## Features to Test

✅ User Registration with validation
✅ Login with username/password
✅ JWT token authentication
✅ View user dashboard
✅ Edit profile information
✅ Logout functionality

## API Endpoints Used

- POST `/api/auth/register` - Create account
- POST `/api/auth/login` - Authenticate user
- GET `/api/auth/profile` - Get user data
- PUT `/api/auth/profile` - Update user data
- POST `/api/auth/logout` - End session

---

**Note:** The app does NOT connect directly to the database. All data operations go through your Spring Boot REST API, which handles the MySQL database connection.

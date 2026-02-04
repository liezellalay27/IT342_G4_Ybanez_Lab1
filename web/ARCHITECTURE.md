# Frontend Architecture Overview

## 📊 Application Flow

```
User Request
    ↓
Navigation Component (Navigation.js)
    ↓
React Router (App.js)
    ↓
┌─────────────────┬──────────────────┬─────────────────────┐
│   Login.js      │  Registration.js  │  ProtectedRoute     │
│   (Public)      │  (Public)         │  → Dashboard.js     │
│                 │                   │     (Protected)      │
└────────┬────────┴────────┬──────────┴──────────┬──────────┘
         │                 │                      │
         └─────────────────┼──────────────────────┘
                           ↓
                    AuthContext.js
                   (Global State)
                           ↓
                    authService.js
                    (API Layer)
                           ↓
                    Axios Instance
                 (HTTP Client with JWT)
                           ↓
                    Backend API
                 (Spring Boot Server)
```

## 🔄 Component Interaction

### Registration Flow
1. User fills registration form → **Registration.js**
2. Form validation (client-side)
3. Call `register()` from **AuthContext**
4. **authService.js** sends POST to `/api/auth/register`
5. Success → Redirect to Login

### Login Flow
1. User enters credentials → **Login.js**
2. Call `login()` from **AuthContext**
3. **authService.js** sends POST to `/api/auth/login`
4. Receive JWT token and user data
5. Store in localStorage
6. Update AuthContext state
7. Redirect to Dashboard

### Protected Access Flow
1. User navigates to `/dashboard`
2. **ProtectedRoute** checks authentication
3. If authenticated → Show **Dashboard.js**
4. If not → Redirect to Login

### Logout Flow
1. User clicks Logout → **Navigation** or **Dashboard**
2. Call `logout()` from **AuthContext**
3. Clear localStorage
4. Clear AuthContext state
5. Redirect to Login

## 📂 File Responsibilities

### Core Files

**App.js**
- Sets up routing
- Wraps app with AuthProvider
- Defines route structure

**index.js**
- Application entry point
- Renders App component

### Context

**AuthContext.js**
- Manages authentication state
- Provides login/register/logout methods
- Handles token persistence
- Exposes `useAuth()` hook

### Services

**authService.js**
- Axios instance configuration
- API endpoint methods
- Request/Response interceptors
- Token injection
- Error handling

### Components

**Navigation.js**
- Dynamic navigation menu
- Shows different links based on auth state
- Logout button

**Login.js**
- Login form
- Form validation
- Error display
- Calls AuthContext.login()

**Registration.js**
- Registration form
- Comprehensive validation (email, password strength, matching)
- Error display
- Calls AuthContext.register()

**Dashboard.js**
- User profile display
- Account information
- Protected content
- Logout functionality

**ProtectedRoute.js**
- HOC (Higher Order Component)
- Checks authentication
- Redirects unauthorized users
- Shows loading state

## 🔐 Authentication State Management

```javascript
AuthContext provides:
{
  user: {
    id: number,
    username: string,
    email: string,
    role: string,
    createdAt: date
  } | null,
  
  isAuthenticated: boolean,
  loading: boolean,
  
  login: (credentials) => Promise,
  register: (userData) => Promise,
  logout: () => void
}
```

## 🎨 Styling Structure

Each component has its own CSS file:
- **index.css** → Global styles
- **App.css** → Shared form styles
- **Login.css** → Login-specific styles
- **Registration.css** → Registration-specific styles
- **Dashboard.css** → Dashboard-specific styles
- **Navigation.css** → Navigation-specific styles

## 🔄 Data Flow Example

**User Login:**
```
User enters email/password
    ↓
Login.js validates input
    ↓
Calls useAuth().login(credentials)
    ↓
AuthContext.login() called
    ↓
authService.login() makes API call
    ↓
Axios POST to /api/auth/login with credentials
    ↓
Backend validates and returns { token, user }
    ↓
authService returns response
    ↓
AuthContext stores token in localStorage
    ↓
AuthContext updates user state
    ↓
Login.js navigates to /dashboard
    ↓
ProtectedRoute allows access
    ↓
Dashboard.js renders with user data
```

## 🛡️ Security Features

1. **JWT Token Management**
   - Stored in localStorage
   - Automatically added to requests
   - Expired tokens trigger logout

2. **Protected Routes**
   - ProtectedRoute component guards pages
   - Unauthenticated users redirected

3. **Form Validation**
   - Client-side validation before API calls
   - Password strength requirements
   - Email format validation

4. **Error Handling**
   - Axios interceptors catch errors
   - User-friendly error messages
   - Network error handling

## 🚀 Getting Started

1. Install dependencies: `npm install`
2. Configure backend URL in `.env`
3. Start development server: `npm start`
4. Access at `http://localhost:3000`

## 📝 API Contract

The frontend expects these responses:

**POST /api/auth/login**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "user": {
    "id": 1,
    "username": "john_doe",
    "email": "john@example.com",
    "role": "USER",
    "createdAt": "2026-02-04T10:00:00Z"
  }
}
```

**POST /api/auth/register**
```json
{
  "message": "User registered successfully",
  "data": { /* user data */ }
}
```

## 🎯 Key Features Implemented

✅ User Registration with validation  
✅ User Login with JWT  
✅ Protected Routes  
✅ User Dashboard/Profile  
✅ Logout functionality  
✅ Persistent authentication  
✅ Token management  
✅ Error handling  
✅ Responsive design  
✅ Modern UI/UX  

---

For detailed setup instructions, see [SETUP.md](SETUP.md)  
For complete documentation, see [README.md](README.md)

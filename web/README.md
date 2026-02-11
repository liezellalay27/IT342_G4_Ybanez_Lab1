# User Authentication System - Frontend

A modern React.js frontend application for user authentication with registration, login, profile management, and logout functionality.

## 🚀 Features

- **User Registration** with validation
  - Email format validation
  - Password strength validation (minimum 6 characters, must include uppercase, lowercase, and number)
  - Duplicate username/email checking
  - Confirm password matching

- **User Login** with JWT authentication
  - Secure credential validation
  - Session management with JWT tokens
  - Automatic token storage in localStorage

- **Protected Routes**
  - Route guards for authenticated pages
  - Automatic redirect to login for unauthenticated users
  - Persistent authentication across page reloads

- **User Dashboard/Profile**
  - View user information
  - Display account statistics
  - Quick action buttons
  - Logout functionality

- **Modern UI/UX**
  - Responsive design for all devices
  - Beautiful gradient styling
  - Smooth animations and transitions
  - Clear error messages

## 📁 Project Structure

```
web/
├── public/
│   └── index.html
├── src/
│   ├── components/
│   │   ├── Dashboard.js          # Protected dashboard page
│   │   ├── Dashboard.css
│   │   ├── Login.js              # Login form component
│   │   ├── Login.css
│   │   ├── Registration.js       # Registration form component
│   │   ├── Registration.css
│   │   ├── Navigation.js         # Navigation bar component
│   │   ├── Navigation.css
│   │   └── ProtectedRoute.js     # Route protection component
│   ├── context/
│   │   └── AuthContext.js        # Global authentication state management
│   ├── services/
│   │   └── authService.js        # API service layer for backend calls
│   ├── App.js                    # Main app component with routing
│   ├── App.css
│   ├── index.js                  # App entry point
│   └── index.css
├── .env                          # Environment variables
├── .gitignore
└── package.json
```

## 🛠️ Technologies Used

- **React 18.2** - UI library
- **React Router DOM 6.20** - Client-side routing
- **Axios 1.6** - HTTP client for API calls
- **Context API** - State management
- **CSS3** - Modern styling with gradients and animations

## 📋 Prerequisites

- Node.js (v14 or higher)
- npm (v6 or higher)
- Backend API running on `http://localhost:8080/api` (or configure in .env)

## 🔧 Installation

1. Navigate to the web directory:
   ```bash
   cd web
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Configure environment variables in `.env`:
   ```
   REACT_APP_API_URL=http://localhost:8080/api
   ```

## 🚀 Running the Application

Start the development server:
```bash
npm start
```

The application will open at `http://localhost:3000`

## 📝 API Integration

The frontend expects the following backend API endpoints:

### Authentication Endpoints

- **POST** `/api/auth/register`
  - Body: `{ username, email, password }`
  - Response: `{ message, data }`

- **POST** `/api/auth/login`
  - Body: `{ email, password }`
  - Response: `{ token, user: { id, username, email, role, createdAt } }`

- **POST** `/api/auth/logout`
  - Headers: `Authorization: Bearer <token>`
  - Response: `{ message }`

- **GET** `/api/auth/profile`
  - Headers: `Authorization: Bearer <token>`
  - Response: `{ user }`

## 🔐 Security Features

- **Password Encryption**: Passwords are never stored in frontend state
- **JWT Token Management**: Secure token storage in localStorage
- **Automatic Token Refresh**: Interceptor handles expired tokens
- **Protected Routes**: Unauthorized access prevention
- **Input Validation**: Client-side validation before API calls
- **HTTPS Support**: Ready for secure communication

## 🎨 Component Overview

### AuthContext
Manages global authentication state and provides authentication methods throughout the app.

### Services Layer
- `authService.js`: Handles all API communications with axios interceptors for token management

### Components
- **Login**: User login form with validation
- **Registration**: User registration form with comprehensive validation
- **Dashboard**: Protected user profile and account information
- **Navigation**: Dynamic navigation based on authentication status
- **ProtectedRoute**: HOC for route protection

## 📱 Responsive Design

The application is fully responsive and works seamlessly on:
- Desktop (1200px+)
- Tablet (768px - 1199px)
- Mobile (320px - 767px)

## 🐛 Error Handling

- Network errors are caught and displayed to users
- Form validation errors are shown inline
- API errors are displayed with clear messages
- Token expiration triggers automatic logout

## 🔄 Available Scripts

- `npm start` - Start development server
- `npm build` - Create production build
- `npm test` - Run tests
- `npm eject` - Eject from Create React App (irreversible)

## 🤝 Integration with Backend

Ensure your Spring Boot backend is running and configured to:
1. Accept CORS requests from `http://localhost:3000`
2. Return JWT tokens in the specified format
3. Implement the authentication endpoints listed above
4. Use Bearer token authentication

## 📄 License

This project is part of IT342_G4_Ybanez_Lab1

## 👥 Authors

Liezel A. Ybañez

---

**Note**: Make sure the backend server is running before starting the frontend application for full functionality.

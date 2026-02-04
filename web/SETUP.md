# Quick Setup Guide

## Installation Steps

1. **Navigate to web directory:**
   ```bash
   cd web
   ```

2. **Install dependencies:**
   ```bash
   npm install
   ```

3. **Start the development server:**
   ```bash
   npm start
   ```

## What You Get

✅ **Complete React.js Authentication System** with:

### Components
- ✅ Registration form with validation
- ✅ Login form with JWT authentication
- ✅ Protected dashboard/profile page
- ✅ Navigation bar with dynamic links
- ✅ Route protection for authenticated pages

### Features
- ✅ Email format validation
- ✅ Password strength validation (6+ chars, uppercase, lowercase, number)
- ✅ Duplicate checking
- ✅ JWT token management
- ✅ Protected routes
- ✅ Automatic logout on token expiration
- ✅ Persistent authentication (localStorage)
- ✅ Error handling and display
- ✅ Responsive design

### Architecture
- ✅ Context API for state management
- ✅ Service layer for API calls
- ✅ Axios interceptors for token handling
- ✅ React Router for navigation
- ✅ Component-based structure

## Project Structure Created

```
web/
├── public/
│   └── index.html
├── src/
│   ├── components/
│   │   ├── Dashboard.js & .css
│   │   ├── Login.js & .css
│   │   ├── Registration.js & .css
│   │   ├── Navigation.js & .css
│   │   └── ProtectedRoute.js
│   ├── context/
│   │   └── AuthContext.js
│   ├── services/
│   │   └── authService.js
│   ├── App.js & .css
│   ├── index.js & .css
├── .env
├── .gitignore
├── package.json
└── README.md
```

## Backend Requirements

Your Spring Boot backend must provide these endpoints:

1. **POST** `/api/auth/register`
   - Input: `{ username, email, password }`
   - Output: Success message

2. **POST** `/api/auth/login`
   - Input: `{ email, password }`
   - Output: `{ token, user: { id, username, email, role, createdAt } }`

3. **GET** `/api/auth/profile`
   - Headers: `Authorization: Bearer <token>`
   - Output: User data

4. **POST** `/api/auth/logout`
   - Headers: `Authorization: Bearer <token>`
   - Output: Success message

## Configuration

Update `.env` file if your backend is not at `http://localhost:8080`:

```
REACT_APP_API_URL=http://your-backend-url/api
```

## Testing the Application

1. Start your backend server first
2. Start the React frontend (`npm start`)
3. Navigate to `http://localhost:3000`
4. Register a new user
5. Login with credentials
6. View dashboard
7. Logout

## Common Issues

**CORS Error?**
- Configure your Spring Boot backend to allow CORS from `http://localhost:3000`

**API Connection Failed?**
- Check if backend is running
- Verify API URL in `.env` file
- Check backend console for errors

**Login Not Working?**
- Verify backend returns JWT token in format: `{ token: "...", user: {...} }`
- Check browser console for error messages

## Next Steps

1. ✅ Frontend is complete and ready
2. 🔄 Ensure backend endpoints match the API contract
3. 🔄 Configure CORS in Spring Boot
4. 🔄 Test registration flow
5. 🔄 Test login and authentication
6. 🔄 Test protected routes

---

Need help? Check the main README.md for detailed documentation.

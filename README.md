# IT342_G4_Ybanez_Lab1

## User Authentication System

A comprehensive full-stack user authentication system with secure registration, login, profile management, and logout functionality.

---

## 📋 Project Description

This project implements a complete user authentication system following modern web development practices. The system provides:

- **User Registration** - New users can create accounts with username, email, and password
- **User Login** - Secure authentication using JWT (JSON Web Tokens)
- **Profile Dashboard** - Authenticated users can view their profile information
- **Logout** - Users can securely logout, destroying their session
- **Protected Routes** - Unauthorized users are redirected to login
- **Password Security** - Passwords are encrypted using BCrypt
- **Session Management** - JWT tokens for stateless authentication
- **Input Validation** - Client-side and server-side validation
- **Responsive Design** - Modern UI that works on all devices

### Key Features
✅ Email format validation  
✅ Password strength validation (6+ characters, uppercase, lowercase, number)  
✅ Duplicate username/email checking  
✅ JWT token generation and validation  
✅ Protected API endpoints  
✅ CORS configuration  
✅ Error handling and user feedback  
✅ Persistent authentication  

---

## 🛠️ Technologies Used

### Frontend
- **React.js 18.2** - UI library
- **React Router DOM 6.20** - Client-side routing
- **Axios 1.6** - HTTP client for API calls
- **Context API** - Global state management
- **CSS3** - Modern styling with gradients and animations

### Backend
- **Spring Boot 3.x** - Java framework
- **Java 19** - Programming language
- **Spring Security** - Authentication and authorization
- **Spring Data JPA** - Database ORM
- **JWT (JSON Web Tokens)** - Token-based authentication
- **BCrypt** - Password encryption
- **MySQL/PostgreSQL** - Relational database
- **Lombok** - Reduce boilerplate code
- **Maven** - Dependency management

### Tools & Environment
- **IntelliJ IDEA** - IDE for backend development
- **VS Code** - IDE for frontend development
- **Node.js & npm** - JavaScript runtime and package manager
- **Git & GitHub** - Version control
- **Postman** - API testing (optional)

---

## 🚀 Steps to Run Backend

### Prerequisites
- Java 19 installed
- MySQL or PostgreSQL installed
- IntelliJ IDEA (recommended)
- Maven

### Setup Instructions

1. **Navigate to backend directory:**
   ```bash
   cd backend
   ```

2. **Configure Database:**
   
   Edit `src/main/resources/application.properties`:
   ```properties
   # Database Configuration
   spring.datasource.url=jdbc:mysql://localhost:3306/auth_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   
   # JWT Secret Key
   jwt.secret=your-secret-key-here
   jwt.expiration=86400000
   ```

3. **Create Database:**
   ```sql
   CREATE DATABASE auth_db;
   ```

4. **Install Dependencies:**
   ```bash
   mvn clean install
   ```

5. **Run the Application:**
   
   **Option A - Using Maven:**
   ```bash
   mvn spring-boot:run
   ```
   
   **Option B - Using IntelliJ IDEA:**
   - Open the backend folder in IntelliJ
   - Locate `UserAuthenticationApplication.java`
   - Click the green play button or press `Shift + F10`

6. **Verify Backend is Running:**
   
   Backend should start at: `http://localhost:8080`
   
   Test endpoint: `http://localhost:8080/api/auth/test` (if available)

---

## 🌐 Steps to Run Web App

### Prerequisites
- Node.js (v14 or higher)
- npm (v6 or higher)

### Setup Instructions

1. **Navigate to web directory:**
   ```bash
   cd web
   ```

2. **Install Dependencies:**
   ```bash
   npm install
   ```
   
   **Note:** If you encounter PowerShell execution policy errors on Windows:
   ```powershell
   Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
   ```

3. **Configure Backend API URL:**
   
   Edit `.env` file in the web directory:
   ```env
   REACT_APP_API_URL=http://localhost:8080/api
   ```

4. **Start Development Server:**
   ```bash
   npm start
   ```

5. **Access the Application:**
   
   The app will automatically open at: `http://localhost:3000`
   
   If not, manually navigate to: `http://localhost:3000`

6. **Test the Application:**
   - Navigate to Registration page
   - Create a new account
   - Login with your credentials
   - View your profile dashboard
   - Test logout functionality

### Production Build
To create an optimized production build:
```bash
npm run build
```

---

## 📱 Steps to Run Mobile App

**Status:** Mobile application is not yet implemented.

**Planned Technologies:**
- React Native or Flutter
- Same backend API endpoints

**Future Implementation:**
- Mobile app will connect to the same Spring Boot backend
- Will use the same authentication API endpoints
- JWT token management for mobile sessions

---

## 📡 List of API Endpoints

### Base URL
```
http://localhost:8080/api
```

### Authentication Endpoints

#### 1. Register User
**Endpoint:** `POST /api/auth/register`

**Description:** Register a new user account

**Request Body:**
```json
{
  "username": "john_doe",
  "email": "john@example.com",
  "password": "Password123"
}
```

**Response (Success - 201 Created):**
```json
{
  "message": "User registered successfully",
  "data": {
    "id": 1,
    "username": "john_doe",
    "email": "john@example.com",
    "role": "USER",
    "createdAt": "2026-02-04T10:00:00"
  }
}
```

**Response (Error - 400 Bad Request):**
```json
{
  "message": "Email already exists"
}
```

---

#### 2. Login User
**Endpoint:** `POST /api/auth/login`

**Description:** Authenticate user and receive JWT token

**Request Body:**
```json
{
  "email": "john@example.com",
  "password": "Password123"
}
```

**Response (Success - 200 OK):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "user": {
    "id": 1,
    "username": "john_doe",
    "email": "john@example.com",
    "role": "USER",
    "createdAt": "2026-02-04T10:00:00"
  }
}
```

**Response (Error - 401 Unauthorized):**
```json
{
  "message": "Invalid email or password"
}
```

---

#### 3. Get User Profile
**Endpoint:** `GET /api/auth/profile`

**Description:** Get authenticated user's profile information

**Headers:**
```
Authorization: Bearer <jwt_token>
```

**Response (Success - 200 OK):**
```json
{
  "id": 1,
  "username": "john_doe",
  "email": "john@example.com",
  "role": "USER",
  "createdAt": "2026-02-04T10:00:00",
  "updatedAt": "2026-02-04T10:00:00"
}
```

**Response (Error - 401 Unauthorized):**
```json
{
  "message": "Invalid or expired token"
}
```

---

#### 4. Logout User
**Endpoint:** `POST /api/auth/logout`

**Description:** Logout user and invalidate session

**Headers:**
```
Authorization: Bearer <jwt_token>
```

**Response (Success - 200 OK):**
```json
{
  "message": "Logout successful"
}
```

---

### Error Responses

All endpoints may return the following error responses:

**400 Bad Request:**
```json
{
  "message": "Validation error message"
}
```

**401 Unauthorized:**
```json
{
  "message": "Unauthorized access"
}
```

**403 Forbidden:**
```json
{
  "message": "Access denied"
}
```

**404 Not Found:**
```json
{
  "message": "Resource not found"
}
```

**500 Internal Server Error:**
```json
{
  "message": "An error occurred on the server"
}
```

---

## 📂 Project Structure

```
IT342_G4_Ybanez_Lab1/
├── backend/                  # Spring Boot backend
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/auth/
│   │   │   │   ├── entity/          # User entity
│   │   │   │   ├── repository/      # JPA repositories
│   │   │   │   ├── service/         # Business logic
│   │   │   │   ├── controller/      # REST controllers
│   │   │   │   ├── dto/             # Data transfer objects
│   │   │   │   ├── security/        # JWT & Security config
│   │   │   │   ├── config/          # App configuration
│   │   │   │   └── exception/       # Exception handling
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── pom.xml
│   └── README.md
│
├── web/                      # React frontend
│   ├── public/
│   ├── src/
│   │   ├── components/       # React components
│   │   ├── context/          # Auth context
│   │   ├── services/         # API services
│   │   ├── App.js
│   │   └── index.js
│   ├── package.json
│   └── README.md
│
├── docs/                     # Documentation
│   ├── TASKLIST.md          # Task tracking
│   └── YBAÑEZ-SRS.pdf       # Requirements document
│
└── README.md                # This file
```

---

## 👥 Team

**Group 4 - Ybanez**

---

## 📝 License

This project is for educational purposes as part of IT342 course requirements.

---

## 📞 Support

For questions or issues, please refer to the documentation in the respective folders:
- Backend: `backend/README.md`
- Frontend: `web/README.md`
- Tasks: `docs/TASKLIST.md`

---

**Last Updated:** February 4, 2026
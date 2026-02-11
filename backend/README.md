# Spring Boot Authentication Backend

A RESTful API backend built with Spring Boot that provides JWT-based authentication for both Web and Mobile applications.

## Features

- User Registration
- User Login with JWT Token
- Protected Routes with JWT Authentication
- Get Current User Information (/me endpoint)
- User Logout
- MySQL Database Integration
- CORS Configuration for Web and Mobile
- Input Validation
- Global Exception Handling

## Technology Stack

- **Framework**: Spring Boot 3.2.0
- **Language**: Java 17
- **Database**: MySQL
- **Security**: Spring Security + JWT
- **Build Tool**: Maven
- **ORM**: JPA/Hibernate

## Prerequisites

Before running this application, make sure you have the following installed:

- Java 17 or higher
- Maven 3.6 or higher
- MySQL 8.0 or higher
- IDE (IntelliJ IDEA, Eclipse, or VS Code with Java extensions)

## Database Setup

1. Install MySQL and start the MySQL server

2. Create a database (optional, as it will be created automatically):
```sql
CREATE DATABASE auth_db;
```

3. Update database credentials in `src/main/resources/application.properties`:
```properties
spring.datasource.username=root
spring.datasource.password=your_password
```

## Installation & Running

### Method 1: Using Maven (Command Line)

1. Navigate to the backend directory:
```bash
cd backend
```

2. Install dependencies:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn spring-boot:run
```

### Method 2: Using IDE

1. Open the project in your IDE (IntelliJ IDEA recommended)
2. Wait for Maven to download dependencies
3. Run the `AuthBackendApplication.java` main class

### Method 3: Using JAR

1. Build the JAR file:
```bash
mvn clean package
```

2. Run the JAR:
```bash
java -jar target/auth-backend-1.0.0.jar
```

The server will start on `http://localhost:8080`

## API Endpoints

### Public Endpoints (No Authentication Required)

#### 1. Register User
```http
POST /api/auth/register
Content-Type: application/json

{
  "username": "john_doe",
  "email": "john@example.com",
  "password": "password123",
  "fullName": "John Doe",
  "phoneNumber": "+1234567890"
}
```

**Response (Success):**
```json
{
  "message": "User registered successfully!",
  "success": true
}
```

#### 2. Login User
```http
POST /api/auth/login
Content-Type: application/json

{
  "usernameOrEmail": "john_doe",
  "password": "password123"
}
```

**Response (Success):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer",
  "id": 1,
  "username": "john_doe",
  "email": "john@example.com",
  "fullName": "John Doe"
}
```

#### 3. Test Public Endpoint
```http
GET /api/test/public
```

### Protected Endpoints (Authentication Required)

Add the JWT token to the Authorization header:
```
Authorization: Bearer <your_jwt_token>
```

#### 1. Get Current User
```http
GET /api/auth/me
Authorization: Bearer <token>
```

**Response:**
```json
{
  "id": 1,
  "username": "john_doe",
  "email": "john@example.com",
  "fullName": "John Doe",
  "phoneNumber": "+1234567890",
  "createdAt": "2026-02-04T10:30:00"
}
```

#### 2. Logout
```http
POST /api/auth/logout
Authorization: Bearer <token>
```

**Response:**
```json
{
  "message": "User logged out successfully!",
  "success": true
}
```

#### 3. Test Protected Endpoint
```http
GET /api/test/protected
Authorization: Bearer <token>
```

## Project Structure

```
backend/
├── src/
│   ├── main/
│   │   ├── java/com/auth/
│   │   │   ├── AuthBackendApplication.java
│   │   │   ├── config/
│   │   │   │   ├── SecurityConfig.java
│   │   │   │   └── WebConfig.java
│   │   │   ├── controller/
│   │   │   │   ├── AuthController.java
│   │   │   │   └── TestController.java
│   │   │   ├── dto/
│   │   │   │   ├── AuthResponse.java
│   │   │   │   ├── LoginRequest.java
│   │   │   │   ├── MessageResponse.java
│   │   │   │   ├── RegisterRequest.java
│   │   │   │   └── UserResponse.java
│   │   │   ├── exception/
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   ├── entity/
│   │   │   │   └── User.java
│   │   │   ├── repository/
│   │   │   │   └── UserRepository.java
│   │   │   ├── security/
│   │   │   │   ├── JwtAuthenticationEntryPoint.java
│   │   │   │   ├── JwtAuthenticationFilter.java
│   │   │   │   └── JwtTokenProvider.java
│   │   │   └── service/
│   │   │       ├── AuthService.java
│   │   │       └── CustomUserDetailsService.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

## Configuration

### JWT Configuration

Edit `application.properties` to customize JWT settings:

```properties
# JWT secret key (should be at least 256 bits for HS256)
jwt.secret=YourSecretKeyHereMustBeAtLeast256BitsLongForHS256Algorithm

# JWT expiration time in milliseconds (86400000 = 24 hours)
jwt.expiration=86400000
```

### CORS Configuration

The application is configured to allow requests from:
- Web: `http://localhost:3000`
- Mobile: `http://localhost:8081`

To modify CORS settings, edit `WebConfig.java`:
```java
.allowedOrigins("http://localhost:3000", "http://localhost:8081")
```

## Testing the API

### Using cURL

**Register:**
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","email":"test@example.com","password":"test123","fullName":"Test User"}'
```

**Login:**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"usernameOrEmail":"testuser","password":"test123"}'
```

**Get Current User:**
```bash
curl -X GET http://localhost:8080/api/auth/me \
  -H "Authorization: Bearer <your_token_here>"
```

### Using Postman

1. Import the API endpoints
2. For protected routes, add token to Authorization tab
3. Select "Bearer Token" type
4. Paste the JWT token received from login

## Integration with Frontend

### ReactJS Web App

```javascript
// Login example
const response = await axios.post('http://localhost:8080/api/auth/login', {
  usernameOrEmail: 'john_doe',
  password: 'password123'
});
const token = response.data.token;
localStorage.setItem('token', token);

// Protected request
axios.get('http://localhost:8080/api/auth/me', {
  headers: { Authorization: `Bearer ${token}` }
});
```

### Android Kotlin Mobile App

```kotlin
// Using Retrofit
interface ApiService {
    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequest): AuthResponse
    
    @GET("api/auth/me")
    suspend fun getCurrentUser(@Header("Authorization") token: String): UserResponse
}

// Usage
val token = "Bearer $jwtToken"
val user = apiService.getCurrentUser(token)
```

## Troubleshooting

### Database Connection Issues
- Verify MySQL is running
- Check database credentials in `application.properties`
- Ensure the database `auth_db` exists or `createDatabaseIfNotExist=true` is set

### Port Already in Use
Change the port in `application.properties`:
```properties
server.port=8081
```

### JWT Token Invalid
- Check if token has expired (default: 24 hours)
- Verify the token is being sent in the correct format: `Bearer <token>`
- Ensure the JWT secret matches between deployments

## Security Notes

⚠️ **Important for Production:**

1. Change the JWT secret key to a strong, random value
2. Use environment variables for sensitive data
3. Enable HTTPS
4. Implement rate limiting
5. Add refresh token functionality
6. Restrict CORS origins to your actual domains
7. Use strong password requirements
8. Add account verification (email confirmation)

## License

This project is for educational purposes (IT342 Lab Assignment).

## Contributors

- Liezel A. Ybañez

---

For questions or issues, please contact the development team.

package com.auth.mobile.data.model

data class User(
    val id: Long? = null,
    val username: String,
    val email: String,
    val fullName: String? = null,
    val phoneNumber: String? = null,
    val enabled: Boolean = true
)

data class LoginRequest(
    val username: String,
    val password: String
)

data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String,
    val fullName: String? = null,
    val phoneNumber: String? = null
)

data class UpdateProfileRequest(
    val username: String,
    val email: String,
    val fullName: String? = null,
    val phoneNumber: String? = null
)

data class AuthResponse(
    val token: String,
    val user: User
)

data class MessageResponse(
    val message: String
)

data class UserResponse(
    val user: User
)

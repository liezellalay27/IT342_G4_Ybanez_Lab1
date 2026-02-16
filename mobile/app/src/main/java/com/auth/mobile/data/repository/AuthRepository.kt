package com.auth.mobile.data.repository

import com.auth.mobile.data.local.TokenManager
import com.auth.mobile.data.model.*
import com.auth.mobile.data.remote.ApiClient
import com.auth.mobile.data.remote.ErrorParser
import kotlinx.coroutines.flow.first

class AuthRepository(private val tokenManager: TokenManager) {
    
    private val api = ApiClient.authApi
    
    suspend fun register(request: RegisterRequest): Result<String> {
        return try {
            val response = api.register(request)
            if (response.isSuccessful) {
                Result.success(response.body()?.message ?: "Registration successful")
            } else {
                val errorMsg = ErrorParser.parseErrorBody(response.errorBody()?.string())
                Result.failure(Exception(errorMsg))
            }
        } catch (e: java.net.ConnectException) {
            Result.failure(Exception("Cannot connect to server. Please check if backend is running."))
        } catch (e: java.net.SocketTimeoutException) {
            Result.failure(Exception("Connection timeout. Please try again."))
        } catch (e: Exception) {
            Result.failure(Exception(e.message ?: "Registration failed. Please try again."))
        }
    }
    
    suspend fun login(request: LoginRequest): Result<AuthResponse> {
        return try {
            val response = api.login(request)
            if (response.isSuccessful) {
                response.body()?.let { authResponse ->
                    tokenManager.saveToken(authResponse.token)
                    // Convert flat AuthResponse to User object
                    val user = User(
                        id = authResponse.id,
                        username = authResponse.username,
                        email = authResponse.email,
                        fullName = authResponse.fullName,
                        phoneNumber = null,
                        enabled = true
                    )
                    tokenManager.saveUser(user)
                    ApiClient.setToken(authResponse.token)
                    Result.success(authResponse)
                } ?: Result.failure(Exception("Empty response"))
            } else {
                val errorMsg = ErrorParser.parseErrorBody(response.errorBody()?.string())
                Result.failure(Exception(errorMsg))
            }
        } catch (e: java.net.ConnectException) {
            Result.failure(Exception("Cannot connect to server. Please check if backend is running."))
        } catch (e: java.net.SocketTimeoutException) {
            Result.failure(Exception("Connection timeout. Please try again."))
        } catch (e: Exception) {
            Result.failure(Exception(e.message ?: "Login failed. Please try again."))
        }
    }
    
    suspend fun logout(): Result<String> {
        return try {
            api.logout()
            tokenManager.clearAll()
            ApiClient.setToken(null)
            Result.success("Logged out successfully")
        } catch (e: Exception) {
            tokenManager.clearAll()
            ApiClient.setToken(null)
            Result.success("Logged out")
        }
    }
    
    suspend fun getProfile(): Result<User> {
        return try {
            val response = api.getProfile()
            if (response.isSuccessful) {
                response.body()?.let { userResponse ->
                    val user = User(
                        id = userResponse.id,
                        username = userResponse.username,
                        email = userResponse.email,
                        fullName = userResponse.fullName,
                        phoneNumber = userResponse.phoneNumber,
                        enabled = true
                    )
                    tokenManager.saveUser(user)
                    Result.success(user)
                } ?: Result.failure(Exception("Empty response"))
            } else {
                val errorMsg = ErrorParser.parseErrorBody(response.errorBody()?.string())
                Result.failure(Exception(errorMsg))
            }
        } catch (e: java.net.ConnectException) {
            Result.failure(Exception("Cannot connect to server. Please check if backend is running."))
        } catch (e: java.net.SocketTimeoutException) {
            Result.failure(Exception("Connection timeout. Please try again."))
        } catch (e: Exception) {
            Result.failure(Exception(e.message ?: "Failed to get profile. Please try again."))
        }
    }
    
    suspend fun updateProfile(request: UpdateProfileRequest): Result<User> {
        return try {
            val response = api.updateProfile(request)
            if (response.isSuccessful) {
                response.body()?.let { userResponse ->
                    val user = User(
                        id = userResponse.id,
                        username = userResponse.username,
                        email = userResponse.email,
                        fullName = userResponse.fullName,
                        phoneNumber = userResponse.phoneNumber,
                        enabled = true
                    )
                    tokenManager.saveUser(user)
                    Result.success(user)
                } ?: Result.failure(Exception("Empty response"))
            } else {
                val errorMsg = ErrorParser.parseErrorBody(response.errorBody()?.string())
                Result.failure(Exception(errorMsg))
            }
        } catch (e: java.net.ConnectException) {
            Result.failure(Exception("Cannot connect to server. Please check if backend is running."))
        } catch (e: java.net.SocketTimeoutException) {
            Result.failure(Exception("Connection timeout. Please try again."))
        } catch (e: Exception) {
            Result.failure(Exception(e.message ?: "Failed to update profile. Please try again."))
        }
    }
    
    suspend fun initializeToken() {
        val token = tokenManager.getToken().first()
        ApiClient.setToken(token)
    }
    
    fun getUserFlow() = tokenManager.getUser()
    
    suspend fun isLoggedIn(): Boolean {
        return tokenManager.getToken().first() != null
    }
}

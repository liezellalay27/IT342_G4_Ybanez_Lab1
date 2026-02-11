package com.auth.mobile.data.repository

import com.auth.mobile.data.local.TokenManager
import com.auth.mobile.data.model.*
import com.auth.mobile.data.remote.ApiClient
import kotlinx.coroutines.flow.first

class AuthRepository(private val tokenManager: TokenManager) {
    
    private val api = ApiClient.authApi
    
    suspend fun register(request: RegisterRequest): Result<String> {
        return try {
            val response = api.register(request)
            if (response.isSuccessful) {
                Result.success(response.body()?.message ?: "Registration successful")
            } else {
                Result.failure(Exception(response.errorBody()?.string() ?: "Registration failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun login(request: LoginRequest): Result<AuthResponse> {
        return try {
            val response = api.login(request)
            if (response.isSuccessful) {
                response.body()?.let { authResponse ->
                    tokenManager.saveToken(authResponse.token)
                    tokenManager.saveUser(authResponse.user)
                    ApiClient.setToken(authResponse.token)
                    Result.success(authResponse)
                } ?: Result.failure(Exception("Empty response"))
            } else {
                Result.failure(Exception(response.errorBody()?.string() ?: "Login failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
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
                response.body()?.user?.let { user ->
                    tokenManager.saveUser(user)
                    Result.success(user)
                } ?: Result.failure(Exception("Empty response"))
            } else {
                Result.failure(Exception(response.errorBody()?.string() ?: "Failed to get profile"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun updateProfile(request: UpdateProfileRequest): Result<User> {
        return try {
            val response = api.updateProfile(request)
            if (response.isSuccessful) {
                response.body()?.user?.let { user ->
                    tokenManager.saveUser(user)
                    Result.success(user)
                } ?: Result.failure(Exception("Empty response"))
            } else {
                Result.failure(Exception(response.errorBody()?.string() ?: "Failed to update profile"))
            }
        } catch (e: Exception) {
            Result.failure(e)
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

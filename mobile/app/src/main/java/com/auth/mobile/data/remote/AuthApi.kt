package com.auth.mobile.data.remote

import com.auth.mobile.data.model.*
import retrofit2.Response
import retrofit2.http.*

interface AuthApi {
    
    @POST("auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<MessageResponse>
    
    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<AuthResponse>
    
    @POST("auth/logout")
    suspend fun logout(): Response<MessageResponse>
    
    @GET("auth/profile")
    suspend fun getProfile(): Response<UserResponse>
    
    @PUT("auth/profile")
    suspend fun updateProfile(
        @Body request: UpdateProfileRequest
    ): Response<UserResponse>
}

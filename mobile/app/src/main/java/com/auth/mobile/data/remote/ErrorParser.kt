package com.auth.mobile.data.remote

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException

data class ErrorResponse(
    val message: String? = null,
    val success: Boolean? = null,
    val error: String? = null
)

object ErrorParser {
    private val gson = Gson()
    
    fun parseErrorBody(errorBody: String?): String {
        if (errorBody.isNullOrBlank()) {
            return "An error occurred"
        }
        
        return try {
            // Try to parse as JSON error response
            val errorResponse = gson.fromJson(errorBody, ErrorResponse::class.java)
            errorResponse.message ?: errorResponse.error ?: errorBody
        } catch (e: JsonSyntaxException) {
            // If not JSON, return the raw error body, but clean it up
            errorBody.take(200) // Limit length
        } catch (e: Exception) {
            "An error occurred"
        }
    }
}

package com.auth.mobile.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.auth.mobile.data.model.UpdateProfileRequest
import com.auth.mobile.data.model.User
import com.auth.mobile.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class EditProfileUiState(
    val username: String = "",
    val email: String = "",
    val fullName: String = "",
    val phoneNumber: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val successMessage: String? = null
)

class EditProfileViewModel(private val repository: AuthRepository) : ViewModel() {
    
    private val _uiState = MutableStateFlow(EditProfileUiState())
    val uiState: StateFlow<EditProfileUiState> = _uiState.asStateFlow()
    
    init {
        loadUser()
    }
    
    private fun loadUser() {
        viewModelScope.launch {
            repository.getUserFlow().collect { user ->
                user?.let {
                    _uiState.value = _uiState.value.copy(
                        username = it.username,
                        email = it.email,
                        fullName = it.fullName ?: "",
                        phoneNumber = it.phoneNumber ?: ""
                    )
                }
            }
        }
    }
    
    fun onUsernameChange(username: String) {
        _uiState.value = _uiState.value.copy(username = username, errorMessage = null)
    }
    
    fun onEmailChange(email: String) {
        _uiState.value = _uiState.value.copy(email = email, errorMessage = null)
    }
    
    fun onFullNameChange(fullName: String) {
        _uiState.value = _uiState.value.copy(fullName = fullName)
    }
    
    fun onPhoneNumberChange(phoneNumber: String) {
        _uiState.value = _uiState.value.copy(phoneNumber = phoneNumber)
    }
    
    fun updateProfile() {
        val state = _uiState.value
        
        if (state.username.isBlank() || state.email.isBlank()) {
            _uiState.value = state.copy(errorMessage = "Username and email are required")
            return
        }
        
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(state.email).matches()) {
            _uiState.value = state.copy(errorMessage = "Please enter a valid email")
            return
        }
        
        viewModelScope.launch {
            _uiState.value = state.copy(isLoading = true, errorMessage = null)
            
            val result = repository.updateProfile(
                UpdateProfileRequest(
                    username = state.username,
                    email = state.email,
                    fullName = state.fullName.takeIf { it.isNotBlank() },
                    phoneNumber = state.phoneNumber.takeIf { it.isNotBlank() }
                )
            )
            
            result.fold(
                onSuccess = {
                    _uiState.value = state.copy(
                        isLoading = false,
                        successMessage = "Profile updated successfully!"
                    )
                },
                onFailure = { error ->
                    _uiState.value = state.copy(
                        isLoading = false,
                        errorMessage = error.message ?: "Failed to update profile"
                    )
                }
            )
        }
    }
    
    fun clearMessages() {
        _uiState.value = _uiState.value.copy(errorMessage = null, successMessage = null)
    }
}

package com.auth.mobile.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.auth.mobile.data.model.User
import com.auth.mobile.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class DashboardUiState(
    val user: User? = null,
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val isLoggedOut: Boolean = false
)

class DashboardViewModel(private val repository: AuthRepository) : ViewModel() {
    
    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()
    
    init {
        loadUser()
    }
    
    private fun loadUser() {
        viewModelScope.launch {
            repository.getUserFlow().collect { user ->
                _uiState.value = _uiState.value.copy(
                    user = user,
                    isLoading = false
                )
            }
        }
    }
    
    fun logout() {
        viewModelScope.launch {
            repository.logout()
            _uiState.value = _uiState.value.copy(isLoggedOut = true)
        }
    }
}

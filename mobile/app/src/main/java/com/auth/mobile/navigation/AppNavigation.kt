package com.auth.mobile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.auth.mobile.data.local.TokenManager
import com.auth.mobile.data.repository.AuthRepository
import com.auth.mobile.ui.auth.*
import com.auth.mobile.ui.dashboard.DashboardScreen
import com.auth.mobile.ui.dashboard.DashboardViewModel
import com.auth.mobile.ui.profile.EditProfileScreen
import com.auth.mobile.ui.profile.EditProfileViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    startDestination: String,
    tokenManager: TokenManager
) {
    val repository = AuthRepository(tokenManager)
    
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Login.route) {
            val viewModel = LoginViewModel(repository)
            LoginScreen(
                viewModel = viewModel,
                onNavigateToRegister = {
                    navController.navigate(Screen.Register.route)
                },
                onLoginSuccess = {
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }
        
        composable(Screen.Register.route) {
            val viewModel = RegisterViewModel(repository)
            RegisterScreen(
                viewModel = viewModel,
                onNavigateToLogin = {
                    navController.popBackStack()
                }
            )
        }
        
        composable(Screen.Dashboard.route) {
            val viewModel = DashboardViewModel(repository)
            DashboardScreen(
                viewModel = viewModel,
                onNavigateToEditProfile = {
                    navController.navigate(Screen.EditProfile.route)
                },
                onLogout = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
        
        composable(Screen.EditProfile.route) {
            val viewModel = EditProfileViewModel(repository)
            EditProfileScreen(
                viewModel = viewModel,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

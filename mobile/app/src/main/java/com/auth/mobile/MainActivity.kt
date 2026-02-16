package com.auth.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.auth.mobile.data.local.TokenManager
import com.auth.mobile.data.repository.AuthRepository
import com.auth.mobile.navigation.AppNavigation
import com.auth.mobile.navigation.Screen
import com.auth.mobile.ui.theme.AuthMobileAppTheme
import kotlinx.coroutines.flow.first

class MainActivity : ComponentActivity() {
    private lateinit var tokenManager: TokenManager
    private lateinit var repository: AuthRepository
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        tokenManager = TokenManager(this)
        repository = AuthRepository(tokenManager)
        
        setContent {
            AuthMobileAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var startDestination by remember { mutableStateOf<String?>(null) }
                    
                    LaunchedEffect(Unit) {
                        repository.initializeToken()
                        val isLoggedIn = repository.isLoggedIn()
                        startDestination = if (isLoggedIn) {
                            Screen.Dashboard.route
                        } else {
                            Screen.Login.route
                        }
                    }
                    
                    startDestination?.let { destination ->
                        val navController = rememberNavController()
                        AppNavigation(
                            navController = navController,
                            startDestination = destination,
                            tokenManager = tokenManager
                        )
                    }
                }
            }
        }
    }
}

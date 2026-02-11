package com.auth.mobile.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Dashboard : Screen("dashboard")
    object EditProfile : Screen("edit_profile")
}

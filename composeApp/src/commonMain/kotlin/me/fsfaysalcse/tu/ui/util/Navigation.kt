package me.fsfaysalcse.tu.ui.util

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Signup : Screen("signup")
    object Home : Screen("home/{email}") {
        fun createRoute(email: String): String = "home/$email"
    }
}
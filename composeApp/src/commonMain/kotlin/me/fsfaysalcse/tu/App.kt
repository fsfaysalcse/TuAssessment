package me.fsfaysalcse.tu

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.fsfaysalcse.tu.ui.screens.HomeScreen
import me.fsfaysalcse.tu.ui.screens.LoginScreen
import me.fsfaysalcse.tu.ui.screens.SignupScreen
import me.fsfaysalcse.tu.ui.theme.TuAssessmentTheme
import me.fsfaysalcse.tu.ui.util.Screen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    TuAssessmentTheme {

        val navController: NavHostController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Screen.Login.route,
            modifier = Modifier.fillMaxSize()
        ) {
            composable(route = Screen.Login.route) {
                LoginScreen(navController = navController)
            }

            composable(route = Screen.Signup.route) {
                SignupScreen(navController = navController)
            }

            composable(route = Screen.Home.route) {
                HomeScreen(navController = navController)
            }
        }
    }
}
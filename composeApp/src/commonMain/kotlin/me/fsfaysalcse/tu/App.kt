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
import me.fsfaysalcse.tu.ui.viewModels.UserViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    val viewModel = koinViewModel<UserViewModel>()

    TuAssessmentTheme {

        val navController: NavHostController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Screen.Login.route,
            modifier = Modifier.fillMaxSize()
        ) {
            composable(route = Screen.Login.route) {
                LoginScreen(
                    navController = navController,
                    viewModel = viewModel
                )
            }

            composable(route = Screen.Signup.route) {
                SignupScreen(
                    navController = navController,
                    viewModel = viewModel
                )
            }

            composable(route = Screen.Home.route) {
                HomeScreen(navController = navController)
            }
        }
    }
}
package me.fsfaysalcse.tu

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.fsfaysalcse.tu.ui.screens.HomeScreen
import me.fsfaysalcse.tu.ui.screens.LoginScreen
import me.fsfaysalcse.tu.ui.screens.SignupScreen
import me.fsfaysalcse.tu.ui.screens.SplashScreen
import me.fsfaysalcse.tu.ui.theme.TuAssessmentTheme
import me.fsfaysalcse.tu.ui.util.Screen
import me.fsfaysalcse.tu.ui.viewModels.UserViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App(prefs: DataStore<Preferences>) {
    val viewModel = koinViewModel<UserViewModel>()
    val navController: NavHostController = rememberNavController()


    TuAssessmentTheme {
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.fillMaxSize()
        ) {

            composable(route = Screen.Splash.route) {
                SplashScreen(
                    navController = navController,
                    prefs = prefs
                )
            }

            composable(route = Screen.Login.route) {
                LoginScreen(
                    navController = navController,
                    viewModel = viewModel,
                    prefs = prefs
                )
            }

            composable(route = Screen.Signup.route) {
                SignupScreen(
                    navController = navController,
                    viewModel = viewModel,
                    prefs = prefs
                )
            }

            composable(route = Screen.Home.route) {
                HomeScreen(
                    navController = navController,
                    viewModel = viewModel,
                    prefs = prefs
                )
            }
        }
    }
}

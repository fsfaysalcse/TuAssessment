package me.fsfaysalcse.tu.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.navigation.NavHostController
import com.plusmobileapps.konnectivity.Konnectivity
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import me.fsfaysalcse.tu.data.database.LOGGED_IN_USER_EMAIL
import me.fsfaysalcse.tu.data.models.User
import me.fsfaysalcse.tu.ui.theme.TuMain
import me.fsfaysalcse.tu.ui.uiStates.HomeUiState
import me.fsfaysalcse.tu.ui.util.CountdownText
import me.fsfaysalcse.tu.ui.util.Screen
import me.fsfaysalcse.tu.ui.util.getCurrentDateTime
import me.fsfaysalcse.tu.ui.viewModels.UserViewModel
import org.jetbrains.compose.resources.painterResource
import tuassessment.composeapp.generated.resources.Res
import tuassessment.composeapp.generated.resources.compose_multiplatform

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: UserViewModel,
    prefs: DataStore<Preferences>
) {

    val konnectivity = remember { Konnectivity() }
    val isConnected by konnectivity.isConnectedState.collectAsState()

    val coroutineScope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    var userInfo by remember { mutableStateOf<User?>(null) }

    val loggedInUserEmail = prefs.data
        .map { it[stringPreferencesKey(LOGGED_IN_USER_EMAIL)] }
        .collectAsState(initial = null).value

    LaunchedEffect(loggedInUserEmail) {
        loggedInUserEmail?.let { viewModel.getUserByEmail(email = it) }
    }

    val homeUiState = viewModel.homeUiState.value

    LaunchedEffect(homeUiState) {
        when (homeUiState) {
            is HomeUiState.Success -> userInfo = homeUiState.user
            is HomeUiState.Error -> snackBarHostState.showSnackbar(homeUiState.message)
            else -> Unit
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(Res.drawable.compose_multiplatform),
                    contentDescription = "App Logo",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
                    modifier = Modifier
                        .size(100.dp)
                        .padding(bottom = 32.dp)
                )

                Text(
                    text = "Welcome".uppercase(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 10.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = "${userInfo?.name}\n${userInfo?.email}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Last Logged In: ${getCurrentDateTime()}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(30.dp))

                CountdownText(initialCountdownValue = 60) {
                    coroutineScope.launch {
                        if (isConnected) {
                            snackBarHostState.showSnackbar("New Information has been sent to server")
                        } else {
                            snackBarHostState.showSnackbar("No Internet 202")
                        }

                    }
                }

                Spacer(modifier = Modifier.height(100.dp))

                Button(
                    onClick = {
                        coroutineScope.launch {
                            // Clear the preferences and user state
                            prefs.edit { it.clear() }

                             // Navigate to the Login screen and clear the navigation stack
                            navController.navigate(Screen.Splash.route) {
                                popUpTo(Screen.Home.route) {
                                    inclusive = true
                                }
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = TuMain)
                ) {
                    Text(
                        text = "Logout".uppercase(),
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 5.sp
                    )
                }
            }
        }
    )
}


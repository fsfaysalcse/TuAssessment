package me.fsfaysalcse.tu

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import me.fsfaysalcse.tu.ui.screens.HomeScreen
import me.fsfaysalcse.tu.ui.screens.LoginScreen
import me.fsfaysalcse.tu.ui.screens.SignupScreen
import me.fsfaysalcse.tu.ui.theme.TuAssessmentTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import tuassessment.composeapp.generated.resources.Res
import tuassessment.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    TuAssessmentTheme {
       /*var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text(
                        text = "Compose: $greeting"
                    )
                }
            }
        }*/

        HomeScreen()
    }
}
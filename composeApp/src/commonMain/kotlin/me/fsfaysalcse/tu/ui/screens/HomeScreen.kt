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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import me.fsfaysalcse.tu.ui.theme.TuMain
import me.fsfaysalcse.tu.ui.util.Screen
import org.jetbrains.compose.resources.painterResource
import tuassessment.composeapp.generated.resources.Res
import tuassessment.composeapp.generated.resources.compose_multiplatform

@Composable
fun HomeScreen(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // App Logo
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

        Spacer(modifier = Modifier.fillMaxWidth().height(30.dp))

        Text(
            text = "Faysal Hossain",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))

        Text(
            text = "Last Logged In : 03 Dec 2025 - 10:30",
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(modifier = Modifier.fillMaxWidth().height(100.dp))
        // Sign-in Button
        Button(
            onClick = {
                navController.navigate(Screen.Home.route)
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = TuMain
            )
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

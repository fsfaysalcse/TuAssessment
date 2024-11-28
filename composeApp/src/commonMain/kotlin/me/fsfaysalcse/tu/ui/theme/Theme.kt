package me.fsfaysalcse.tu.ui.theme


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = TuMain,
    secondary = RedGrey80,
    tertiary = Yellow60
)

private val LightColorScheme = lightColorScheme(
    primary = Red40,
    secondary = RedGry40,
    tertiary = Tit40
)

@Composable
fun TuAssessmentTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colorScheme = when (!darkTheme) {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
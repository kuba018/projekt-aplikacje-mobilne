package com.example.projekt_aplikacje_mobilne.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = Blue80,
    secondary = Teal80,
    tertiary = Coral80
)

private val DarkColors = darkColorScheme(
    primary = Blue40,
    secondary = Teal40,
    tertiary = Coral40
)

@Composable
fun ProjektAplikacjeMobilneTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
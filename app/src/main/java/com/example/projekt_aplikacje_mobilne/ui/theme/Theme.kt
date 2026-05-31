package com.example.projekt_aplikacje_mobilne.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import com.example.projekt_aplikacje_mobilne.model.FontScaleOption
import com.example.projekt_aplikacje_mobilne.model.ThemeMode

private val LightColors = lightColorScheme(
    primary = Blue40,
    secondary = Teal40,
    tertiary = Coral40
)

private val DarkColors = darkColorScheme(
    primary = Blue80,
    secondary = Teal80,
    tertiary = Coral80
)

@Composable
fun ProjektAplikacjeMobilneTheme(
    themeMode: ThemeMode = ThemeMode.SYSTEM,
    fontScale: FontScaleOption = FontScaleOption.NORMAL,
    content: @Composable () -> Unit
) {
    val isDark = when (themeMode) {
        ThemeMode.LIGHT -> false
        ThemeMode.DARK -> true
        ThemeMode.SYSTEM -> isSystemInDarkTheme()
    }

    val density = LocalDensity.current

    CompositionLocalProvider(
        LocalDensity provides Density(
            density = density.density,
            fontScale = fontScale.scale
        )
    ) {
        MaterialTheme(
            colorScheme = if (isDark) DarkColors else LightColors,
            typography = Typography,
            content = content
        )
    }
}
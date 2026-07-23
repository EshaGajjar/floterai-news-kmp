package com.app.kmp_app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = Color.White,
    surface = Color.White,
    surfaceVariant = Color.White
)

@Immutable
data class CustomColorsPalette(
    val whiteColor: Color = Color.Unspecified,
    val titleBlack: Color = Color.Unspecified,
    val primaryColor: Color = Color.Unspecified,
    val actionPrimaryColor: Color = Color.Unspecified,
)

val LocalCustomColorsPalette = staticCompositionLocalOf { CustomColorsPalette() }

val OnLightCustomColorsPalette = CustomColorsPalette(
    whiteColor = Color.White,
    titleBlack = C_3F3F46,
    primaryColor = C_51A8FF,
    actionPrimaryColor = C_60A5FA
)

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    val colors = LightColorScheme
    val customColorsPalette = OnLightCustomColorsPalette

    CompositionLocalProvider(
        LocalCustomColorsPalette provides customColorsPalette
    ) {
        MaterialTheme(
            colorScheme = colors,
            typography = Typography,
            content = content
        )
    }
}

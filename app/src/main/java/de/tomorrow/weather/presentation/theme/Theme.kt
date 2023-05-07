package de.tomorrow.weather.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = BlueCatalina, secondary = WhiteLilac, tertiary = TealDeep
)

@Composable
fun TomorrowWeatherTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme, typography = Typography, content = content
    )
}
package org.example.mood

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val MoodColorScheme = darkColorScheme(
    primary = Color(0xFF8B5CF6),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFF221630),
    onPrimaryContainer = Color(0xFFEAE6FF),
    secondary = Color(0xFF22D3EE),
    onSecondary = Color(0xFF071018),
    secondaryContainer = Color(0xFF0D2A33),
    onSecondaryContainer = Color(0xFFC7F7FF),
    tertiary = Color(0xFFEC4899),
    onTertiary = Color(0xFF1A0A12),
    background = Color(0xFF0A0B12),
    onBackground = Color(0xFFE9E8F0),
    surface = Color(0xFF101420),
    onSurface = Color(0xFFDDE0F2),
    surfaceVariant = Color(0xFF1B2133),
    onSurfaceVariant = Color(0xFFBAC1D4),
    outline = Color(0xFF3C4A68),
)

private val MoodTypography = Typography()
private val MoodShapes = Shapes()

@Composable
fun MoodTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MoodColorScheme,
        typography = MoodTypography,
        shapes = MoodShapes,
        content = content,
    )
}


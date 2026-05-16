package org.example.mood

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

object MoodTokens {
    val backgroundGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF0A0B12),
            Color(0xFF0D0E1A),
            Color(0xFF121527),
        ),
    )

    val glassSurface = Color(0xFF151A2A).copy(alpha = 0.78f)
    val glassStroke = Color(0xFF7C8FB8).copy(alpha = 0.35f)
    val glowPrimary = Color(0xFF8B5CF6).copy(alpha = 0.6f)
    val glowSecondary = Color(0xFF22D3EE).copy(alpha = 0.6f)
}

@Composable
fun MoodBackground(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Box(modifier = modifier.background(MoodTokens.backgroundGradient)) {
        content()
    }
}


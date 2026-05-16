package org.example.mood

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

private enum class Screen {
    Start,
    Rating,
}

@Composable
@Preview
fun App() {
    MoodTheme {
        MoodBackground(modifier = Modifier.fillMaxSize()) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onBackground,
            ) {
                var screen by remember { mutableStateOf(Screen.Start) }
                when (screen) {
                    Screen.Start -> StartScreen(onStart = { screen = Screen.Rating })
                    Screen.Rating -> StartScreen(onStart = { screen = Screen.Start })
                }
            }
        }
    }
}
package org.example.mood

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

private enum class Screen {
    Start,
    Rating,
    Stats,
}

@Composable
@Preview
fun App() {
    MoodTheme {
        MoodBackground(modifier = Modifier.fillMaxSize()) {
            Surface(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(0.6f),
                color = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onBackground,
            ) {
                var screen by remember { mutableStateOf(Screen.Start) }
                var ratings by remember { mutableStateOf<Map<Int, Set<String>>>(emptyMap()) }

                val stats = remember(ratings) {
                    val totalTracks = ratings.size.coerceAtLeast(1)
                    Emotions.map { emotion ->
                        val count = ratings.values.count { it.contains(emotion.id) }
                        EmotionStatUi(
                            label = emotion.label,
                            value = count.toFloat() / totalTracks.toFloat(),
                            color = emotion.color,
                        )
                    }
                }

                when (screen) {
                    Screen.Start -> StartScreen(onStart = { screen = Screen.Rating })
                    Screen.Rating -> AudioRatingScreen(
                        onComplete = {
                            ratings = it
                            screen = Screen.Stats
                        },
                    )
                    Screen.Stats -> StatsScreen(
                        stats = stats,
                        onRestart = {
                            ratings = emptyMap()
                            screen = Screen.Start
                        },
                    )
                }
            }
        }
    }
}
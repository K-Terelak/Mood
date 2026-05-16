package org.example.mood

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import mood.shared.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource

private data class TrackUi(
    val title: String,
    val source: String,
)

private data class EmotionUi(
    val label: String,
    val icon: DrawableResource,
    val color: androidx.compose.ui.graphics.Color,
)

private fun formatTime(seconds: Double): String {
    val safe = seconds.takeIf { it.isFinite() && it >= 0.0 } ?: 0.0
    val totalSeconds = safe.toInt()
    val minutes = totalSeconds / 60
    val secs = totalSeconds % 60
    val mm = minutes.toString().padStart(2, '0')
    val ss = secs.toString().padStart(2, '0')
    return "$mm:$ss"
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AudioRatingScreen(onNext: () -> Unit) {
    val tracks = remember {
        listOf(
            TrackUi("Track 01", "audio/audio_1.mp3"),
            TrackUi("Track 02", "audio/audio_1.mp3"),
            TrackUi("Track 03", "audio/audio_1.mp3"),
            TrackUi("Track 04", "audio/audio_1.mp3"),
            TrackUi("Track 05", "audio/audio_1.mp3"),
            TrackUi("Track 06", "audio/audio_1.mp3"),
            TrackUi("Track 07", "audio/audio_1.mp3"),
            TrackUi("Track 08", "audio/audio_1.mp3"),
            TrackUi("Track 09", "audio/audio_1.mp3"),
            TrackUi("Track 10", "audio/audio_1.mp3"),
        )
    }
    val emotions = listOf(
        EmotionUi("Radosc", Res.drawable.ic_1, androidx.compose.ui.graphics.Color(0xFFF59E0B)),
        EmotionUi("Spokoj", Res.drawable.ic_2, androidx.compose.ui.graphics.Color(0xFF22D3EE)),
        EmotionUi("Ciekawosc", Res.drawable.ic_3, androidx.compose.ui.graphics.Color(0xFF60A5FA)),
        EmotionUi("Smutek", Res.drawable.ic_4, androidx.compose.ui.graphics.Color(0xFF818CF8)),
        EmotionUi("Strach", Res.drawable.ic_5, androidx.compose.ui.graphics.Color(0xFFA78BFA)),
        EmotionUi("Zlosc", Res.drawable.ic_6, androidx.compose.ui.graphics.Color(0xFFF43F5E)),
        EmotionUi("Zaskoczenie", Res.drawable.ic_7, androidx.compose.ui.graphics.Color(0xFFFB7185)),
        EmotionUi("Nostalgia", Res.drawable.ic_8, androidx.compose.ui.graphics.Color(0xFF34D399)),
        EmotionUi("Odraza", Res.drawable.ic_9, androidx.compose.ui.graphics.Color(0xFF10B981)),
    )

    val controller = remember { createAudioController() }
    var currentIndex by remember { mutableStateOf(0) }
    var currentTime by remember { mutableStateOf(0.0) }
    var duration by remember { mutableStateOf(0.0) }
    var isPlaying by remember { mutableStateOf(false) }
    val selections = remember { mutableStateMapOf<Int, Set<String>>() }

    LaunchedEffect(currentIndex) {
        controller.load(tracks[currentIndex].source)
        controller.pause()
        currentTime = 0.0
        duration = 0.0
        isPlaying = false
    }

    LaunchedEffect(controller) {
        controller.setOnEnded {
            isPlaying = false
            currentTime = duration
        }
    }

    LaunchedEffect(controller, currentIndex) {
        while (isActive) {
            currentTime = controller.getCurrentTime()
            duration = controller.getDuration()
            isPlaying = controller.isPlaying()
            delay(250)
        }
    }

    val progress = (currentIndex + 1).toFloat() / tracks.size.toFloat()
    val selectedForTrack = selections[currentIndex].orEmpty()
    val canContinue = selectedForTrack.isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ProgressHeader(current = currentIndex + 1, total = tracks.size, progress = progress)
        AudioPlayerCard(
            timeText = "${formatTime(currentTime)}/${formatTime(duration)}",
            playIcon = Res.drawable.ic_play,
            stopIcon = Res.drawable.ic_pause,
            isPlaying = isPlaying,
            onPlayPause = {
                if (controller.isPlaying()) {
                    controller.pause()
                } else {
                    controller.play()
                }
                isPlaying = controller.isPlaying()
            },
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SectionTitle(text = "Wybierz emocje")
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.fillMaxWidth(0.5f),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
            ) {
                items(emotions) { emotion ->
                    val isSelected = selectedForTrack.contains(emotion.label)
                    EmotionChip(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f),
                        label = emotion.label,
                        icon = emotion.icon,
                        color = emotion.color,
                        selected = isSelected,
                        onClick = {
                            val updated = selectedForTrack.toMutableSet()
                            if (isSelected) {
                                updated.remove(emotion.label)
                            } else {
                                updated.add(emotion.label)
                            }
                            selections[currentIndex] = updated
                        },
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        PrimaryButton(
            text = "Dalej",
            enabled = canContinue,
            onClick = {
                controller.stop()
                if (currentIndex < tracks.lastIndex) {
                    currentIndex += 1
                } else {
                    onNext()
                }
            },
        )
    }
}

package org.example.mood

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mood.shared.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource

private data class EmotionUi(
    val label: String,
    val icon: DrawableResource,
    val color: androidx.compose.ui.graphics.Color,
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AudioRatingScreen(onNext: () -> Unit) {
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ProgressHeader(current = 3, total = 10, progress = 0.3f)
        AudioPlayerCard(
            timeText = "00:12/00:45",
            playIcon = Res.drawable.ic_play,
            stopIcon = Res.drawable.ic_pause,
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
                    EmotionChip(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f),
                        label = emotion.label,
                        icon = emotion.icon,
                        color = emotion.color,
                        selected = false,
                        onClick = {},
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        PrimaryButton(text = "Dalej", onClick = onNext)
    }
}

package org.example.mood

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

private data class EmotionStatUi(
    val label: String,
    val value: Float,
)

@Composable
fun StatsScreen(onRestart: () -> Unit) {
    val stats = listOf(
        EmotionStatUi("Radosc", 0.34f),
        EmotionStatUi("Spokoj", 0.18f),
        EmotionStatUi("Ciekawosc", 0.16f),
        EmotionStatUi("Smutek", 0.1f),
        EmotionStatUi("Strach", 0.08f),
        EmotionStatUi("Zlosc", 0.06f),
        EmotionStatUi("Zaskoczenie", 0.04f),
        EmotionStatUi("Nostalgia", 0.03f),
        EmotionStatUi("Odraza", 0.01f),
    )

    Column(
        modifier = Modifier
            .fillMaxWidth(0.7f)
            .padding(vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SectionTitle(text = "Podsumowanie")
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            stats.forEach { stat ->
                EmotionProgressBar(
                    label = stat.label,
                    progress = stat.value,
                )
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        PrimaryButton(text = "Wroc", onClick = onRestart)
    }
}


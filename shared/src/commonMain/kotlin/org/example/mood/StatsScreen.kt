package org.example.mood

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class EmotionStatUi(
    val label: String,
    val value: Float,
    val color: Color,
)

@Composable
fun StatsScreen(stats: List<EmotionStatUi>, onRestart: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(60.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SectionTitle(text = "Podsumowanie")
        EmotionSpiderPlot(stats = stats)
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            stats.forEach { stat ->
                EmotionProgressBar(
                    label = stat.label,
                    progress = stat.value,
                    color = stat.color,
                )
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        PrimaryButton(text = "Wroc", onClick = onRestart)
    }
}

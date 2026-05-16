package org.example.mood

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
fun ProgressHeader(current: Int, total: Int, progress: Float) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "$current / $total",
                style = MaterialTheme.typography.titleMedium
            )
        }
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier.height(6.dp),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
            drawStopIndicator = {}
        )
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun AudioPlayerCard(
    timeText: String,
    playIcon: DrawableResource,
    stopIcon: DrawableResource,
    isPlaying: Boolean = false,
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MoodTokens.glassSurface),
        border = BorderStroke(1.dp, MoodTokens.glassStroke),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier,
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Surface(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                shape = RoundedCornerShape(20.dp),
            ) {
                Box(
                    modifier = Modifier.size(104.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        painter = painterResource(if (isPlaying) stopIcon else playIcon),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(56.dp),
                    )
                }
            }
            Text(text = timeText, style = MaterialTheme.typography.labelLarge)
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun EmotionChip(
    label: String,
    icon: DrawableResource,
    color: Color,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val alpha = if (selected) 0.85f else 0.45f
    Surface(
        onClick = onClick,
        color = color.copy(alpha = alpha),
        shape = RoundedCornerShape(18.dp),
        border = BorderStroke(1.dp, color.copy(alpha = 0.8f)),
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.size(28.dp),
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = label, style = MaterialTheme.typography.labelLarge)
        }
    }
}

@Composable
fun PrimaryButton(text: String, onClick: () -> Unit) {
    Button(
        modifier = Modifier.fillMaxWidth().height(52.dp),
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
    ) {
        Text(text = text, style = MaterialTheme.typography.titleMedium)
    }
}

@Composable
fun SectionTitle(text: String) {
    Text(text = text, style = MaterialTheme.typography.titleMedium)
}

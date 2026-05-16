package org.example.mood

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

@Composable
fun EmotionSpiderPlot(
    stats: List<EmotionStatUi>,
    modifier: Modifier = Modifier,
) {
    if (stats.isEmpty()) return

    val fullCircle = (2f * PI).toFloat()
    val stepRadians = fullCircle / stats.size
    val angles = remember(stats.size) {
        List(stats.size) { index -> -PI.toFloat() / 2f + index * stepRadians }
    }
    val fillColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.75f)
    val labelColor = MaterialTheme.colorScheme.onSurface

    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp),
    ) {
        val widthPx = constraints.maxWidth.toFloat()
        val heightPx = constraints.maxHeight.toFloat()
        val center = Offset(widthPx / 2f, heightPx / 2f)
        val radius = min(widthPx, heightPx) * 0.4f
        val labelRadius = radius * 1.55f
        val lineColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.18f)

        Canvas(
            modifier = Modifier.fillMaxWidth()
                .height(220.dp)
        ) {
            val path = Path()
            val tickColor = lineColor
            val tickLength = 8f
            val tickFractions = listOf(0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1f)
            angles.forEachIndexed { index, angle ->
                val value = stats[index].value.coerceIn(0f, 1f)
                val point = Offset(
                    x = center.x + cos(angle) * radius * value,
                    y = center.y + sin(angle) * radius * value,
                )
                val end = Offset(
                    x = center.x + cos(angle) * radius,
                    y = center.y + sin(angle) * radius,
                )
                val normal = Offset(-sin(angle), cos(angle))
                drawLine(
                    color = lineColor,
                    start = center,
                    end = end,
                    strokeWidth = 1.5f,
                )
                tickFractions.forEach { fraction ->
                    val tickCenter = Offset(
                        x = center.x + cos(angle) * radius * fraction,
                        y = center.y + sin(angle) * radius * fraction,
                    )
                    drawLine(
                        color = tickColor,
                        start = tickCenter - normal * (tickLength / 2f),
                        end = tickCenter + normal * (tickLength / 2f),
                        strokeWidth = 1.5f,
                    )
                }
                if (index == 0) {
                    path.moveTo(point.x, point.y)
                } else {
                    path.lineTo(point.x, point.y)
                }
            }
            path.close()
            drawPath(path = path, color = fillColor)
        }

        angles.forEachIndexed { index, angle ->
            val labelPosition = Offset(
                x = center.x + cos(angle) * labelRadius,
                y = center.y + sin(angle) * labelRadius,
            )
            Text(
                text = stats[index].label,
                color = labelColor,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.offset { labelPosition.toIntOffset() },
                maxLines = 1,
            )
        }
    }
}

private fun Offset.toIntOffset(): androidx.compose.ui.unit.IntOffset {
    return androidx.compose.ui.unit.IntOffset(x.toInt(), y.toInt())
}


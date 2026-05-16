package org.example.mood

import androidx.compose.ui.graphics.Color
import mood.shared.generated.resources.Res
import mood.shared.generated.resources.ic_1
import mood.shared.generated.resources.ic_2
import mood.shared.generated.resources.ic_3
import mood.shared.generated.resources.ic_4
import mood.shared.generated.resources.ic_5
import mood.shared.generated.resources.ic_6
import mood.shared.generated.resources.ic_7
import mood.shared.generated.resources.ic_8
import mood.shared.generated.resources.ic_9
import org.jetbrains.compose.resources.DrawableResource

internal data class Emotion(
    val id: String,
    val label: String,
    val icon: DrawableResource,
    val color: Color,
)

internal val Emotions = listOf(
    Emotion("radosc", "Radosc", Res.drawable.ic_1, Color(0xFFF59E0B)),
    Emotion("spokoj", "Spokoj", Res.drawable.ic_2, Color(0xFF22D3EE)),
    Emotion("ciekawosc", "Ciekawosc", Res.drawable.ic_3, Color(0xFF60A5FA)),
    Emotion("smutek", "Smutek", Res.drawable.ic_4, Color(0xFF818CF8)),
    Emotion("strach", "Strach", Res.drawable.ic_5, Color(0xFFA78BFA)),
    Emotion("zlosc", "Zlosc", Res.drawable.ic_6, Color(0xFFF43F5E)),
    Emotion("zaskoczenie", "Zaskoczenie", Res.drawable.ic_7, Color(0xFFFB7185)),
    Emotion("nostalgia", "Nostalgia", Res.drawable.ic_8, Color(0xFF34D399)),
    Emotion("odraza", "Odraza", Res.drawable.ic_9, Color(0xFF10B981)),
)


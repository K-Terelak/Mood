package org.example.mood

import androidx.compose.ui.graphics.Color
import mood.shared.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource

internal data class Emotion(
    val id: String,
    val label: String,
    val icon: DrawableResource,
    val color: Color,
)

internal val Emotions = listOf(
    Emotion("radosc", "Radosc", Res.drawable.ic_joy, Color(0xFFF59E0B)),
    Emotion("spokoj", "Spokoj", Res.drawable.ic_calm, Color(0xFF22D3EE)),
    Emotion("ciekawosc", "Ciekawosc", Res.drawable.ic_curiosity, Color(0xFF60A5FA)),
    Emotion("smutek", "Smutek", Res.drawable.ic_sad, Color(0xFF818CF8)),
    Emotion("strach", "Strach", Res.drawable.ic_fear, Color(0xFFA78BFA)),
    Emotion("zlosc", "Zlosc", Res.drawable.ic_anger, Color(0xFFF43F5E)),
    Emotion("zaskoczenie", "Zaskoczenie", Res.drawable.ic_celebration, Color(0xFFFB7185)),
    Emotion("nostalgia", "Nostalgia", Res.drawable.ic_nostalgic, Color(0xFF34D399)),
    Emotion("odraza", "Odraza", Res.drawable.ic_disgust, Color(0xFF10B981)),
)


package com.example.projekt_aplikacje_mobilne.ui.details

import androidx.compose.animation.core.Easing
import androidx.compose.ui.graphics.Color

data class FavoriteAnimationSpec(
    val durationMillis: Int,
    val easing: Easing,
    val minScale: Float,
    val maxScale: Float,
    val inactiveColor: Color,
    val activeColor: Color
)
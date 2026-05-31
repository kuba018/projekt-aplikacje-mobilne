package com.example.projekt_aplikacje_mobilne.ui.details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Immutable
data class FavoriteAnimationSpec(
    val bubbleDurationMillis: Int = 1400,
    val badgeEnterMillis: Int = 420,
    val badgeExitMillis: Int = 260,
    val minBubbleScale: Float = 0.65f,
    val maxBubbleScale: Float = 1.15f,
    val badgeBackgroundColor: Color,
    val badgeContentColor: Color,
    val bubbleColor: Color
)

@Composable
fun rememberFavoriteAnimationSpec(): FavoriteAnimationSpec {
    return FavoriteAnimationSpec(
        badgeBackgroundColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.14f),
        badgeContentColor = MaterialTheme.colorScheme.primary,
        bubbleColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.28f)
    )
}

@Composable
fun AnimatedFavoriteBadge(
    visible: Boolean,
    modifier: Modifier = Modifier,
    text: String = "Dodano do ulubionych",
    spec: FavoriteAnimationSpec = rememberFavoriteAnimationSpec()
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically(
                initialOffsetY = { it / 2 },
                animationSpec = tween(
                    durationMillis = spec.badgeEnterMillis,
                    easing = LinearOutSlowInEasing
                )
            ) + fadeIn(
                animationSpec = tween(durationMillis = spec.badgeEnterMillis)
            ) + scaleIn(
                initialScale = 0.88f,
                animationSpec = tween(
                    durationMillis = spec.badgeEnterMillis,
                    easing = FastOutSlowInEasing
                )
            ),
            exit = slideOutVertically(
                targetOffsetY = { -it / 3 },
                animationSpec = tween(durationMillis = spec.badgeExitMillis)
            ) + fadeOut(
                animationSpec = tween(durationMillis = spec.badgeExitMillis)
            ) + scaleOut(
                targetScale = 0.92f,
                animationSpec = tween(durationMillis = spec.badgeExitMillis)
            )
        ) {
            Box(contentAlignment = Alignment.Center) {
                BubbleParticles(
                    modifier = Modifier
                        .matchParentSize()
                        .padding(bottom = 8.dp),
                    spec = spec
                )

                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = spec.badgeBackgroundColor,
                    contentColor = spec.badgeContentColor,
                    tonalElevation = 0.dp,
                    shadowElevation = 0.dp
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = text,
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun BubbleParticles(
    spec: FavoriteAnimationSpec,
    modifier: Modifier = Modifier
) {
    val transition = rememberInfiniteTransition(label = "bubble_transition")

    val progressA = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = spec.bubbleDurationMillis,
                easing = LinearOutSlowInEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "bubble_a"
    )

    val progressB = transition.animateFloat(
        initialValue = 0.2f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = spec.bubbleDurationMillis + 180,
                easing = LinearOutSlowInEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "bubble_b"
    )

    val progressC = transition.animateFloat(
        initialValue = 0.35f,
        targetValue = 1.35f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = spec.bubbleDurationMillis + 320,
                easing = LinearOutSlowInEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "bubble_c"
    )

    Canvas(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {
        fun drawBubble(
            xFraction: Float,
            baseYFraction: Float,
            progress: Float,
            radius: Float
        ) {
            val safeProgress = progress.coerceIn(0f, 1f)
            val rise = size.height * 0.55f * safeProgress
            val alpha = (1f - safeProgress).coerceIn(0f, 1f)
            val scale =
                spec.minBubbleScale + (spec.maxBubbleScale - spec.minBubbleScale) * safeProgress

            drawCircle(
                color = spec.bubbleColor.copy(alpha = alpha),
                radius = radius * scale,
                center = Offset(
                    x = size.width * xFraction,
                    y = size.height * baseYFraction - rise
                )
            )
        }

        drawBubble(
            xFraction = 0.22f,
            baseYFraction = 0.86f,
            progress = progressA.value,
            radius = 8.dp.toPx()
        )
        drawBubble(
            xFraction = 0.50f,
            baseYFraction = 0.94f,
            progress = progressB.value,
            radius = 6.dp.toPx()
        )
        drawBubble(
            xFraction = 0.78f,
            baseYFraction = 0.88f,
            progress = progressC.value,
            radius = 9.dp.toPx()
        )
    }
}
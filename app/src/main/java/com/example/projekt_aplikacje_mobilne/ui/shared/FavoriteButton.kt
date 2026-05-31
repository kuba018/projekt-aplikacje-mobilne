package com.example.projekt_aplikacje_mobilne.ui.shared

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import com.example.projekt_aplikacje_mobilne.tests.TestTags

@Composable
fun FavoriteButton(
    isFavorite: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scale = remember { Animatable(1f) }
    val rotation = remember { Animatable(0f) }
    val iconSize by animateDpAsState(
        targetValue = if (isFavorite) 28.dp else 24.dp,
        animationSpec = tween(durationMillis = 220),
        label = "favorite_icon_size"
    )

    LaunchedEffect(isFavorite) {
        if (isFavorite) {
            runFavoriteAnimation(scale, rotation)
        } else {
            scale.snapTo(1f)
            rotation.snapTo(0f)
        }
    }

    IconButton(
        onClick = onClick,
        modifier = modifier
            .testTag(TestTags.FAVORITE_BUTTON)
            .semantics {
                stateDescription = if (isFavorite) {
                    "produkt w ulubionych"
                } else {
                    "produkt poza ulubionymi"
                }
            }
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = if (isFavorite) "Usuń z ulubionych" else "Dodaj do ulubionych",
                tint = if (isFavorite) {
                    MaterialTheme.colorScheme.error
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                },
                modifier = Modifier
                    .size(iconSize)
                    .scale(scale.value)
                    .graphicsLayer {
                        rotationZ = rotation.value
                    }
            )
        }
    }
}

private suspend fun runFavoriteAnimation(
    scale: Animatable<Float, AnimationVector1D>,
    rotation: Animatable<Float, AnimationVector1D>
) {
    rotation.animateTo(
        targetValue = 0f,
        animationSpec = keyframes {
            durationMillis = 420
            0f at 0
            -14f at 90
            12f at 180
            -6f at 270
            0f at 420
        }
    )

    scale.animateTo(
        targetValue = 1f,
        animationSpec = keyframes {
            durationMillis = 460
            1f at 0
            0.82f at 70
            1.34f at 180
            1.08f at 300
            1f at 460
        }
    )

    scale.animateTo(
        targetValue = 1.15f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
}
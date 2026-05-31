package com.example.projekt_aplikacje_mobilne.ui.media

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.projekt_aplikacje_mobilne.data.LocalProductRepository
import com.example.projekt_aplikacje_mobilne.ui.shared.AppScaffold
import com.example.projekt_aplikacje_mobilne.ui.shared.EmptyState

@Composable
fun FullscreenVideoScreen(
    productId: String,
    productRepository: LocalProductRepository,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val product = remember(productId) {
        productRepository.getProductById(productId)
    }

    val videoResId = product?.videoResId

    if (product == null || videoResId == null) {
        AppScaffold(
            title = "Wideo",
            onBack = onBack
        ) { padding ->
            EmptyState(
                title = "Brak materiału wideo",
                message = "Ten produkt nie ma przypisanego materiału wideo.",
                modifier = Modifier.padding(padding)
            )
        }
        return
    }

    val videoUri = remember(videoResId) {
        Uri.parse("android.resource://${context.packageName}/$videoResId")
    }

    val exoPlayer = remember(videoUri) {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(videoUri))
            prepare()
            playWhenReady = true
        }
    }

    DisposableEffect(exoPlayer) {
        onDispose {
            exoPlayer.release()
        }
    }

    AppScaffold(
        title = "Wideo",
        onBack = onBack
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            AndroidView(
                factory = { ctx ->
                    PlayerView(ctx).apply {
                        player = exoPlayer
                        useController = true
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
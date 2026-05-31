package com.example.projekt_aplikacje_mobilne.ui.media

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.projekt_aplikacje_mobilne.data.LocalProductRepository
import com.example.projekt_aplikacje_mobilne.ui.shared.AppScaffold
import com.example.projekt_aplikacje_mobilne.ui.shared.EmptyState

private const val LOOP_PAGE_COUNT = 10_000

@Composable
fun FullscreenImageScreen(
    productId: String,
    initialImageIndex: Int,
    productRepository: LocalProductRepository,
    onBack: () -> Unit
) {
    val product = remember(productId) {
        productRepository.getProductById(productId)
    }

    if (product == null || product.galleryImageResIds.isEmpty()) {
        AppScaffold(
            title = "Podgląd zdjęcia",
            onBack = onBack
        ) { padding ->
            EmptyState(
                title = "Brak zdjęć",
                message = "Nie udało się załadować galerii tego produktu.",
                modifier = Modifier.padding(padding)
            )
        }
        return
    }

    val images = product.galleryImageResIds
    val safeIndex = initialImageIndex.coerceIn(0, images.lastIndex)

    val startPage = remember(images.size, safeIndex) {
        val middle = LOOP_PAGE_COUNT / 2
        middle - (middle % images.size) + safeIndex
    }

    val pagerState = rememberPagerState(
        initialPage = startPage,
        pageCount = { LOOP_PAGE_COUNT }
    )

    AppScaffold(
        title = "Galeria zdjęć",
        onBack = onBack
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color.Black),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentPadding = PaddingValues(0.dp)
            ) { page ->
                val realIndex = page % images.size

                Image(
                    painter = painterResource(id = images[realIndex]),
                    contentDescription = "Zdjęcie ${realIndex + 1} produktu ${product.name}",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }

            Text(
                text = "${(pagerState.currentPage % images.size) + 1} / ${images.size}",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
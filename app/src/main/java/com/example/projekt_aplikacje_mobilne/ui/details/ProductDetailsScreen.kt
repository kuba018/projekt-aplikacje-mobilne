package com.example.projekt_aplikacje_mobilne.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projekt_aplikacje_mobilne.data.LocalProductRepository
import com.example.projekt_aplikacje_mobilne.favorites.FavoritesRepository
import com.example.projekt_aplikacje_mobilne.tests.TestTags
import com.example.projekt_aplikacje_mobilne.ui.shared.AppScaffold
import com.example.projekt_aplikacje_mobilne.ui.shared.EmptyState
import com.example.projekt_aplikacje_mobilne.ui.shared.SectionTitle

@Composable
fun ProductDetailsScreen(
    productId: String,
    productRepository: LocalProductRepository,
    favoritesRepository: FavoritesRepository,
    onBack: () -> Unit,
    onOpenImage: (Int) -> Unit,
    onOpenVideo: () -> Unit
) {
    val viewModel: ProductDetailsViewModel = viewModel(
        factory = ProductDetailsViewModel.factory(
            productId = productId,
            productRepository = productRepository,
            favoritesRepository = favoritesRepository
        )
    )

    val isFavorite by viewModel.isFavorite.collectAsState()
    val product = viewModel.product

    AppScaffold(
        title = "Szczegóły produktu",
        onBack = onBack
    ) { padding ->
        if (product == null) {
            EmptyState(
                title = "Nie znaleziono produktu",
                message = "Podany identyfikator produktu jest nieprawidłowy.",
                modifier = Modifier.padding(padding)
            )
            return@AppScaffold
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .testTag(TestTags.PRODUCT_DETAILS_SCREEN),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                ProductHeader(
                    product = product,
                    isFavorite = isFavorite,
                    onFavoriteClick = viewModel::toggleFavorite
                )
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = product.mainImageResId),
                        contentDescription = "Zdjęcie główne produktu ${product.name}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 220.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            item {
                SectionTitle(text = "Opis")
                Card(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = product.longDescription,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            item {
                ProductGallerySection(
                    imageIds = product.galleryImageResIds,
                    onImageClick = onOpenImage
                )
            }

            item {
                ProductVideoSection(
                    hasVideo = product.videoResId != null,
                    thumbnailResId = product.videoThumbnailResId,
                    onOpenVideo = onOpenVideo
                )
            }

            item {
                ProductAudioSection(
                    audioTitle = product.audioTitle
                )
            }
        }
    }
}
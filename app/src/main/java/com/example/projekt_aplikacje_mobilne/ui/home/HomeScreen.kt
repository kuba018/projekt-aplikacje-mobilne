package com.example.projekt_aplikacje_mobilne.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.projekt_aplikacje_mobilne.model.Product
import com.example.projekt_aplikacje_mobilne.tests.TestTags
import com.example.projekt_aplikacje_mobilne.ui.products.ProductCard
import com.example.projekt_aplikacje_mobilne.ui.shared.AppScaffold
import com.example.projekt_aplikacje_mobilne.ui.shared.SectionTitle

@Composable
fun HomeScreen(
    featuredProducts: List<Product>,
    favoriteIds: Set<String>,
    onOpenCategories: () -> Unit,
    onOpenFavorites: () -> Unit,
    onOpenSettings: () -> Unit,
    onProductClick: (String) -> Unit,
    onFavoriteClick: (String) -> Unit
) {
    AppScaffold(
        title = "Katalog sprzętu pływackiego",
        onOpenFavorites = onOpenFavorites,
        onOpenSettings = onOpenSettings
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .testTag(TestTags.HOME_SCREEN),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(onClick = onOpenCategories)
                        .testTag(TestTags.OPEN_CATEGORIES_BUTTON)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Kategorie",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "Okularki, płetwy i czepki w minimalistycznym katalogu."
                        )
                    }
                }
            }

            item {
                SectionTitle(text = "Wyróżnione produkty")
            }

            items(featuredProducts) { product ->
                ProductCard(
                    product = product,
                    isFavorite = favoriteIds.contains(product.id),
                    onProductClick = onProductClick,
                    onFavoriteClick = onFavoriteClick
                )
            }
        }
    }
}
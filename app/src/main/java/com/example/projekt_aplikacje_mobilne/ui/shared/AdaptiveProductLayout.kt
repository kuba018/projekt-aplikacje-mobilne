package com.example.projekt_aplikacje_mobilne.ui.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.projekt_aplikacje_mobilne.model.Product
import com.example.projekt_aplikacje_mobilne.model.ProductLayoutMode
import com.example.projekt_aplikacje_mobilne.ui.products.ProductCard
import com.example.projekt_aplikacje_mobilne.ui.products.ProductGrid

@Composable
fun AdaptiveProductLayout(
    products: List<Product>,
    layoutMode: ProductLayoutMode,
    favoriteIds: Set<String>,
    onProductClick: (String) -> Unit,
    onFavoriteClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    when (layoutMode) {
        ProductLayoutMode.LIST -> {
            LazyColumn(
                modifier = modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(products, key = { it.id }) { product ->
                    ProductCard(
                        product = product,
                        isFavorite = favoriteIds.contains(product.id),
                        onProductClick = onProductClick,
                        onFavoriteClick = onFavoriteClick
                    )
                }
            }
        }

        ProductLayoutMode.GRID -> {
            ProductGrid(
                products = products,
                favoriteIds = favoriteIds,
                onProductClick = onProductClick,
                onFavoriteClick = onFavoriteClick,
                modifier = modifier
            )
        }
    }
}
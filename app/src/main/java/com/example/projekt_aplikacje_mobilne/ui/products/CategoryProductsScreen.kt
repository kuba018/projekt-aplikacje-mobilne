package com.example.projekt_aplikacje_mobilne.ui.products

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.projekt_aplikacje_mobilne.model.Product
import com.example.projekt_aplikacje_mobilne.model.ProductCategory
import com.example.projekt_aplikacje_mobilne.settings.SettingsRepository
import com.example.projekt_aplikacje_mobilne.settings.UserPreferences
import com.example.projekt_aplikacje_mobilne.ui.shared.AdaptiveProductLayout
import com.example.projekt_aplikacje_mobilne.ui.shared.AppScaffold

@Composable
fun CategoryProductsScreen(
    category: ProductCategory,
    products: List<Product>,
    favoriteIds: Set<String>,
    settingsRepository: SettingsRepository,
    onBack: () -> Unit,
    onProductClick: (String) -> Unit,
    onFavoriteClick: (String) -> Unit,
    onOpenFavorites: () -> Unit,
    onOpenSettings: () -> Unit
) {
    val preferences by settingsRepository.preferencesFlow.collectAsState(
        initial = UserPreferences()
    )

    AppScaffold(
        title = category.displayNamePl,
        onBack = onBack,
        onOpenFavorites = onOpenFavorites,
        onOpenSettings = onOpenSettings
    ) { padding ->
        AdaptiveProductLayout(
            products = products,
            layoutMode = preferences.layoutMode,
            favoriteIds = favoriteIds,
            onProductClick = onProductClick,
            onFavoriteClick = onFavoriteClick,
            modifier = Modifier.padding(padding)
        )
    }
}
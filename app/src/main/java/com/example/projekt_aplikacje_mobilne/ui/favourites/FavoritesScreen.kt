package com.example.projekt_aplikacje_mobilne.ui.favorites

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.projekt_aplikacje_mobilne.model.Product
import com.example.projekt_aplikacje_mobilne.settings.SettingsRepository
import com.example.projekt_aplikacje_mobilne.settings.UserPreferences
import com.example.projekt_aplikacje_mobilne.ui.shared.AdaptiveProductLayout
import com.example.projekt_aplikacje_mobilne.ui.shared.AppScaffold
import com.example.projekt_aplikacje_mobilne.ui.shared.EmptyState

@Composable
fun FavoritesScreen(
    products: List<Product>,
    favoriteIds: Set<String>,
    settingsRepository: SettingsRepository,
    onBack: () -> Unit,
    onProductClick: (String) -> Unit,
    onFavoriteClick: (String) -> Unit
) {
    val preferences by settingsRepository.preferencesFlow.collectAsState(
        initial = UserPreferences()
    )

    AppScaffold(
        title = "Ulubione",
        onBack = onBack
    ) { padding ->
        if (products.isEmpty()) {
            EmptyState(
                title = "Brak ulubionych produktów",
                message = "Dodaj produkty do ulubionych, aby pojawiły się na tej liście.",
                modifier = Modifier.padding(padding)
            )
        } else {
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
}
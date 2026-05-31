package com.example.projekt_aplikacje_mobilne.favorites

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class FavoritesRepository(
    private val dataSource: FavoritesDataSource
) {
    val favoriteIdsFlow: Flow<Set<String>> = dataSource.favoriteIdsFlow

    suspend fun toggleFavorite(productId: String) {
        dataSource.toggleFavorite(productId)
    }

    suspend fun isFavorite(productId: String): Boolean {
        return favoriteIdsFlow.first().contains(productId)
    }
}
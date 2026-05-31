package com.example.projekt_aplikacje_mobilne.favorites

import kotlinx.coroutines.flow.Flow

interface FavoritesDataSource {
    val favoriteIdsFlow: Flow<Set<String>>
    suspend fun toggleFavorite(productId: String)
}
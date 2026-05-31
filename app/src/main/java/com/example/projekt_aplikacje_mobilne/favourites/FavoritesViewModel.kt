package com.example.projekt_aplikacje_mobilne.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    val favoriteIds = favoritesRepository.favoriteIdsFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptySet()
    )

    fun toggleFavorite(productId: String) {
        viewModelScope.launch {
            favoritesRepository.toggleFavorite(productId)
        }
    }
}
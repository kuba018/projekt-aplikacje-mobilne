package com.example.projekt_aplikacje_mobilne.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.projekt_aplikacje_mobilne.data.LocalProductRepository
import com.example.projekt_aplikacje_mobilne.favorites.FavoritesRepository
import com.example.projekt_aplikacje_mobilne.model.Product
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductDetailsViewModel(
    private val productId: String,
    private val productRepository: LocalProductRepository,
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {

    val product: Product? = productRepository.getProductById(productId)

    val isFavorite: StateFlow<Boolean> = favoritesRepository.favoriteIdsFlow
        .map { ids -> ids.contains(productId) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )

    fun toggleFavorite() {
        viewModelScope.launch {
            favoritesRepository.toggleFavorite(productId)
        }
    }

    companion object {
        fun factory(
            productId: String,
            productRepository: LocalProductRepository,
            favoritesRepository: FavoritesRepository
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ProductDetailsViewModel(
                    productId = productId,
                    productRepository = productRepository,
                    favoritesRepository = favoritesRepository
                ) as T
            }
        }
    }
}
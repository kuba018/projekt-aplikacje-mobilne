package com.example.projekt_aplikacje_mobilne

import com.example.projekt_aplikacje_mobilne.favorites.FavoritesDataSource
import com.example.projekt_aplikacje_mobilne.favorites.FavoritesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class FavoritesRepositoryTest {

    private class FakeFavoritesDataSource(
        initialIds: Set<String> = emptySet()
    ) : FavoritesDataSource {

        private val state = MutableStateFlow(initialIds)

        override val favoriteIdsFlow = state

        override suspend fun toggleFavorite(productId: String) {
            val current = state.value
            state.value =
                if (current.contains(productId)) current - productId else current + productId
        }
    }

    @Test
    fun toggleFavorite_addsProductWhenMissing() = runTest {
        val repository = FavoritesRepository(FakeFavoritesDataSource())
        val productId = "goggles_biofuse_2"

        repository.toggleFavorite(productId)

        assertTrue(repository.isFavorite(productId))
        assertTrue(repository.favoriteIdsFlow.first().contains(productId))
    }

    @Test
    fun toggleFavorite_removesProductWhenAlreadyFavorite() = runTest {
        val productId = "goggles_biofuse_2"
        val repository = FavoritesRepository(
            FakeFavoritesDataSource(initialIds = setOf(productId))
        )

        repository.toggleFavorite(productId)

        assertFalse(repository.isFavorite(productId))
        assertFalse(repository.favoriteIdsFlow.first().contains(productId))
    }
}
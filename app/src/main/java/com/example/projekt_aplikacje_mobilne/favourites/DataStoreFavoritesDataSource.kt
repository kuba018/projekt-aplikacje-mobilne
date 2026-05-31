package com.example.projekt_aplikacje_mobilne.favorites

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.example.projekt_aplikacje_mobilne.settings.SettingsDataStoreProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreFavoritesDataSource(
    private val dataStoreProvider: SettingsDataStoreProvider
) : FavoritesDataSource {

    companion object {
        private val FAVORITES = stringSetPreferencesKey("favorite_ids")
    }

    override val favoriteIdsFlow: Flow<Set<String>> =
        dataStoreProvider.dataStore.data.map { preferences ->
            preferences[FAVORITES] ?: emptySet()
        }

    override suspend fun toggleFavorite(productId: String) {
        dataStoreProvider.dataStore.edit { preferences ->
            val current = preferences[FAVORITES] ?: emptySet()
            preferences[FAVORITES] =
                if (current.contains(productId)) current - productId else current + productId
        }
    }
}
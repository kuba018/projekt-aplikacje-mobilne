package com.example.projekt_aplikacje_mobilne

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projekt_aplikacje_mobilne.data.LocalProductRepository
import com.example.projekt_aplikacje_mobilne.favorites.DataStoreFavoritesDataSource
import com.example.projekt_aplikacje_mobilne.favorites.FavoritesRepository
import com.example.projekt_aplikacje_mobilne.favorites.FavoritesViewModel
import com.example.projekt_aplikacje_mobilne.navigation.AppNavGraph
import com.example.projekt_aplikacje_mobilne.settings.SettingsDataStoreProvider
import com.example.projekt_aplikacje_mobilne.settings.SettingsRepository
import com.example.projekt_aplikacje_mobilne.settings.UserPreferences
import com.example.projekt_aplikacje_mobilne.settings.settingsDataStore
import com.example.projekt_aplikacje_mobilne.ui.theme.ProjektAplikacjeMobilneTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataStoreProvider = object : SettingsDataStoreProvider {
            override val dataStore = applicationContext.settingsDataStore
        }

        val productRepository = LocalProductRepository()
        val settingsRepository = SettingsRepository(dataStoreProvider)
        val favoritesRepository = FavoritesRepository(
            DataStoreFavoritesDataSource(dataStoreProvider)
        )

        setContent {
            val preferences by settingsRepository.preferencesFlow.collectAsState(
                initial = UserPreferences()
            )

            val favoritesViewModel: FavoritesViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    @Suppress("UNCHECKED_CAST")
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return FavoritesViewModel(favoritesRepository) as T
                    }
                }
            )

            ProjektAplikacjeMobilneTheme(
                themeMode = preferences.themeMode,
                fontScale = preferences.fontScale
            ) {
                AppNavGraph(
                    productRepository = productRepository,
                    settingsRepository = settingsRepository,
                    favoritesRepository = favoritesRepository,
                    favoritesViewModel = favoritesViewModel
                )
            }
        }
    }
}
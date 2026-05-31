package com.example.projekt_aplikacje_mobilne.settings

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.projekt_aplikacje_mobilne.model.FontScaleOption
import com.example.projekt_aplikacje_mobilne.model.ProductLayoutMode
import com.example.projekt_aplikacje_mobilne.model.ThemeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsRepository(
    private val dataStoreProvider: SettingsDataStoreProvider
) {

    companion object {
        private val THEME_MODE = stringPreferencesKey("theme_mode")
        private val FONT_SCALE = stringPreferencesKey("font_scale")
        private val LAYOUT_MODE = stringPreferencesKey("layout_mode")
    }

    val preferencesFlow: Flow<UserPreferences> =
        dataStoreProvider.dataStore.data.map { preferences ->
            UserPreferences(
                themeMode = preferences[THEME_MODE]?.let(ThemeMode::valueOf) ?: ThemeMode.SYSTEM,
                fontScale = preferences[FONT_SCALE]?.let(FontScaleOption::valueOf) ?: FontScaleOption.NORMAL,
                layoutMode = preferences[LAYOUT_MODE]?.let(ProductLayoutMode::valueOf) ?: ProductLayoutMode.LIST
            )
        }

    suspend fun setThemeMode(themeMode: ThemeMode) {
        dataStoreProvider.dataStore.edit { preferences ->
            preferences[THEME_MODE] = themeMode.name
        }
    }

    suspend fun setFontScale(fontScaleOption: FontScaleOption) {
        dataStoreProvider.dataStore.edit { preferences ->
            preferences[FONT_SCALE] = fontScaleOption.name
        }
    }

    suspend fun setLayoutMode(layoutMode: ProductLayoutMode) {
        dataStoreProvider.dataStore.edit { preferences ->
            preferences[LAYOUT_MODE] = layoutMode.name
        }
    }
}
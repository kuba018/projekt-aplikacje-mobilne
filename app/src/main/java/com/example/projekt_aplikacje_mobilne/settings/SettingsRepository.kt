package com.example.projekt_aplikacje_mobilne.settings

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.projekt_aplikacje_mobilne.model.FontScaleOption
import com.example.projekt_aplikacje_mobilne.model.ProductLayoutMode
import com.example.projekt_aplikacje_mobilne.model.ThemeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsRepository(
    private val context: Context
) {
    private companion object {
        val THEME_MODE = stringPreferencesKey("theme_mode")
        val FONT_SCALE = stringPreferencesKey("font_scale")
        val LAYOUT_MODE = stringPreferencesKey("layout_mode")
        val FAVORITES = stringPreferencesKey("favorites")
    }

    val userPreferencesFlow: Flow<UserPreferences> =
        context.settingsDataStore.data.map { prefs ->
            UserPreferences(
                themeMode = prefs[THEME_MODE]?.let {
                    runCatching { ThemeMode.valueOf(it) }.getOrDefault(ThemeMode.SYSTEM)
                } ?: ThemeMode.SYSTEM,
                fontScaleOption = prefs[FONT_SCALE]?.let {
                    runCatching { FontScaleOption.valueOf(it) }.getOrDefault(FontScaleOption.NORMAL)
                } ?: FontScaleOption.NORMAL,
                productLayoutMode = prefs[LAYOUT_MODE]?.let {
                    runCatching { ProductLayoutMode.valueOf(it) }.getOrDefault(ProductLayoutMode.LIST)
                } ?: ProductLayoutMode.LIST,
                favoriteProductIds = prefs[FAVORITES]
                    ?.split(",")
                    ?.filter { it.isNotBlank() }
                    ?.toSet()
                    ?: emptySet()
            )
        }

    suspend fun setThemeMode(themeMode: ThemeMode) {
        context.settingsDataStore.edit { prefs ->
            prefs[THEME_MODE] = themeMode.name
        }
    }

    suspend fun setFontScale(fontScaleOption: FontScaleOption) {
        context.settingsDataStore.edit { prefs ->
            prefs[FONT_SCALE] = fontScaleOption.name
        }
    }

    suspend fun setLayoutMode(layoutMode: ProductLayoutMode) {
        context.settingsDataStore.edit { prefs ->
            prefs[LAYOUT_MODE] = layoutMode.name
        }
    }

    suspend fun toggleFavorite(productId: String) {
        context.settingsDataStore.edit { prefs ->
            val current = prefs[FAVORITES]
                ?.split(",")
                ?.filter { it.isNotBlank() }
                ?.toMutableSet()
                ?: mutableSetOf()

            if (current.contains(productId)) {
                current.remove(productId)
            } else {
                current.add(productId)
            }

            prefs[FAVORITES] = current.joinToString(",")
        }
    }
}
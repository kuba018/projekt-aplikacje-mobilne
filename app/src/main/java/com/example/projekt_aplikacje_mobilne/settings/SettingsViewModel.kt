package com.example.projekt_aplikacje_mobilne.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projekt_aplikacje_mobilne.model.FontScaleOption
import com.example.projekt_aplikacje_mobilne.model.ProductLayoutMode
import com.example.projekt_aplikacje_mobilne.model.ThemeMode
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    val preferences = settingsRepository.preferencesFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = UserPreferences()
    )

    fun setThemeMode(themeMode: ThemeMode) {
        if (preferences.value.themeMode == themeMode) return
        viewModelScope.launch {
            settingsRepository.setThemeMode(themeMode)
        }
    }

    fun setFontScale(fontScaleOption: FontScaleOption) {
        if (preferences.value.fontScale == fontScaleOption) return
        viewModelScope.launch {
            settingsRepository.setFontScale(fontScaleOption)
        }
    }

    fun setLayoutMode(layoutMode: ProductLayoutMode) {
        if (preferences.value.layoutMode == layoutMode) return
        viewModelScope.launch {
            settingsRepository.setLayoutMode(layoutMode)
        }
    }
}
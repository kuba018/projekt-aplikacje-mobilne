package com.example.projekt_aplikacje_mobilne.settings

import com.example.projekt_aplikacje_mobilne.model.FontScaleOption
import com.example.projekt_aplikacje_mobilne.model.ProductLayoutMode
import com.example.projekt_aplikacje_mobilne.model.ThemeMode

data class UserPreferences(
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val fontScale: FontScaleOption = FontScaleOption.NORMAL,
    val layoutMode: ProductLayoutMode = ProductLayoutMode.LIST,
    val favoriteIds: Set<String> = emptySet()
)
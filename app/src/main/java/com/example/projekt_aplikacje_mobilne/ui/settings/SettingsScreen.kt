package com.example.projekt_aplikacje_mobilne.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Card
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projekt_aplikacje_mobilne.model.FontScaleOption
import com.example.projekt_aplikacje_mobilne.model.ProductLayoutMode
import com.example.projekt_aplikacje_mobilne.model.ThemeMode
import com.example.projekt_aplikacje_mobilne.settings.SettingsRepository
import com.example.projekt_aplikacje_mobilne.settings.SettingsViewModel
import com.example.projekt_aplikacje_mobilne.tests.TestTags
import com.example.projekt_aplikacje_mobilne.ui.shared.AppScaffold

@Composable
fun SettingsScreen(
    settingsRepository: SettingsRepository,
    onBack: () -> Unit
) {
    val factory = remember(settingsRepository) {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return SettingsViewModel(settingsRepository) as T
            }
        }
    }

    val viewModel: SettingsViewModel = viewModel(factory = factory)
    val preferences by viewModel.preferences.collectAsStateWithLifecycle()

    AppScaffold(
        title = "Ustawienia",
        onBack = onBack
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .testTag(TestTags.SETTINGS_SCREEN),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                SettingSectionCard(title = "Motyw") {
                    ThemeMode.entries.forEach { mode ->
                        SettingRadioRow(
                            text = when (mode) {
                                ThemeMode.LIGHT -> "Jasny"
                                ThemeMode.DARK -> "Ciemny"
                                ThemeMode.SYSTEM -> "Systemowy"
                            },
                            selected = preferences.themeMode == mode,
                            tag = when (mode) {
                                ThemeMode.LIGHT -> TestTags.THEME_LIGHT
                                ThemeMode.DARK -> TestTags.THEME_DARK
                                ThemeMode.SYSTEM -> TestTags.THEME_SYSTEM
                            },
                            onClick = { viewModel.setThemeMode(mode) }
                        )
                    }
                }
            }

            item {
                SettingSectionCard(title = "Rozmiar tekstu") {
                    FontScaleOption.entries.forEach { option ->
                        SettingRadioRow(
                            text = option.label,
                            selected = preferences.fontScale == option,
                            tag = when (option) {
                                FontScaleOption.SMALL -> TestTags.FONT_SMALL
                                FontScaleOption.NORMAL -> TestTags.FONT_NORMAL
                                FontScaleOption.LARGE -> TestTags.FONT_LARGE
                                FontScaleOption.EXTRA_LARGE -> TestTags.FONT_EXTRA_LARGE
                            },
                            onClick = { viewModel.setFontScale(option) }
                        )
                    }
                }
            }

            item {
                SettingSectionCard(title = "Układ produktów") {
                    ProductLayoutMode.entries.forEach { mode ->
                        SettingRadioRow(
                            text = if (mode == ProductLayoutMode.LIST) "Lista" else "Siatka",
                            selected = preferences.layoutMode == mode,
                            tag = if (mode == ProductLayoutMode.LIST) {
                                TestTags.LAYOUT_LIST
                            } else {
                                TestTags.LAYOUT_GRID
                            },
                            onClick = { viewModel.setLayoutMode(mode) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SettingSectionCard(
    title: String,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title)
            Column(
                modifier = Modifier.padding(top = 8.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                content()
            }
        }
    }
}

@Composable
private fun SettingRadioRow(
    text: String,
    selected: Boolean,
    tag: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .testTag(tag)
            .selectable(
                selected = selected,
                onClick = onClick,
                role = Role.RadioButton
            )
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick
        )
        Text(
            text = text,
            modifier = Modifier.padding(start = 8.dp, top = 12.dp)
        )
    }
}
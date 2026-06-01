package com.example.projekt_aplikacje_mobilne

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.core.app.ApplicationProvider
import com.example.projekt_aplikacje_mobilne.model.ThemeMode
import com.example.projekt_aplikacje_mobilne.settings.SettingsDataStoreProvider
import com.example.projekt_aplikacje_mobilne.settings.SettingsRepository
import com.example.projekt_aplikacje_mobilne.tests.TestTags
import com.example.projekt_aplikacje_mobilne.ui.settings.SettingsScreen
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class SettingsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun createRepository(fileName: String): SettingsRepository {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        val dataStore = PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile(fileName) }
        )

        val provider = object : SettingsDataStoreProvider {
            override val dataStore = dataStore
        }

        return SettingsRepository(provider)
    }

    @Test
    fun settingsScreen_isDisplayed() {
        val repository = createRepository("settings_screen_display.preferences_pb")

        composeTestRule.setContent {
            SettingsScreen(
                settingsRepository = repository,
                onBack = {}
            )
        }

        composeTestRule.onNodeWithTag(TestTags.SETTINGS_SCREEN).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.THEME_LIGHT).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.FONT_NORMAL).assertIsDisplayed()
        composeTestRule.onNodeWithTag(TestTags.LAYOUT_GRID).assertIsDisplayed()
    }

    @Test
    fun clickingDarkTheme_updatesRepository() {
        val repository = createRepository("settings_dark.preferences_pb")

        composeTestRule.setContent {
            SettingsScreen(
                settingsRepository = repository,
                onBack = {}
            )
        }

        composeTestRule.onNodeWithTag(TestTags.THEME_DARK).performClick()

        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            runBlocking {
                repository.preferencesFlow.first().themeMode == ThemeMode.DARK
            }
        }
        val preferences = runBlocking { repository.preferencesFlow.first() }
        assertEquals(ThemeMode.DARK, preferences.themeMode)
    }
}
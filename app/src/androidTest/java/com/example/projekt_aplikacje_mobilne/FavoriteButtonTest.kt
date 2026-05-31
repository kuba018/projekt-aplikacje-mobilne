package com.example.projekt_aplikacje_mobilne

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasStateDescription
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.projekt_aplikacje_mobilne.tests.TestTags
import com.example.projekt_aplikacje_mobilne.ui.shared.FavoriteButton
import org.junit.Rule
import org.junit.Test

class FavoriteButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun favoriteButton_togglesStateDescription() {
        composeTestRule.setContent {
            val isFavorite = remember { mutableStateOf(false) }

            FavoriteButton(
                isFavorite = isFavorite.value,
                onClick = { isFavorite.value = !isFavorite.value }
            )
        }

        composeTestRule
            .onNodeWithTag(TestTags.FAVORITE_BUTTON)
            .assertIsDisplayed()
            .assert(hasStateDescription("produkt poza ulubionymi"))

        composeTestRule
            .onNodeWithTag(TestTags.FAVORITE_BUTTON)
            .performClick()

        composeTestRule
            .onNodeWithTag(TestTags.FAVORITE_BUTTON)
            .assert(hasStateDescription("produkt w ulubionych"))
    }
}
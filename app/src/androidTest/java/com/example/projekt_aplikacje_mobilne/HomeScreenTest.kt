package com.example.projekt_aplikacje_mobilne.ui.home

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.projekt_aplikacje_mobilne.data.LocalProductRepository
import com.example.projekt_aplikacje_mobilne.tests.TestTags
import com.example.projekt_aplikacje_mobilne.ui.theme.ProjektAplikacjeMobilneTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeScreen_displaysMainContent() {
        val repository = LocalProductRepository()

        composeTestRule.setContent {
            ProjektAplikacjeMobilneTheme {
                HomeScreen(
                    featuredProducts = repository.getFeaturedProducts(),
                    favoriteIds = emptySet(),
                    onOpenCategories = {},
                    onOpenFavorites = {},
                    onOpenSettings = {},
                    onProductClick = {},
                    onFavoriteClick = {}
                )
            }
        }

        composeTestRule.onNodeWithTag(TestTags.HOME_SCREEN).assertExists()
        composeTestRule.onNodeWithTag(TestTags.OPEN_CATEGORIES_BUTTON).assertExists()
    }

    @Test
    fun clickingCategoriesCard_invokesCallback() {
        var clicked = false
        val repository = LocalProductRepository()

        composeTestRule.setContent {
            ProjektAplikacjeMobilneTheme {
                HomeScreen(
                    featuredProducts = repository.getFeaturedProducts(),
                    favoriteIds = emptySet(),
                    onOpenCategories = { clicked = true },
                    onOpenFavorites = {},
                    onOpenSettings = {},
                    onProductClick = {},
                    onFavoriteClick = {}
                )
            }
        }

        composeTestRule.onNodeWithTag(TestTags.OPEN_CATEGORIES_BUTTON).performClick()

        assertTrue(clicked)
    }
}
package com.example.projekt_aplikacje_mobilne.ui.categories

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.example.projekt_aplikacje_mobilne.model.ProductCategory
import com.example.projekt_aplikacje_mobilne.tests.TestTags
import com.example.projekt_aplikacje_mobilne.ui.theme.ProjektAplikacjeMobilneTheme
import org.junit.Rule
import org.junit.Test

class CategoriesScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun categoriesScreen_displaysAllCategories() {
        composeTestRule.setContent {
            ProjektAplikacjeMobilneTheme {
                CategoriesScreen(
                    categories = ProductCategory.entries,
                    onBack = {},
                    onCategoryClick = {},
                    onOpenFavorites = {},
                    onOpenSettings = {}
                )
            }
        }

        composeTestRule.onNodeWithTag(TestTags.CATEGORIES_SCREEN).assertExists()
        composeTestRule.onNodeWithText("Okularki").assertExists()
        composeTestRule.onNodeWithText("Płetwy").assertExists()
        composeTestRule.onNodeWithText("Czepki").assertExists()
    }
}
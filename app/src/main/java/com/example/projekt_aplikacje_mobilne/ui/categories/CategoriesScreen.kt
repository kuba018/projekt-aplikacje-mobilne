package com.example.projekt_aplikacje_mobilne.ui.categories

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.projekt_aplikacje_mobilne.model.ProductCategory
import com.example.projekt_aplikacje_mobilne.tests.TestTags
import com.example.projekt_aplikacje_mobilne.ui.shared.AppScaffold

@Composable
fun CategoriesScreen(
    categories: List<ProductCategory>,
    onBack: () -> Unit,
    onCategoryClick: (ProductCategory) -> Unit,
    onOpenFavorites: () -> Unit,
    onOpenSettings: () -> Unit
) {
    AppScaffold(
        title = "Kategorie",
        onBack = onBack,
        onOpenFavorites = onOpenFavorites,
        onOpenSettings = onOpenSettings
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .testTag(TestTags.CATEGORIES_SCREEN),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(categories) { category ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onCategoryClick(category) }
                ) {
                    Text(
                        text = category.displayNamePl,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}
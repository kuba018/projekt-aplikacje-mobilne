package com.example.projekt_aplikacje_mobilne.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.projekt_aplikacje_mobilne.model.Product
import com.example.projekt_aplikacje_mobilne.ui.shared.FavoriteButton
import kotlinx.coroutines.delay

@Composable
fun ProductHeader(
    product: Product,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showFavoriteBadge by remember { mutableStateOf(false) }

    LaunchedEffect(showFavoriteBadge) {
        if (showFavoriteBadge) {
            delay(1700)
            showFavoriteBadge = false
        }
    }

    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = product.category.displayNamePl,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = product.shortDescription,
                    style = MaterialTheme.typography.bodyLarge
                )
                FavoriteButton(
                    isFavorite = isFavorite,
                    onClick = {
                        val wasFavorite = isFavorite
                        onFavoriteClick()
                        if (!wasFavorite) {
                            showFavoriteBadge = true
                        }
                    }
                )
            }

            AnimatedFavoriteBadge(
                visible = showFavoriteBadge,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 18.dp)
            )
        }
    }
}
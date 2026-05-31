package com.example.projekt_aplikacje_mobilne.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.projekt_aplikacje_mobilne.tests.TestTags
import com.example.projekt_aplikacje_mobilne.ui.shared.SectionTitle

@Composable
fun ProductGallerySection(
    imageIds: List<Int>,
    onImageClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    if (imageIds.isEmpty()) return

    Column(
        modifier = modifier
            .fillMaxWidth()
            .testTag(TestTags.PRODUCT_GALLERY)
    ) {
        SectionTitle(text = "Galeria")

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            imageIds.forEachIndexed { index, imageId ->
                Card(
                    modifier = Modifier
                        .size(width = 140.dp, height = 100.dp)
                        .clickable { onImageClick(index) }
                ) {
                    Image(
                        painter = painterResource(id = imageId),
                        contentDescription = "Miniatura zdjęcia ${index + 1}",
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}
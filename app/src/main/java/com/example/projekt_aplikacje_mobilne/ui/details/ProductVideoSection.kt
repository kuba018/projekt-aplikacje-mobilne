package com.example.projekt_aplikacje_mobilne.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.projekt_aplikacje_mobilne.tests.TestTags
import com.example.projekt_aplikacje_mobilne.ui.shared.SectionTitle

@Composable
fun ProductVideoSection(
    hasVideo: Boolean,
    thumbnailResId: Int?,
    onOpenVideo: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (!hasVideo) return

    Column(
        modifier = modifier.testTag(TestTags.PRODUCT_VIDEO_SECTION)
    ) {
        SectionTitle(text = "Wideo")

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onOpenVideo)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                if (thumbnailResId != null) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                    ) {
                        Image(
                            painter = painterResource(id = thumbnailResId),
                            contentDescription = "Miniatura materiału wideo",
                            modifier = Modifier.fillMaxWidth(),
                            contentScale = ContentScale.Crop
                        )

                        Box(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .clip(CircleShape)
                                .background(Color.Black.copy(alpha = 0.65f))
                                .padding(16.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.PlayArrow,
                                contentDescription = "Odtwórz wideo",
                                tint = Color.White
                            )
                        }
                    }
                }

                Text(
                    text = "Otwórz materiał wideo produktu",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Text(
                    text = "Materiał zostanie uruchomiony w widoku pełnoekranowym.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                )
            }
        }
    }
}
package com.example.projekt_aplikacje_mobilne.ui.details

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.projekt_aplikacje_mobilne.ui.shared.SectionTitle

@Composable
fun ProductAudioSection(
    audioTitle: String?,
    modifier: Modifier = Modifier
) {
    if (audioTitle == null) return

    SectionTitle(
        text = "Audio",
        modifier = modifier
    )

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = audioTitle,
            modifier = Modifier.padding(16.dp)
        )
    }
}
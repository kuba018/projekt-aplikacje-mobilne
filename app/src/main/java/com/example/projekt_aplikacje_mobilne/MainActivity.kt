package com.example.projekt_aplikacje_mobilne

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import com.example.projekt_aplikacje_mobilne.ui.theme.ProjektAplikacjeMobilneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjektAplikacjeMobilneTheme {
                Surface {
                    Text("Projekt aplikacje mobilne")
                }
            }
        }
    }
}
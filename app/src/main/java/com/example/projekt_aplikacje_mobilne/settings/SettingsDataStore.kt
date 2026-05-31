package com.example.projekt_aplikacje_mobilne.settings

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

val Context.settingsDataStore by preferencesDataStore(name = "user_settings")
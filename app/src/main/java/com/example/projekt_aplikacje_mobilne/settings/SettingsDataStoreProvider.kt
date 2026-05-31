package com.example.projekt_aplikacje_mobilne.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

interface SettingsDataStoreProvider {
    val dataStore: DataStore<Preferences>
}
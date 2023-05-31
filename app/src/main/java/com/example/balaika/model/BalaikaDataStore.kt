package com.example.balaika.model

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

class BalaikaDataStore(private val context: Context) {

    private val Context.dataStore by preferencesDataStore(name = "balaikaSetup")

    val values = DataType.values().associateWith {
            key -> context.dataStore.data.map { it[booleanPreferencesKey(key.name)] ?: false }
    }

    suspend fun update(key: DataType, value: Boolean) =
        context.dataStore.edit { it[booleanPreferencesKey(key.name)] = value }

    enum class DataType { FEATURE_ONLY, HAND_PICK_ONLY, NO_SCRUMMING }
}
package com.bootx.yysc.util

import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class StoreManager(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("yysc");
    }

    // 字符串
    suspend fun save(key: String,value: String){
        context.dataStore.edit { preferences->
            preferences[stringPreferencesKey(key)] = value
        }
    }
    fun get(key: String): Flow<String>{
        return context.dataStore.data.map { preferences ->
            preferences[stringPreferencesKey(key)] ?: ""
        }
    }
    suspend fun remove(key: String) {
        context.dataStore.edit { preferences->
            preferences[stringPreferencesKey(key)] = ""
        }
    }

    fun getToken(): Flow<String>{
        return context.dataStore.data.map { preferences ->
            preferences[stringPreferencesKey("token")] ?: ""
        }
    }

}
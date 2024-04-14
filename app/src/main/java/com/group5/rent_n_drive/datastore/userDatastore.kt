package com.group5.rent_n_drive.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class userDatastore(private val context: Context) {
    companion object{
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("UserInformation")

        val userPassword_KEY = stringPreferencesKey("PASSWORD")
        val userUserName_KEY = stringPreferencesKey("USERNAME")
        val carName_KEY = stringPreferencesKey("CARNAME")
        val carStartDate_KEY = stringPreferencesKey("STARTDATE")
    }

    val getUserPassword: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[userPassword_KEY] ?: ""
        }

    val getUserName: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[userUserName_KEY] ?: ""
        }

    suspend fun saveUserInformation(userPassword: String, userName: String){
        context.dataStore.edit { preferences ->
            preferences[userPassword_KEY] = userPassword
            preferences[userUserName_KEY] = userName
        }
    }

    suspend fun saveCarInformation(carName: String, startDate: String){
        context.dataStore.edit { preferences ->
            preferences[carName_KEY] = carName
            preferences[carStartDate_KEY] = startDate
        }
    }

    suspend fun clearAllInformation(){
        context.dataStore.edit { preferences ->
            preferences[userPassword_KEY] = ""
            preferences[userUserName_KEY] = ""
            preferences[carName_KEY] = ""
            preferences[carStartDate_KEY] = ""
        }
    }
}
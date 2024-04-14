package com.group5.rent_n_drive.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class userDatastore(private val context: Context) {
    companion object{
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("UserInformation")

        val userPassword_KEY = stringPreferencesKey("PASSWORD")
        val userUserName_KEY = stringPreferencesKey("USERNAME")
        val carId_Key = intPreferencesKey("CARNAME")
        val carStartDate_KEY = stringPreferencesKey("STARTDATE")
        val carEndDate_KEY = stringPreferencesKey("ENDDATE")
    }

    val getUserPassword: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[userPassword_KEY] ?: ""
        }

    val getUserName: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[userUserName_KEY] ?: ""
        }

    val getCarId: Flow<Int?> = context.dataStore.data
        .map { preferences ->
            preferences[carId_Key] ?: 0
        }

    val getCarStartDate: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[carStartDate_KEY] ?: ""
        }

    val getCarEndDate: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[carEndDate_KEY] ?: ""
        }

    suspend fun saveUserInformation(userPassword: String, userName: String){
        context.dataStore.edit { preferences ->
            preferences[userPassword_KEY] = userPassword
            preferences[userUserName_KEY] = userName
        }
    }

    suspend fun saveCarInformation(carId: Int){
        context.dataStore.edit { preferences ->
            preferences[carId_Key] = carId
        }
    }
    suspend fun saveBookingInformation(startDate: String, endDate: String){
        context.dataStore.edit { preferences ->
            preferences[carStartDate_KEY] = startDate
            preferences[carEndDate_KEY] = endDate
        }
    }
    suspend fun clearCarBookingInformation(){
        context.dataStore.edit { preferences ->
            preferences[carId_Key] = 0
            preferences[carStartDate_KEY] = ""
            preferences[carEndDate_KEY] = ""
        }
    }
}
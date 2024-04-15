package com.group5.rent_n_drive.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
/*
* Made by Ahmad Abbas, Rahul Edirisinghe, Calist Dsouza, Saurav Gautam for MAPD721 Project.
* Class notes, and Other references and online resources used to help write code given below.
* */

class UserBookingDatastore(private val context: Context) {//Use to move
    companion object{
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("UserInformation")

        val previousUserName_Key = stringPreferencesKey("PREVUSER")//used for a bit
    //of Dynamics - hold booking value for previous user
        val userUserName_KEY = stringPreferencesKey("USERNAME")
        val carId_Key = intPreferencesKey("CARNAME")
        val carPrice_Key = intPreferencesKey("PRICE")
        val carStartDate_KEY = stringPreferencesKey("STARTDATE")
        val carEndDate_KEY = stringPreferencesKey("ENDDATE")
        val isBookingMade_KEY = booleanPreferencesKey("ISBOOKINGMADE")
    }

    val getPreviousUser: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[previousUserName_Key] ?: ""
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

    val getIsBookingMade: Flow<Boolean?> = context.dataStore.data
        .map { preferences ->
            preferences[isBookingMade_KEY] ?: false
        }

    val getCarPrice: Flow<Int?> = context.dataStore.data
        .map { preferences ->
            preferences[carPrice_Key] ?: 0
        }

    suspend fun saveUserInformation(userName: String){
        context.dataStore.edit { preferences ->
            if (preferences[previousUserName_Key] == ""){
                preferences[previousUserName_Key] = userName
            }
            preferences[userUserName_KEY] = userName
        }
    }

    suspend fun savePreviousUserInformation(userName: String){
        context.dataStore.edit { preferences ->
            preferences[previousUserName_Key] = userName
        }
    }

    suspend fun saveCarInformation(carId: Int, carPrice: Int){
        context.dataStore.edit { preferences ->
            preferences[carId_Key] = carId
            preferences[carPrice_Key] = carPrice
        }
    }
    suspend fun saveBookingInformation(startDate: String, endDate: String){//save booking data
        context.dataStore.edit { preferences ->
            preferences[carStartDate_KEY] = startDate
            preferences[carEndDate_KEY] = endDate
            preferences[isBookingMade_KEY] = true
        }
    }
    suspend fun clearCarBookingInformation(){//clear booking data
        context.dataStore.edit { preferences ->
            preferences[carId_Key] = 0
            preferences[carStartDate_KEY] = ""
            preferences[carEndDate_KEY] = ""
            preferences[isBookingMade_KEY] = false
            preferences[carPrice_Key] = 0
        }
    }
}
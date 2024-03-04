package com.nubisuno.countries


import android.app.Application
import android.content.Context
import com.nubisuno.countries.networking.ICountriesAPIService
import com.nubisuno.countries.networking.CountriesAPIService
import com.nubisuno.countries.networking.buildApiService

private const val KEY_PREFERENCES = "countries_preferences"
private const val KEY_TOKEN = "token"

class App: Application(){

    companion object {
        private lateinit var instance: App


        private val preferences by lazy {
            instance.getSharedPreferences(KEY_PREFERENCES, Context.MODE_PRIVATE)
        }

        fun saveToken(token: String) {
            preferences.edit()
                .putString(KEY_TOKEN, token)
                .apply()
        }

        fun getToken() = preferences.getString(KEY_TOKEN, "") ?: ""

        private val companyAPIService by lazy { buildApiService() }
        val countryAPI by lazy { CountriesAPIService(companyAPIService) }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
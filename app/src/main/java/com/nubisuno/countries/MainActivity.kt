package com.nubisuno.countries

import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nubisuno.countries.models.Country
import com.nubisuno.countries.models.RequestFailure
import com.nubisuno.countries.models.RequestSuccess
import com.nubisuno.countries.networking.ICountriesAPIService
import com.nubisuno.countries.networking.NetworkStatusChecker
import com.nubisuno.countries.ui.theme.CountriesTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nubisuno.countries.App.Companion.countryAPI
import com.nubisuno.countries.ui.components.*
import com.nubisuno.countries.ui.theme.CountriesTheme
import com.nubisuno.countries.ui.theme.components.ErrorScreen

class MainActivity : ComponentActivity() {

    private val countryAPI = App.countryAPI
    private val networkStatusChecker by lazy {
        NetworkStatusChecker(getSystemService(ConnectivityManager::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            if(!networkStatusChecker.hasInternetConnection()){
                ErrorScreen("No internet connection")
            }else {
                val navController = rememberNavController()

                CountriesTheme {
                    val countries = produceState<List<Country>?>(initialValue = null) {
                        val result = countryAPI.getAllCountries()
                        // check for failure
                        if (result is RequestFailure) {
                            value = null;
                        }else{
                            value = if (result is RequestSuccess) result.data else listOf()
                        }
                    }

                    if (countries.value == null) {
                        countries.value?.let {
                            CountryErrorScreen(message = "Failed to fetch country data")
                        } ?: run {
                            LoadingScreen(message = "Fetching country data ...")
                        }
                    } else {

                        Surface(modifier = Modifier.fillMaxSize()) {
                            NavHost(navController = navController, startDestination = "countries") {
                                composable("countries") {
                                    CountryInfoList(countries = countries.value!!, navController = navController)
                                }
                                composable("details/{countryName}") { backStackEntry ->
                                    val countryName = backStackEntry.arguments?.getString("countryName")
                                    countryName?.let {
                                        CountryInfoScreen(countryName = it)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
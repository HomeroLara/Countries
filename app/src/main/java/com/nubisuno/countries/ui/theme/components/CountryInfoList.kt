package com.nubisuno.countries.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.nubisuno.countries.models.Country
import androidx.navigation.NavController

@Composable
fun CountryInfoList(
    countries: List<Country>,
    navController: NavController) {
    LazyColumn{
        items(countries.size) { index ->
            CountryInfoRow(
                country = countries[index],
                navController = navController)
        }

    }
}

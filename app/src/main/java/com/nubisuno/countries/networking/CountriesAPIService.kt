package com.nubisuno.countries.networking

import com.nubisuno.countries.models.Country
import com.nubisuno.countries.models.RequestResult
import com.nubisuno.countries.models.RequestSuccess
import com.nubisuno.countries.models.RequestFailure

const val BASE_URL = "https://restcountries.com"

class CountriesAPIService(private val apiService: ICountriesAPIService) {

    suspend fun getAllCountries():  RequestResult<List<Country>> = try {
        val getAllCountriesResponse = apiService.getAllCountries()

        if (getAllCountriesResponse.isEmpty()) {
            RequestFailure(Exception("No countries found"))
        } else {
            RequestSuccess(getAllCountriesResponse)
        }
    } catch (e: Exception) {
        RequestFailure(e)
    }
}
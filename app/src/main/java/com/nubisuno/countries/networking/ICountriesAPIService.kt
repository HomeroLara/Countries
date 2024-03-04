package com.nubisuno.countries.networking

import com.nubisuno.countries.models.Country
import com.nubisuno.countries.models.response.GetAllCountriesResponse
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.*

interface ICountriesAPIService {
     @GET("v3.1/all")
     suspend fun getAllCountries(): List<Country>
}
package com.nubisuno.countries.models.response

import android.os.Parcelable
import com.nubisuno.countries.models.Country
import kotlinx.serialization.Serializable

@Serializable
data class GetAllCountriesResponse(
    val countries: List<Country>
)
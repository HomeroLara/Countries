package com.nubisuno.countries.models

import kotlinx.serialization.Serializable

@Serializable
data class Country(
    val name: CountryName,
    val capital: List<String>? = null,
    val population: Long,
    val area: Double,
    val flags: CountryFlags
) {
    val commonName: String
        get() = name.common

    val flagPng: String
        get() = flags.png
}

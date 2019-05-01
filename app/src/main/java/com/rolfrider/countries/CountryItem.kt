package com.rolfrider.countries

data class CountryItem(val code: String, val name: String) {

    val flagUrl = "https://restcountries.eu/data/$code.svg"
    val countryUrl = "https://restcountries.eu/rest/v2/alpha/$code"
}
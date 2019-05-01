package com.rolfrider.countries

import com.rolfrider.countries.api.Country

data class CountryItem(val code: String, val name: String, val flagUrl: String) {

    constructor(country: Country): this(
        country.alpha3Code,
        country.name,
        country.flag
    )

    val countryUrl = "https://restcountries.eu/rest/v2/alpha/$code"
}
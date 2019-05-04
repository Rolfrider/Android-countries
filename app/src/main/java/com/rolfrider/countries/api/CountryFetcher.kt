package com.rolfrider.countries.api

interface CountryFetcher {
    fun fetchAll(onSuccess: (List<Country>) -> Unit, onError: (String) -> Unit)

    fun fetchCountry(countryCode: String, onSuccess: (CountryDetail) -> Unit, onError: (String) -> Unit)
}
package com.rolfrider.countries.api

interface CountryRepository {
    suspend fun fetchAll(): Result<List<Country>>

    suspend fun fetchCountry(countryCode: String): Result<CountryDetail>
}
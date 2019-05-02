package com.rolfrider.countries.api

import retrofit2.Call
import retrofit2.http.GET

interface CountryApi {

    @GET("/rest/v2/all?fields=name;alpha3Code;flag")
    fun getAllCountries(): Call<List<Country>>
}
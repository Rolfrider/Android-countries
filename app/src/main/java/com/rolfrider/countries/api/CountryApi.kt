package com.rolfrider.countries.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryApi {

    @GET("/rest/v2/all?fields=name;alpha3Code;flag")
    fun getAllCountries(): Call<List<Country>>

    @GET("/rest/v2/alpha/{code}")
    fun getCountryDetail(@Path("code") countryCode: String): Call<CountryDetail>
}
package com.rolfrider.countries.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    fun retrofit(baseUrl: String): Retrofit = Retrofit.Builder()
        .client(OkHttpClient())
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}

object ApiFactory{
    private const val API_BASE_URL = "https://restcountries.eu"

    val countryApi: CountryApi = RetrofitFactory.retrofit(API_BASE_URL).create(CountryApi::class.java)
}
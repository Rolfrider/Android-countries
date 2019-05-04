package com.rolfrider.countries.api

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountryFetcherImpl: CountryFetcher {

    private val retrofit = Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .client(HTTP_CLIENT)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val countryApi = retrofit.create(CountryApi::class.java)

    override fun fetchCountry(countryCode: String, onSuccess: (CountryDetail) -> Unit, onError: (String) -> Unit) {
        val call = countryApi.getCountryDetail(countryCode)
        call.enqueue(object : Callback<CountryDetail> {
            override fun onFailure(call: Call<CountryDetail>, t: Throwable) {
                onError(t.message ?: "Unknown error")
            }

            override fun onResponse(call: Call<CountryDetail>, response: Response<CountryDetail>) {
                if (response.isSuccessful){
                    val country = response.body() ?: return onError("Expected response body")
                    onSuccess(country)
                }else{
                    onError(response.message())
                }
            }
        })
    }

    override fun fetchAll(onSuccess: (List<Country>) -> Unit, onError: (String) -> Unit){
        val call = countryApi.getAllCountries()
        call.enqueue(object : Callback<List<Country>> {
            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                onError(t.message ?: "Unknown error")
            }

            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                if (response.isSuccessful){
                    val countryList = response.body() ?: return onError("Expected response body")
                    onSuccess(countryList)
                }else{
                    onError(response.message())
                }
            }
        })
    }


    companion object {
        const val API_BASE_URL = "https://restcountries.eu"
        val HTTP_CLIENT: OkHttpClient by lazy { OkHttpClient() }
    }
}
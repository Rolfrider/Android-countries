package com.rolfrider.countries.api

import retrofit2.Response

class CountryRepositoryImpl(private val api: CountryApi = ApiFactory.countryApi): CountryRepository {


    override suspend fun fetchAll(): Result<List<Country>> {
        return try {
            val response = api.getAllCountries().await()
            return unwrapBody(response)
        } catch (e: Exception){
            Result.Error(e)
        }
    }

    override suspend fun fetchCountry(countryCode: String): Result<CountryDetail> {
        return try {
            val response = api.getCountryDetail(countryCode).await()
            return unwrapBody(response)
        } catch (e: Exception){
            Result.Error(e)
        }
    }

    private fun <T: Any>unwrapBody(response: Response<T>): Result<T> =
        if (response.body() != null)
            Result.Success(response.body()!!)
        else
            Result.Error(Exception("Empty body"))

}
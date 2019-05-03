package com.rolfrider.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rolfrider.countries.api.CountryFetcher
import com.rolfrider.countries.api.CountryFetcherImpl

class CountriesViewModel(private val countryFetcher: CountryFetcher = CountryFetcherImpl()): ViewModel() {



    private val countryLiveData = MutableLiveData<List<CountryItem>>()
    private var allCountries: List<CountryItem> = emptyList()


    fun countries(): LiveData<List<CountryItem>> = countryLiveData

    fun getCountries(){
        countryFetcher.fetchAll(
            {
                allCountries = it.map(::CountryItem)
                countryLiveData.value = allCountries
            },
            { println(it)}
        )
    }

    fun searchCountry(query: String?){
        if (query.isNullOrBlank()){
            countryLiveData.value = allCountries
        } else {
            countryLiveData.value = allCountries.filter { it.name.contains(query, true)}
        }
    }
}
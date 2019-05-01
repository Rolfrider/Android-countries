package com.rolfrider.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rolfrider.countries.api.CountryFetcher

class CountriesViewModel: ViewModel() {

    private val countryFetcher = CountryFetcher()

    private val countryLiveData = MutableLiveData<List<CountryItem>>()

    fun countries(): LiveData<List<CountryItem>> = countryLiveData

    fun getCountries(name: String?){
        if (name.isNullOrBlank()){
            countryFetcher.fetchAll(
                {countryLiveData.value = it.map(::CountryItem)},
                { println(it)}
            )
        }else{
            //TODO: fetch countries with given name
            countryLiveData.value = listOf(CountryItem("pol", "Poland", "https://restcountries.eu/data/pol.svg"))
        }
    }
}
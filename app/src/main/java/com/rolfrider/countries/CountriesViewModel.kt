package com.rolfrider.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CountriesViewModel: ViewModel() {

    private val countryLiveData = MutableLiveData<List<CountryItem>>()

    fun countries(): LiveData<List<CountryItem>> = countryLiveData

    fun getCountries(name: String?){
        if (name.isNullOrBlank()){
            //TODO: fetch all countries
            countryLiveData.value = (0..12).map { CountryItem("col", "Columbia") }
        }else{
            //TODO: fetch countries with given name
            countryLiveData.value = listOf(CountryItem("pol", "Poland"))
        }
    }
}
package com.rolfrider.countries.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rolfrider.countries.api.CountryFetcher
import com.rolfrider.countries.api.CountryFetcherImpl

class ViewModelFactory: ViewModelProvider.Factory {


    private val countryFetcher: CountryFetcher

    init {
        countryFetcher = CountryFetcherImpl()
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(CountriesViewModel::class.java) -> {
                CountriesViewModel(countryFetcher = countryFetcher) as T
            }
            modelClass.isAssignableFrom(DetailCountryViewModel::class.java) -> {
                DetailCountryViewModel(countryFetcher) as T
            }
            else -> throw IllegalArgumentException("Unknown view model ${modelClass.name}")
        }
    }
}
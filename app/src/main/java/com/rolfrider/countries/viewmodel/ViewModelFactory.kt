package com.rolfrider.countries.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rolfrider.countries.api.CountryRepository
import com.rolfrider.countries.api.CountryRepositoryImpl

class ViewModelFactory: ViewModelProvider.Factory {


    private val countryRepository: CountryRepository

    init {
        countryRepository = CountryRepositoryImpl()
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(CountriesViewModel::class.java) -> {
                CountriesViewModel(countryRepository = countryRepository) as T
            }
            modelClass.isAssignableFrom(DetailCountryViewModel::class.java) -> {
                DetailCountryViewModel(countryRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown view model ${modelClass.name}")
        }
    }
}
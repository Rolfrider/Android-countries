package com.rolfrider.countries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rolfrider.countries.view.countrylist.CountryItem
import com.rolfrider.countries.api.CountryRepository
import com.rolfrider.countries.api.CountryRepositoryImpl
import com.rolfrider.countries.api.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CountriesViewModel(
    private val countryRepository: CountryRepository = CountryRepositoryImpl(),
    private var allCountries: List<CountryItem> = emptyList()
): ViewModel() {

    private val countryLiveData = MutableLiveData<List<CountryItem>>()
    private val errorLiveData = MutableLiveData<String>()


    fun countries() = countryLiveData
    fun error() = errorLiveData

    fun getCountries(){
        viewModelScope.launch{
            when(val result = withContext(Dispatchers.IO){ countryRepository.fetchAll() }){
                is Result.Success -> countryLiveData.value = result.data.map { CountryItem(it) }
                is Result.Error -> errorLiveData.value = result.exception.message
            }
        }
    }

    fun searchCountry(query: String?){
        if (query.isNullOrBlank()){
            countryLiveData.value = allCountries
        } else {
            countryLiveData.value = allCountries.filter { it.name.contains(query, true)}
        }
    }
}
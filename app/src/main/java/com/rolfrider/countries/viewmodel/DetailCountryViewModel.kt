package com.rolfrider.countries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.rolfrider.countries.view.countrydetail.CountryDetailItem
import com.rolfrider.countries.api.CountryRepository
import com.rolfrider.countries.api.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Error
import java.util.ArrayList

class DetailCountryViewModel(private val countryRepository: CountryRepository): ViewModel() {

    private val countryLiveData = MutableLiveData<CountryDetailItem>()
    private val latLngLiveData = MutableLiveData<LatLng>()
    private val errorLiveData = MutableLiveData<String>()

    fun country() = countryLiveData
    fun latLng() = latLngLiveData
    fun error() = errorLiveData

    fun fetchCountry(countryCode: String){
        viewModelScope.launch{

            when(val result = countryRepository.fetchCountry(countryCode)){
                is Result.Success -> {
                    countryLiveData.value = CountryDetailItem(result.data)
                    latLngLiveData.value = parseLatLng(result.data.latlng)
                }
                is Result.Error -> errorLiveData.value = result.exception.message
            }
        }
    }

    private fun parseLatLng(latLng: ArrayList<Double>): LatLng = if (latLng.isEmpty())
        LatLng(0.0, 0.0)
    else
        LatLng(latLng.first(), latLng.last())
}
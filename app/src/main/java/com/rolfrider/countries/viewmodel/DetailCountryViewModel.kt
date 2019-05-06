package com.rolfrider.countries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.rolfrider.countries.view.countrydetail.CountryDetailItem
import com.rolfrider.countries.api.CountryFetcher

class DetailCountryViewModel(private val countryFetcher: CountryFetcher): ViewModel() {

    private val countryLiveData = MutableLiveData<CountryDetailItem>()
    private val latLngLiveData = MutableLiveData<LatLng>()

    fun country() = countryLiveData
    fun latLang() = latLngLiveData

    fun fetchCountry(countryCode: String){
        countryFetcher.fetchCountry(countryCode,
            {
                countryLiveData.value = CountryDetailItem(it)
                latLngLiveData.value = LatLng(it.latlng.first(), it.latlng.last())
            },
            { println(it)}
        )
    }
}
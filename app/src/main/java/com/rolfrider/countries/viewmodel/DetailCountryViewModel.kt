package com.rolfrider.countries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.rolfrider.countries.view.countrydetail.CountryDetailItem
import com.rolfrider.countries.api.CountryFetcher
import java.util.ArrayList

class DetailCountryViewModel(private val countryFetcher: CountryFetcher): ViewModel() {

    private val countryLiveData = MutableLiveData<CountryDetailItem>()
    private val latLngLiveData = MutableLiveData<LatLng>()

    fun country() = countryLiveData
    fun latLng() = latLngLiveData

    fun fetchCountry(countryCode: String){
        countryFetcher.fetchCountry(countryCode,
            {
                countryLiveData.value = CountryDetailItem(it)
                latLngLiveData.value = parseLatLng(it.latlng)
            },
            { println(it)}
        )
    }

    private fun parseLatLng(latLng: ArrayList<Double>): LatLng = if (latLng.isEmpty())
        LatLng(0.0, 0.0)
    else
        LatLng(latLng.first(), latLng.last())
}
package com.rolfrider.countries.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.android.gms.maps.model.LatLng
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.rolfrider.countries.api.CountryDetail
import com.rolfrider.countries.api.CountryFetcher
import com.rolfrider.countries.api.Currency
import com.rolfrider.countries.api.Language
import com.rolfrider.countries.view.countrydetail.CountryDetailItem
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule


class DetailCountryViewModelTest{

    private val countryFetcher = mock<CountryFetcher>()

    @get:Rule// to solve live data assertions, this runs tasks in test thread
    var rule: TestRule = InstantTaskExecutorRule()

    private val countryDetail = CountryDetail(
        "Poland",
        "POL",
        "Warsaw",
        "Eastern Europe",
        36000000,
        arrayListOf(52.0, 20.0),
        312679.0,
        arrayListOf(Currency("Polish złoty", "PLN")),
        arrayListOf(Language("Polish"))
        )

    private val countryDetailItem = CountryDetailItem(
        "Poland",
        "POL",
        "Warsaw",
        "Eastern Europe",
        36000000,
        312679.0,
        arrayListOf(Currency("Polish złoty", "PLN")),
        arrayListOf("Polish")
        )

    private val latLng = LatLng(52.0, 20.0)

    private fun successfulResponse(country: CountryDetail){
        whenever(countryFetcher.fetchCountry(any(), any(), any())).thenAnswer {
            val onSuccess = it.getArgument<((CountryDetail) -> Unit)>(1)
            onSuccess.invoke(country)
        }
    }

    private fun errorResponse(errorMsg: String){
        whenever(countryFetcher.fetchCountry(any(), any(), any())).thenAnswer {
            val onError = it.getArgument<((String) -> Unit)>(2)
            onError.invoke(errorMsg)
        }
    }

    @Test
    fun `view model updates country item on successful response`(){
        val sut = DetailCountryViewModel(countryFetcher)

        successfulResponse(countryDetail)

        sut.fetchCountry("POL")

        Assert.assertEquals(countryDetailItem, sut.country().value)
    }

    @Test
    fun `view model not updates country item on error response`(){
        val sut = DetailCountryViewModel(countryFetcher)

        errorResponse("Some network error")

        sut.fetchCountry("POL")

        Assert.assertNull(sut.country().value)
    }


    @Test
    fun `view model updates latLng value on successful response`(){
        val sut = DetailCountryViewModel(countryFetcher)

        successfulResponse(countryDetail)

        sut.fetchCountry("POL")

        Assert.assertEquals(latLng, sut.latLng().value)
    }

    @Test
    fun `view model not updates latLng value on error response`(){
        val sut = DetailCountryViewModel(countryFetcher)

        errorResponse("Some network error")

        sut.fetchCountry("POL")

        Assert.assertNull(sut.country().value)
    }

    @Test
    fun `view model sets latLng to (0,0) when values not provided`(){
       val noLatLngCountry = CountryDetail(
            "Country with no latLng",
            "XYZ",
            "Capital",
            "Sub region",
            36000000,
            arrayListOf(),
            312679.0,
            arrayListOf(Currency("Name", "XYZ")),
            arrayListOf(Language("Name"))
        )

        val sut = DetailCountryViewModel(countryFetcher)

        successfulResponse(noLatLngCountry)

        sut.fetchCountry("POL")

        Assert.assertEquals(LatLng(0.0, 0.0), sut.latLng().value)
    }
}
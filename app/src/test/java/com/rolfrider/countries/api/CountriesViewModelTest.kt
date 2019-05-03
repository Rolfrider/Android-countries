package com.rolfrider.countries.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import com.rolfrider.countries.CountriesViewModel
import com.rolfrider.countries.CountryItem
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class CountriesViewModelTest{

    private val countryFetcher = mock<CountryFetcher>()
    private val countries = listOf(Country("Poland", "POL", "polFlagImageLink"),
        Country("Germany", "GER", "gerFlagImageLink"))
    private val countryItems = listOf(CountryItem("POL", "Poland", "polFlagImageLink"),
        CountryItem("GER", "Germany", "gerFlagImageLink"))

    @get:Rule// to solve live data assertions, this runs tasks in test thread
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun `view model updates list of countries on successful response`(){
        val sut = CountriesViewModel(countryFetcher)

        whenever(countryFetcher.fetchAll(any(), any())).thenAnswer {
            val onSuccess = it.getArgument<((List<Country>) -> Unit)>(0)
            onSuccess.invoke(countries)
        }

        sut.getCountries()
        Assert.assertEquals(countryItems, sut.countries().value)
    }

    @Test
    fun `view model not updates list of countries on error response`(){
        val sut = CountriesViewModel(countryFetcher)

        whenever(countryFetcher.fetchAll(any(), any())).thenAnswer {
            val onSuccess = it.getArgument<((String) -> Unit)>(1)
            onSuccess.invoke("Some network error")
        }

        sut.getCountries()
        Assert.assertNull(sut.countries().value)
    }


    @Test
    fun `view model calls fetchAll method when getting countries`(){
        val sut = CountriesViewModel(countryFetcher)
        sut.getCountries()
        verify(countryFetcher).fetchAll(any(), any())
    }
}
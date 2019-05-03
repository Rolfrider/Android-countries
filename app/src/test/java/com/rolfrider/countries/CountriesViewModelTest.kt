package com.rolfrider.countries

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import com.rolfrider.countries.api.Country
import com.rolfrider.countries.api.CountryFetcher
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class CountriesViewModelTest{

    private val countryFetcher = mock<CountryFetcher>()
    private val countries = listOf(
        Country("Poland", "POL", "polFlagImageLink"),
        Country("Germany", "GER", "gerFlagImageLink")
    )
    private val countryItems = listOf(CountryItem("POL", "Poland", "polFlagImageLink"),
        CountryItem("GER", "Germany", "gerFlagImageLink"))

    @get:Rule// to solve live data assertions, this runs tasks in test thread
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun `view model returns full list of countries if query is blank`(){
        val sut = CountriesViewModel(allCountries = countryItems)
        val query = " "
        sut.searchCountry(query)
        Assert.assertEquals(countryItems, sut.countries().value)
    }

    @Test
    fun `view model returns single country of name given in query`(){
        val sut = CountriesViewModel(allCountries = countryItems)
        val query = "Poland"
        sut.searchCountry(query)
        Assert.assertEquals(CountryItem("POL", "Poland", "polFlagImageLink"),
            sut.countries().value?.singleOrNull())
    }


    @Test
    fun `view model returns countries that contains given query`(){
        val biggerListOfCountries = countryItems +
            listOf(CountryItem("PLD", "Polandia", "pldFlagImageLink"))
        val sut = CountriesViewModel(allCountries = biggerListOfCountries)
        val query = "Pol"
        sut.searchCountry(query)
        Assert.assertEquals(listOf(CountryItem("POL", "Poland", "polFlagImageLink"),
            CountryItem("PLD", "Polandia", "pldFlagImageLink")),
            sut.countries().value)
    }

    @Test
    fun `view model updates list of countries on successful response`(){
        val sut = CountriesViewModel(countryFetcher = countryFetcher)

        whenever(countryFetcher.fetchAll(any(), any())).thenAnswer {
            val onSuccess = it.getArgument<((List<Country>) -> Unit)>(0)
            onSuccess.invoke(countries)
        }

        sut.getCountries()
        Assert.assertEquals(countryItems, sut.countries().value)
    }

    @Test
    fun `view model not updates list of countries on error response`(){
        val sut = CountriesViewModel(countryFetcher = countryFetcher)

        whenever(countryFetcher.fetchAll(any(), any())).thenAnswer {
            val onSuccess = it.getArgument<((String) -> Unit)>(1)
            onSuccess.invoke("Some network error")
        }

        sut.getCountries()
        Assert.assertNull(sut.countries().value)
    }


    @Test
    fun `view model calls fetchAll method when getting countries`(){
        val sut = CountriesViewModel(countryFetcher = countryFetcher)
        sut.getCountries()
        verify(countryFetcher).fetchAll(any(), any())
    }
}
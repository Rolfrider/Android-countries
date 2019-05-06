package com.rolfrider.countries.view.countrydetail

import com.rolfrider.countries.api.CountryDetail
import com.rolfrider.countries.api.Currency

data class CountryDetailItem(val name: String,
                             val code: String,
                             val capital: String,
                             val subregion: String,
                             val population: Long,
                             val area: Double,
                             val currencies: List<Currency>,
                             val languages: List<String>){
    constructor(country: CountryDetail): this(
        country.name,
        country.alpha3Code,
        country.capital,
        country.subregion,
        country.population,
        country.area,
        country.currencies,
        country.languages.map { it.name }
    )
}
package com.rolfrider.countries.api

import java.util.*

data class CountryDetail(val name: String,
                         val alpha3Code: String,
                         val capital: String,
                         val subregion: String,
                         val population: Long,
                         val latlng: ArrayList<Double>,
                         val area: Double,
                         val currencies: ArrayList<Currency>,
                         val languages: ArrayList<Language>)

data class Currency(val name: String, val code: String)

data class Language(val name: String)
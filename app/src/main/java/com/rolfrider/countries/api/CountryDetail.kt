package com.rolfrider.countries.api

import com.google.gson.annotations.JsonAdapter
import java.util.ArrayList

data class CountryDetail(val name: String,
                         val alpha3Code: String,
                         val capital: String,
                         val subregion: String,
                         val population: Long,
                         val latlng: ArrayList<Double>,
                         val area: Double,
                         val currencies: ArrayList<Currency>,
                         val languages: ArrayList<Language>,
                         val flag: String)

data class Currency(val name: String, val code: String)

data class Language(val name: String)
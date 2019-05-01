package com.rolfrider.countries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rolfrider.countries.recycler.CountryAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: CountriesViewModel by lazy { ViewModelProviders.of(this).get(CountriesViewModel::class.java) }

    private val adapter: CountryAdapter by lazy { CountryAdapter(emptyList()) { println("Clicked")} }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        countryRecyclerView.adapter = adapter
        viewModel.countries().observe(this, Observer(this::updateCountryList))
        viewModel.getCountries(null)
    }

    private fun updateCountryList(countries: List<CountryItem>?){
        if (countries == null ) return
        adapter.items = countries
        adapter.notifyDataSetChanged()
    }
}

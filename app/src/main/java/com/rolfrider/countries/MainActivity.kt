package com.rolfrider.countries

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
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
        viewModel.getCountries()
    }

    private fun updateCountryList(countries: List<CountryItem>?){
        if (countries == null ) return
        adapter.updateItemList(countries)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (searchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchCountry(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchCountry(query)
                return false
            }
        })

        return true
    }


}

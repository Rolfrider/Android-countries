package com.rolfrider.countries.view.countrylist

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rolfrider.countries.viewmodel.CountriesViewModel
import com.rolfrider.countries.R
import com.rolfrider.countries.view.countrydetail.CountryDetailActivity
import com.rolfrider.countries.recycler.CountryAdapter
import com.rolfrider.countries.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: CountriesViewModel by lazy {
        ViewModelProviders.of(this,
            ViewModelFactory())[CountriesViewModel::class.java]
    }

    private val adapter: CountryAdapter by lazy { CountryAdapter(emptyList(), this::handleItemClick) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        countryRecyclerView.adapter = adapter
        viewModel.countries().observe(this, Observer(this::updateCountryList))
        viewModel.error().observe(this, Observer(this::showErrorMessage))
        viewModel.getCountries()
    }

    private fun updateCountryList(countries: List<CountryItem>?){
        if (countries == null ) return
        adapter.updateItemList(countries)
    }

    private fun showErrorMessage(message: String){
        val toastMessage = getString(R.string.error_message) + " : " + message
        Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show()
    }


    private fun handleItemClick(item: CountryItem, sharedView: View){

        val transitionName = getString(R.string.transition_name)

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
            sharedView, transitionName)

        Intent(this, CountryDetailActivity::class.java)
            .putExtra(CountryDetailActivity.EXTRA_KEY, item)
            .let { startActivity(it, options.toBundle()) }
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

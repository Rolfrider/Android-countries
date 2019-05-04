package com.rolfrider.countries.activity

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYouListener
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.rolfrider.countries.CountryDetailItem
import com.rolfrider.countries.CountryItem
import com.rolfrider.countries.R
import com.rolfrider.countries.viewmodel.DetailCountryViewModel
import com.rolfrider.countries.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_country_detail.*
import kotlinx.android.synthetic.main.country_recycle_view_item.flagImageView

class CountryDetailActivity: AppCompatActivity(), OnMapReadyCallback{

    private lateinit var mMap: GoogleMap

    private val viewModel: DetailCountryViewModel by lazy {
        ViewModelProviders.of(this,
            ViewModelFactory()
        )[DetailCountryViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail)
        supportPostponeEnterTransition()

        val countryItem = intent.getParcelableExtra<CountryItem>(EXTRA_KEY)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        viewModel.country().observe(this, Observer(this::updateCountry))
        viewModel.fetchCountry(countryItem.code)

        loadFlagImage(countryItem.flagUrl)
    }

    private fun updateCountry(country: CountryDetailItem){
    }

    private fun loadFlagImage(flagUrl: String){
        GlideToVectorYou.init()
            .with(this)
            .withListener(object : GlideToVectorYouListener{
                override fun onLoadFailed() {
                    flagImageView.setImageDrawable(getDrawable(R.drawable.ic_flag_failed))
                    supportStartPostponedEnterTransition()
                }

                override fun onResourceReady() {
                    supportStartPostponedEnterTransition()
                }

            })
            .load(Uri.parse(flagUrl), flagImageView)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        viewModel.latLang().observe(this, Observer {
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(it, 5f))

        })

    }


    companion object{
        const val EXTRA_KEY = "country_item"
    }

}

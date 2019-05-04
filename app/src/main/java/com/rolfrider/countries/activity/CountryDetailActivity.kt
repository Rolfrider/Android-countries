package com.rolfrider.countries.activity

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYouListener
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.rolfrider.countries.CountryItem
import com.rolfrider.countries.R
import com.rolfrider.countries.viewmodel.DetailCountryViewModel
import com.rolfrider.countries.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.country_recycle_view_item.flagImageView

class CountryDetailActivity: AppCompatActivity(), OnMapReadyCallback{

    private lateinit var map: GoogleMap

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
        loadFlagImage(countryItem.flagUrl)
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
        map = googleMap
        val sydney = LatLng(-34.0, 151.0)
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }


    companion object{
        const val EXTRA_KEY = "country_item"
    }

}

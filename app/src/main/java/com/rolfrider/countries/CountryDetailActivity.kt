package com.rolfrider.countries

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng

class CountryDetailActivity: AppCompatActivity(), OnMapReadyCallback{

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_detail)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment

        mapFragment?.getMapAsync(this)
    }



    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val sydney = LatLng(-34.0, 151.0)
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}

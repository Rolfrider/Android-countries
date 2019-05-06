package com.rolfrider.countries.view.countrylist

import android.os.Parcel
import android.os.Parcelable
import com.rolfrider.countries.api.Country


data class CountryItem(val code: String, val name: String, val flagUrl: String) : Parcelable {

    constructor(country: Country): this(
        country.alpha3Code,
        country.name,
        country.flag
    )

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(code)
        parcel.writeString(name)
        parcel.writeString(flagUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CountryItem> {
        override fun createFromParcel(parcel: Parcel): CountryItem {
            return CountryItem(parcel)
        }

        override fun newArray(size: Int): Array<CountryItem?> {
            return arrayOfNulls(size)
        }
    }
}
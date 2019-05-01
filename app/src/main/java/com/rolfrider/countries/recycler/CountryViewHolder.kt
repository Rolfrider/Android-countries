package com.rolfrider.countries.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rolfrider.countries.CountryItem
import kotlinx.android.synthetic.main.country_recycle_view_item.view.*

class CountryViewHolder(viewItem: View): RecyclerView.ViewHolder(viewItem){
    private val imageView = viewItem.flagImageView
    private val nameView = viewItem.countryNameView

    fun bindItem(countryItem: CountryItem, onItemClick: (CountryItem) -> Unit){
        nameView.text = countryItem.name
        //TODO: Image download
        itemView.setOnClickListener { onItemClick(countryItem) }
    }
}
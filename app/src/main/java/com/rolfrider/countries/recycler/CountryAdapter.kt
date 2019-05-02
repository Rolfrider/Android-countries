package com.rolfrider.countries.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rolfrider.countries.CountryItem
import com.rolfrider.countries.R

class CountryAdapter(
    var items: List<CountryItem>,
    private val onItemClick: (CountryItem) -> Unit
): RecyclerView.Adapter<CountryViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_recycle_view_item,
            parent, false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bindItem(items[position], onItemClick)
    }


}
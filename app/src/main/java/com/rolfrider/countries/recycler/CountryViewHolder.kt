package com.rolfrider.countries.recycler

import android.app.Activity
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.rolfrider.countries.CountryItem
import kotlinx.android.synthetic.main.country_recycle_view_item.view.*

class CountryViewHolder(viewItem: View): RecyclerView.ViewHolder(viewItem){
    private val imageView = viewItem.flagImageView
    private val nameView = viewItem.countryNameView

    fun bindItem(countryItem: CountryItem, onItemClick: (CountryItem) -> Unit){
        nameView.text = countryItem.name
        val activity = itemView.context as Activity
        GlideToVectorYou.
            justLoadImage(activity, Uri.parse(countryItem.flagUrl), imageView)
        itemView.setOnClickListener { onItemClick(countryItem) }
    }
}
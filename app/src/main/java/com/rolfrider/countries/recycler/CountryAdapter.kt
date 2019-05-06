package com.rolfrider.countries.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rolfrider.countries.view.countrylist.CountryItem
import com.rolfrider.countries.R

class CountryAdapter(
    items: List<CountryItem>,
    private val onItemClick: (CountryItem, View) -> Unit
): RecyclerView.Adapter<CountryViewHolder>(){

    private val asyncListDiffer: AsyncListDiffer<CountryItem> = AsyncListDiffer(this, DIFF_CALLBACK)

    init {
        asyncListDiffer.submitList(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_recycle_view_item,
            parent, false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int = asyncListDiffer.currentList.size


    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bindItem(asyncListDiffer.currentList[position], onItemClick)
    }

    fun updateItemList(newItems: List<CountryItem>){
        asyncListDiffer.submitList(newItems)
    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CountryItem>(){
            override fun areItemsTheSame(oldItem: CountryItem, newItem: CountryItem): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: CountryItem, newItem: CountryItem): Boolean =
                oldItem.name == newItem.name

        }
    }

}
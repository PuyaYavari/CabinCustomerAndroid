package com.cabinInformationTechnologies.cabin.fragments.filterDetail.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabin.fragments.filterDetail.CabinCustomerFilterDetailFragment
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterCategory

class CabinCustomerFilterCategoriesAdapter (val fragment: CabinCustomerFilterDetailFragment,
                                            private val myDataset: MutableList<MODELFilterCategory>)
    : RecyclerView.Adapter<CabinCustomerFilterCategoriesAdapter.FilterCategoryViewHolder>(){
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    class FilterCategoryViewHolder(categoryView: View) : RecyclerView.ViewHolder(categoryView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterCategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val categoryView = inflater.inflate(R.layout.cabin_customer_filter_categorybox, parent, false)
        return FilterCategoryViewHolder(
            categoryView
        )
    }

    override fun onBindViewHolder(holder: FilterCategoryViewHolder, position: Int) {
        holder.itemView.apply {
            findViewById<TextView>(R.id.filter_categorybox_category_header_text).text =
                myDataset[position].getName()
        }
        val subCategories = myDataset[position].getSubCategories()
        if (!subCategories.isNullOrEmpty()) {
            val viewManager = LinearLayoutManager(fragment.context)
            val viewAdapter = CabinCustomerFilterCategoriesSubCategoriesAdapter(fragment,subCategories,null)
            holder.itemView.findViewById<RecyclerView>(R.id.filter_categorybox_category_recyclerview).apply {
                setHasFixedSize(true)
                layoutManager = viewManager
                adapter = viewAdapter
            }
        }
    }

    override fun getItemCount(): Int = myDataset.size

    fun getDataset() = this.myDataset
}
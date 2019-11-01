package com.cabinInformationTechnologies.cabin.fragments.filterDetail.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabin.fragments.filterDetail.CabinCustomerFilterDetailContracts
import com.cabinInformationTechnologies.cabin.fragments.filterDetail.CabinCustomerFilterDetailFragment
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterSize
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterSizeGroup

class CabinCustomerFilterSizegroupAdapter(val fragment: CabinCustomerFilterDetailFragment,
                                          private val myDataset: MutableList<MODELFilterSizeGroup>)
    : RecyclerView.Adapter<CabinCustomerFilterSizegroupAdapter.FilterSizegroupViewHolder>() {
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    class FilterSizegroupViewHolder(sizegroupView: View) : RecyclerView.ViewHolder(sizegroupView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterSizegroupViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val sizegroupView = inflater.inflate(R.layout.cabin_customer_filter_sizegroupbox, parent, false)
        return FilterSizegroupViewHolder(
            sizegroupView
        )
    }

    override fun onBindViewHolder(holder: FilterSizegroupViewHolder, position: Int) {
        holder.itemView.apply {
            val sizes = myDataset[position].getSizes()
            if (sizes != null) {
                findViewById<TextView>(R.id.sizegroup_name).text = myDataset[position].getName()
                val viewManager = GridLayoutManager(this.context, 4)
                val viewAdapter = CabinCustomerFilterSizeAdapter(
                    fragment,
                    sizes,
                    object: CabinCustomerFilterDetailContracts.SizesCallback {
                        override fun setSizes(sizes: List<MODELFilterSize>) {
                            val newSizes: MutableList<MODELFilterSize>? = mutableListOf()
                            if (sizes.isNotEmpty()) {
                                newSizes?.addAll(sizes)
                                myDataset[position].setSizes(newSizes)
                            }
                        }
                    }
                )
                val recyclerView = findViewById<RecyclerView>(R.id.sizegroupbox_sizes_recyclerview)
                recyclerView.apply {
                    setHasFixedSize(true)
                    layoutManager = viewManager
                    adapter = viewAdapter
                }
            }
        }
    }

    override fun getItemCount(): Int = myDataset.size

    fun getDataset(): MutableList<MODELFilterSizeGroup> = myDataset
}
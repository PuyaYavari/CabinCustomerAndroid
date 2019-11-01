package com.cabinInformationTechnologies.cabin.fragments.filterDetail.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabin.fragments.filterDetail.CabinCustomerFilterDetailFragment
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterPrice

class CabinCustomerFilterPriceAdapter (val fragment: CabinCustomerFilterDetailFragment,
                                       private val myDataset: MutableList<MODELFilterPrice>)
    : RecyclerView.Adapter<CabinCustomerFilterPriceAdapter.FilterPriceViewHolder>(){
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    class FilterPriceViewHolder(priceView: View) : RecyclerView.ViewHolder(priceView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterPriceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val priceView = inflater.inflate(R.layout.cabin_customer_filter_pricebox, parent, false)
        return FilterPriceViewHolder(
            priceView
        )
    }

    override fun onBindViewHolder(holder: FilterPriceViewHolder, position: Int) {
        holder.itemView.apply {
            findViewById<TextView>(R.id.filter_pricebox_label).text = myDataset[position].getName()
            findViewById<CheckBox>(R.id.filter_pricebox_checkbox).apply{
                isChecked = myDataset[position].isSelected
                setOnCheckedChangeListener { _, isChecked ->
                    myDataset[position].isSelected = isChecked
                }
            }
        }
    }

    override fun getItemCount(): Int = myDataset.size

    fun getDataset(): MutableList<MODELFilterPrice> = myDataset
}
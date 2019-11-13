package com.cabinInformationTechnologies.cabin.fragments.filterDetail.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabin.fragments.filterDetail.CabinCustomerFilterDetailFragment
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterSeller

class CabinCustomerFilterSellerAdapter (val fragment: CabinCustomerFilterDetailFragment,
                                        private var myDataset: MutableList<MODELFilterSeller>)
    : RecyclerView.Adapter<CabinCustomerFilterSellerAdapter.FilterSellerViewHolder>(){
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    class FilterSellerViewHolder(sellerView: View) : RecyclerView.ViewHolder(sellerView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterSellerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val sellerView = inflater.inflate(R.layout.cabin_customer_filter_sellerbox, parent, false)
        return FilterSellerViewHolder(
            sellerView
        )
    }

    override fun onBindViewHolder(holder: FilterSellerViewHolder, position: Int) {
        holder.itemView.apply {
            findViewById<CheckBox>(R.id.filter_sellerbox_checkbox).apply{
                text = myDataset[position].name
                isChecked = myDataset[position].isSelected
                setOnCheckedChangeListener { _, isChecked ->
                    myDataset[position].isSelected = isChecked
                }
            }
        }
    }

    override fun getItemCount(): Int = myDataset.size

    fun getDataset(): MutableList<MODELFilterSeller> = myDataset
    fun setDataset(dataset: MutableList<MODELFilterSeller>) {
        this.myDataset = dataset
        notifyDataSetChanged()
    }
}
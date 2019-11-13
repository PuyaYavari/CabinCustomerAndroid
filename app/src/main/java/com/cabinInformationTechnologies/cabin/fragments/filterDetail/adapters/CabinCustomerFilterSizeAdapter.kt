package com.cabinInformationTechnologies.cabin.fragments.filterDetail.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabin.fragments.filterDetail.CabinCustomerFilterDetailContracts
import com.cabinInformationTechnologies.cabin.fragments.filterDetail.CabinCustomerFilterDetailFragment
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterSize

class CabinCustomerFilterSizeAdapter (val fragment: CabinCustomerFilterDetailFragment,
                                      private val myDataset: List<MODELFilterSize>,
                                      private val sizeCallback: CabinCustomerFilterDetailContracts.SizesCallback)
    : RecyclerView.Adapter<CabinCustomerFilterSizeAdapter.FilterSizeViewHolder>() {
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    class FilterSizeViewHolder(sizeView: View) : RecyclerView.ViewHolder(sizeView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterSizeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val sizeView = inflater.inflate(R.layout.cabin_customer_filter_sizebox, parent, false)
        return FilterSizeViewHolder(
            sizeView
        )
    }

    override fun onBindViewHolder(holder: FilterSizeViewHolder, position: Int) {
        holder.itemView.apply {
            findViewById<ImageView>(R.id.filter_sizebox_background).apply {
                background = if(myDataset[position].isSelected)
                    resources.getDrawable(R.drawable.circle_page_color_light_cabin_color_stroke, fragment.activity?.theme)
                else
                    resources.getDrawable(R.drawable.circle_page_background_color, fragment.activity?.theme)
                setOnClickListener {
                    myDataset[position].isSelected = !myDataset[position].isSelected
                    sizeCallback.setSizes(myDataset)
                    notifyItemChanged(position)
                }
            }
            findViewById<TextView>(R.id.filter_sizebox_label).text = myDataset[position].getName()
            if (myDataset[position].amount < 100)
                findViewById<TextView>(R.id.filter_sizebox_count).text = myDataset[position].amount.toString()
            else
                findViewById<TextView>(R.id.filter_sizebox_count).text = "+99"
        }
    }

    override fun getItemCount(): Int = myDataset.size
}
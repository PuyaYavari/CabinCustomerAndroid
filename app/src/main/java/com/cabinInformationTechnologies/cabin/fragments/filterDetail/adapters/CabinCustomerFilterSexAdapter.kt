package com.cabinInformationTechnologies.cabin.fragments.filterDetail.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabin.fragments.filterDetail.CabinCustomerFilterDetailFragment
import com.cabinInformationTechnologies.cabinCustomerBase.baseAbstracts.Sex
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterSex

class CabinCustomerFilterSexAdapter (val fragment: CabinCustomerFilterDetailFragment,
                                     private var myDataset: MutableList<MODELFilterSex>)
    : RecyclerView.Adapter<CabinCustomerFilterSexAdapter.FilterSexViewHolder>(){
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    class FilterSexViewHolder(sexView: View) : RecyclerView.ViewHolder(sexView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterSexViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val sexView = inflater.inflate(R.layout.cabin_customer_filter_sexbox, parent, false)
        return FilterSexViewHolder(
            sexView
        )
    }

    override fun onBindViewHolder(holder: FilterSexViewHolder, position: Int) {
        holder.itemView.apply {
            if (myDataset[position].getId() == Sex.MAN) //FIXME: LOAD IMAGES FROM SERVER
                findViewById<ImageView>(R.id.filter_sexbox_icon).setImageResource(R.drawable.man_icon_white)
            else
                findViewById<ImageView>(R.id.filter_sexbox_icon).setImageResource(R.drawable.woman_icon_white)
            findViewById<CheckBox>(R.id.filter_sexbox_checkbox).apply{
                text = myDataset[position].getName()
                isChecked = myDataset[position].isSelected
                setOnCheckedChangeListener { _, isChecked ->
                    myDataset[position].isSelected = isChecked
                }
            }
        }
    }

    override fun getItemCount(): Int = myDataset.size

    fun getDataset(): MutableList<MODELFilterSex> = myDataset
    fun setDataset(dataset: MutableList<MODELFilterSex>) {
        this.myDataset = dataset
        notifyDataSetChanged()
    }
}
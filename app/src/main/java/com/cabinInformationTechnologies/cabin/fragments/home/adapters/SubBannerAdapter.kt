package com.cabinInformationTechnologies.cabin.fragments.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabin.fragments.home.CabinCustomerHomeContracts

class SubBannerAdapter (val presenter: CabinCustomerHomeContracts.Presenter?, val position: Int)
    : RecyclerView.Adapter<SubBannerAdapter.SubBannerViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    class SubBannerViewHolder(subBannerView: View) : RecyclerView.ViewHolder(subBannerView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubBannerViewHolder {
        val subBannerView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cabin_customer_sub_banner_box, parent, false)
        return SubBannerViewHolder(subBannerView)
    }

    override fun onBindViewHolder(holder: SubBannerViewHolder, position: Int) {
        if (position == 0) {
            val params = holder.itemView.layoutParams as RecyclerView.LayoutParams
            params.marginStart = 40
            holder.itemView.layoutParams = params
        }
        holder.itemView.findViewById<ImageView>(R.id.sub_banner_image).apply {

            //TODO: SET IMAGE AND ON CLICK AND SET LAYOUT PARAMS IF NEEDED
        }
    }

    override fun getItemCount(): Int = presenter?.myDataset?.get(position)?.getBanners()?.size ?: 0
}
package com.cabinInformationTechnologies.cabin.fragments.home.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabin.fragments.home.CabinCustomerHomeContracts

class CabinCustomerHomeHeaderAdapter(val presenter: CabinCustomerHomeContracts.Presenter?)
    : RecyclerView.Adapter<CabinCustomerHomeHeaderAdapter.HomeHeaderViewHolder>() {

    var selectedPosition: Int = 0

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    class HomeHeaderViewHolder(homeHeaderView: View) : RecyclerView.ViewHolder(homeHeaderView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHeaderViewHolder {
        val homeHeaderView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cabin_customer_home_header_box, parent, false)
        return HomeHeaderViewHolder(homeHeaderView)
    }

    override fun onBindViewHolder(holder: HomeHeaderViewHolder, position: Int) {
        holder.itemView.apply {
            if (position == selectedPosition) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    findViewById<CardView>(R.id.home_header_box_layout).setCardBackgroundColor(
                        resources.getColor(R.color.colorPrimaryDark, context.theme)
                    )
                } else {
                    findViewById<CardView>(R.id.home_header_box_layout).setCardBackgroundColor(
                        resources.getColor(R.color.colorPrimaryDark)
                    )
                }
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    findViewById<CardView>(R.id.home_header_box_layout).setCardBackgroundColor(
                        resources.getColor(R.color.colorButtonUnselectedLabelPrimary, context.theme)
                    )
                } else {
                    findViewById<CardView>(R.id.home_header_box_layout).setCardBackgroundColor(
                        resources.getColor(R.color.colorButtonUnselectedLabelPrimary)
                    )
                }
            }
            findViewById<TextView>(R.id.home_header_box_text).text = presenter?.headers?.get(position)?.getText()
            setOnClickListener {
                notifyItemChanged(selectedPosition)
                selectedPosition = position
                presenter?.myDataset = presenter?.headers?.get(position)?.getSubBanners() ?: mutableListOf()
            }
        }
    }

    override fun getItemCount(): Int = presenter?.headers?.size ?: 0
}
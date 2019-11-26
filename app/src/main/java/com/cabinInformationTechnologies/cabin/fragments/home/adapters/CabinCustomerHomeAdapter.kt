package com.cabinInformationTechnologies.cabin.fragments.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabin.fragments.home.BannerGroupTypeIDs
import com.cabinInformationTechnologies.cabin.fragments.home.CabinCustomerHomeContracts

class CabinCustomerHomeAdapter(val presenter: CabinCustomerHomeContracts.Presenter?)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    class SubBannerGroupViewHolder(subBannerView: View) : RecyclerView.ViewHolder(subBannerView)
    class CommercialViewHolder(commercialView: View) : RecyclerView.ViewHolder(commercialView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            BannerGroupTypeIDs.SUB -> {
                val subBannerView = inflater.inflate(R.layout.cabin_customer_home_sub_banner_group_box, parent, false)
                SubBannerGroupViewHolder(subBannerView)
            }
            BannerGroupTypeIDs.COMMERCIAL -> {
                val commercialView = inflater.inflate(R.layout.cabin_customer_commercial_banner_box, parent, false)
                CommercialViewHolder(commercialView)
            }
            else -> throw IllegalStateException("unsupported item type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            BannerGroupTypeIDs.SUB -> {
                holder.itemView.apply {
                    findViewById<TextView>(R.id.sub_banner_group_header).apply {
                        if (presenter?.myDataset?.get(position)?.getText() != null) {
                            visibility = View.VISIBLE
                            text = presenter.myDataset[position].getText()
                        } else {
                            visibility = View.GONE
                            text = null
                        }
                    }
                    findViewById<RecyclerView>(R.id.sub_banner_group_recycler_view).apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
                        adapter = SubBannerAdapter(presenter, position)
                    }
                }
            }
            BannerGroupTypeIDs.COMMERCIAL -> {
                holder.itemView.apply {
                    findViewById<TextView>(R.id.commercial_banner_group_header).apply {
                        if (presenter?.myDataset?.get(position)?.getText() != null) {
                            visibility = View.VISIBLE
                            text = presenter.myDataset[position].getText()
                        } else {
                            visibility = View.GONE
                            text = null
                        }
                    }
                    //TODO: SET PAGER LAYOUT PARAMS IF NEEDED
                    val inflater = LayoutInflater.from(context)
                    findViewById<ViewPager>(R.id.commercial_banner_pager)
                        .adapter = CommercialPagerAdapter(presenter?.myDataset?.get(position)?.getBanners(), inflater)
                }
            }
        }
    }

    override fun getItemCount(): Int = presenter?.myDataset?.size ?: 0

    override fun getItemViewType(position: Int): Int {
        return if (presenter?.myDataset?.get(position)?.isCommercial() == true)
            BannerGroupTypeIDs.COMMERCIAL
        else
            BannerGroupTypeIDs.SUB
    }
}
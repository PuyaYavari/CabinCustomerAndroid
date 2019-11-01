package com.cabinInformationTechnologies.cabin.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.cabinInformationTechnologies.cabin.MainContracts
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.BaseFragment

class NoInternetAdapter (val view: BaseFragment,
                         private val noInternetCallback: MainContracts.NoInternetCallback):
    RecyclerView.Adapter<NoInternetAdapter.NoInternetViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class NoInternetViewHolder(noInternetView: View) : RecyclerView.ViewHolder(noInternetView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoInternetViewHolder {
        // create a new view
        val noInternetView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cabin_customer_blocker_layout, parent, false) as View
        // set the view's size, margins, paddings and layout parameters

        return NoInternetViewHolder(
            noInternetView
        )
    }

    override fun onBindViewHolder(holder: NoInternetViewHolder, position: Int) {
        holder.itemView.findViewById<Button>(R.id.no_internet_button).setOnClickListener {
            noInternetCallback.retry()
        }
    }

    override fun getItemCount(): Int = 1
}
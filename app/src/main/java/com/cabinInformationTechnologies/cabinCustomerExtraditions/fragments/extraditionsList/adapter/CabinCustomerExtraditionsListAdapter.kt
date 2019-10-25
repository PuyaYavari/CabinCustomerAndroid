package com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.cabinInformationTechnologies.cabin.R
import kotlinx.android.synthetic.main.cabin_customer_extraditions_extradition_view.view.*

class CabinCustomerExtraditionsListAdapter (val fragment: com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList.CabinCustomerExtraditionsListContracts.View,
                                            private val myDataset: List<com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList.CabinCustomerExtraditionsListContracts.ExtraditionBox>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    class NoExtraditionViewHolder(noExtraditionView: View) : RecyclerView.ViewHolder(noExtraditionView)
    class ExtraditionViewHolder(extraditionView: View) : RecyclerView.ViewHolder(extraditionView)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        // create a new view
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList.adapter.ExtraditionBoxTypeIDs.Companion.NO_EXTRADITION -> {
                val boxView = inflater.inflate(R.layout.cabin_customer_extraditions_no_extradition_view, parent, false)
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList.adapter.CabinCustomerExtraditionsListAdapter.NoExtraditionViewHolder(
                    boxView
                )
            }
            com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList.adapter.ExtraditionBoxTypeIDs.Companion.EXTRADITION -> {
                val boxView =
                    inflater.inflate(R.layout.cabin_customer_extraditions_extradition_view, parent, false)
                com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList.adapter.CabinCustomerExtraditionsListAdapter.ExtraditionViewHolder(
                    boxView
                )
            }
            else -> throw IllegalStateException("unsupported item type")
        }
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        when (getItemViewType(position)) {
            com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList.adapter.ExtraditionBoxTypeIDs.Companion.NO_EXTRADITION -> {
                val holder = viewHolder as com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList.adapter.CabinCustomerExtraditionsListAdapter.NoExtraditionViewHolder
                holder.itemView.findViewById<Button>(R.id.no_extraditions_add_extradition_button)
                    .setOnClickListener { fragment.addExtraditionListener() }
            }
            com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList.adapter.ExtraditionBoxTypeIDs.Companion.EXTRADITION -> {
                val holder = viewHolder as com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList.adapter.CabinCustomerExtraditionsListAdapter.ExtraditionViewHolder
                when {
                    myDataset[position].getStatusID() == com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList.adapter.ExtraditionStatusIDs.Companion.DENIED -> holder.itemView.extraditionbox_layout.setOnClickListener { fragment.moveToExtraditionDetailDenied() }
                    myDataset[position].getStatusID() == com.cabinInformationTechnologies.cabinCustomerExtraditions.fragments.extraditionsList.adapter.ExtraditionStatusIDs.Companion.ACCEPTED -> holder.itemView.extraditionbox_layout.setOnClickListener { fragment.moveToExtraditionDetailAccepted() }
                    else -> holder.itemView.extraditionbox_layout.setOnClickListener { fragment.moveToExtraditionDetail() }
                }
            }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size

    override fun getItemViewType(position: Int): Int {
        return myDataset[position].getType()
    }
}
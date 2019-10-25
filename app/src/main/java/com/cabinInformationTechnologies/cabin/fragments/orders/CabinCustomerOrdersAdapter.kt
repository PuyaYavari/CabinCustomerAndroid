package com.cabinInformationTechnologies.cabin.fragments.orders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cabinInformationTechnologies.cabin.R
import kotlinx.android.synthetic.main.cabin_customer_orderbox_view.view.*

class CabinCustomerOrdersAdapter(val view: CabinCustomerOrdersContracts.FragmentsView
                                 , private val myDataset: Array<String>) :
    RecyclerView.Adapter<CabinCustomerOrdersAdapter.OrdersViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class OrdersViewHolder(orderboxView: View) : RecyclerView.ViewHolder(orderboxView)


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): OrdersViewHolder {
        // create a new view
        val orderboxView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cabin_customer_orderbox_view, parent, false) as View
        // set the view's size, margins, paddings and layout parameters

        return OrdersViewHolder(
            orderboxView
        )
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.itemView.order_count.text = myDataset[position]
        holder.itemView.order_background.setOnClickListener { view.orderboxOnClickListener() }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size
}

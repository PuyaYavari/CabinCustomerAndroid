package com.cabinInformationTechnologies.cabin.fragments.orders

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELOrder

class CabinCustomerOrdersAdapter(val view: CabinCustomerOrdersContracts.FragmentsView,
                                 val manager: CabinCustomerOrdersContracts.FragmentsManager,
                                 val myDataset: MutableList<MODELOrder?>,
                                 currentPage: Int) :
    RecyclerView.Adapter<CabinCustomerOrdersAdapter.OrdersViewHolder>() {

    private var currentOrdersPageID: Int = currentPage

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class OrdersViewHolder(orderboxView: View) : RecyclerView.ViewHolder(orderboxView)


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

    @Suppress("DEPRECATION")
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        holder.itemView.apply {
            findViewById<TextView>(R.id.order_count).text = myDataset[position]?.productCount.toString()
            findViewById<TextView>(R.id.order_price).text = myDataset[position]?.getPrice().toString()
            findViewById<TextView>(R.id.order_date).text =
                "${myDataset[position]?.getOrderDate()?.year}/" +
                        "${myDataset[position]?.getOrderDate()?.month}/" +
                        "${myDataset[position]?.getOrderDate()?.date}"
            findViewById<TextView>(R.id.order_datetime).text = myDataset[position]?.getOrderTime()
            findViewById<TextView>(R.id.order_payment_type).text = myDataset[position]?.getPaymentType()
            when (currentOrdersPageID) {
                PagesIDs.PENDING_PAGE -> {

                    findViewById<ConstraintLayout>(R.id.order_background).setOnClickListener {
                        val order = myDataset[position]
                        if (order != null)
                            view.moveToOrderDetail(order)
                    }
                }
                PagesIDs.SHIPPING_PAGE -> {
                    findViewById<ConstraintLayout>(R.id.order_background).setOnClickListener {
                        val order = myDataset[position]
                        if (order != null)
                            view.moveToOrderDetail(order)
                    }
                }
                PagesIDs.SENT_PAGE -> {
                    findViewById<ConstraintLayout>(R.id.order_background).setOnClickListener {
                        val order = myDataset[position]
                        if (order != null)
                            view.moveToOrderDetail(order)
                    }
                }
                else -> {
                    findViewById<TextView>(R.id.order_count).text = ""
                    findViewById<TextView>(R.id.order_price).text = ""
                    findViewById<TextView>(R.id.order_date).text = ""
                    findViewById<TextView>(R.id.order_datetime).text = ""
                    findViewById<TextView>(R.id.order_payment_type).text = ""
                    findViewById<ConstraintLayout>(R.id.order_background).setOnClickListener { }
                    val context = view.getActivityContext()
                    if (context != null)
                        Logger.failure(
                            context,
                            this::class.java.name,
                            "Unknown page!",
                            null
                        )
                }
            }
        }
        //TODO: PAGING
    }

    override fun getItemCount(): Int {
        return myDataset.size
    }

    fun setOrdersPageTo(page: Int) {
        if (page > -1 && page < 3) {
            currentOrdersPageID = page
            notifyDataSetChanged()
        } else {
            val context = view.getActivityContext()
            if (context != null)
                Logger.warn(
                    context,
                    this::class.java.name,
                    "Unknown page!",
                    null
                )
        }
    }

    fun notifyNewData() {
        when(currentOrdersPageID) {
            PagesIDs.PENDING_PAGE -> myDataset.addAll(manager.orders.pending)
            PagesIDs.SHIPPING_PAGE -> myDataset.addAll(manager.orders.shipped)
            PagesIDs.SENT_PAGE -> myDataset.addAll(manager.orders.sent)
        }
        notifyDataSetChanged()
    }
}

package com.cabinInformationTechnologies.cabin.fragments.orders

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.Constants
import com.cabinInformationTechnologies.cabinCustomerBase.Logger

class CabinCustomerOrdersAdapter(val view: CabinCustomerOrdersContracts.FragmentsView,
                                 val manager: CabinCustomerOrdersContracts.FragmentsManager,
                                 currentPage: Int) :
    RecyclerView.Adapter<CabinCustomerOrdersAdapter.OrdersViewHolder>() {

    private var currentOrdersPageID: Int = currentPage
    private var lastAddedPage: Int = 1

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
            when (currentOrdersPageID) {
                PagesIDs.PENDING_PAGE -> {
                    findViewById<TextView>(R.id.order_count).text = manager.orders.pending[position]?.productCount.toString()
                    findViewById<TextView>(R.id.order_price).text = manager.orders.pending[position]?.getPrice().toString()
                    findViewById<TextView>(R.id.order_date).text =
                        "${manager.orders.pending[position]?.getOrderDate()?.year}/" +
                        "${manager.orders.pending[position]?.getOrderDate()?.month}/" +
                        "${manager.orders.pending[position]?.getOrderDate()?.date}"
                    findViewById<TextView>(R.id.order_datetime).text = manager.orders.pending[position]?.getOrderTime()
                    findViewById<TextView>(R.id.order_payment_type).text = manager.orders.pending[position]?.getPaymentType()
                    findViewById<ConstraintLayout>(R.id.order_background).setOnClickListener {
                        val order = manager.orders.pending[position]
                        if (order != null)
                            view.moveToOrderDetail(order)
                    }
                }
                PagesIDs.SHIPPING_PAGE -> {
                    findViewById<TextView>(R.id.order_count).text = manager.orders.shipped[position]?.productCount.toString()
                    findViewById<TextView>(R.id.order_price).text = manager.orders.shipped[position]?.getPrice().toString()
                    findViewById<TextView>(R.id.order_date).text =
                        "${manager.orders.shipped[position]?.getOrderDate()?.year}/" +
                        "${manager.orders.shipped[position]?.getOrderDate()?.month}/" +
                        "${manager.orders.shipped[position]?.getOrderDate()?.date}"
                    findViewById<TextView>(R.id.order_datetime).text = manager.orders.shipped[position]?.getOrderTime()
                    findViewById<TextView>(R.id.order_payment_type).text = manager.orders.shipped[position]?.getPaymentType()
                    findViewById<ConstraintLayout>(R.id.order_background).setOnClickListener {
                        val order = manager.orders.shipped[position]
                        if (order != null)
                            view.moveToOrderDetail(order)
                    }
                }
                PagesIDs.SENT_PAGE -> {
                    findViewById<TextView>(R.id.order_count).text = manager.orders.sent[position]?.productCount.toString()
                    findViewById<TextView>(R.id.order_price).text = manager.orders.sent[position]?.getPrice().toString()
                    findViewById<TextView>(R.id.order_date).text =
                        "${manager.orders.sent[position]?.getOrderDate()?.year}/" +
                        "${manager.orders.sent[position]?.getOrderDate()?.month}/" +
                        "${manager.orders.sent[position]?.getOrderDate()?.date}"
                    findViewById<TextView>(R.id.order_datetime).text = manager.orders.sent[position]?.getOrderTime()
                    findViewById<TextView>(R.id.order_payment_type).text = manager.orders.sent[position]?.getPaymentType()
                    findViewById<ConstraintLayout>(R.id.order_background).setOnClickListener {
                        val order = manager.orders.sent[position]
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
        if (position == (Constants.ORDERS_PAGE_SIZE * manager.currentPage)-1) {//FIXME: PROPER PAGING
            manager.getNewPage(manager.currentPage + 1, this)
        }
    }

    override fun getItemCount(): Int {
        return when (currentOrdersPageID) {
            PagesIDs.PENDING_PAGE -> manager.orders.pending.size
            PagesIDs.SHIPPING_PAGE -> manager.orders.shipped.size
            PagesIDs.SENT_PAGE -> manager.orders.sent.size
            else -> -1
        }
    }

    fun notifyNewPage() {
        notifyDataSetChanged()
        lastAddedPage = manager.currentPage
    }

    fun checkData() {
        if (lastAddedPage != manager.currentPage)
            notifyNewPage()
    }
}

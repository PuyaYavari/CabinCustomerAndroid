package com.cabinInformationTechnologies.cabin.fragments.orders

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELOrder
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELOrders
import kotlinx.android.synthetic.main.cabin_customer_orderbox_view.view.*

class CabinCustomerOrdersAdapter(val view: CabinCustomerOrdersContracts.FragmentsView,
                                 private val pendingDataset: MutableList<MODELOrder>,
                                 private val shippedDataset: MutableList<MODELOrder>,
                                 private val sentDataset: MutableList<MODELOrder>) :
    RecyclerView.Adapter<CabinCustomerOrdersAdapter.OrdersViewHolder>() {

    private var currentOrdersPageID: Int = 0

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

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        holder.itemView.order_background.setOnClickListener { view.orderboxOnClickListener() }

        holder.itemView.apply {
            when (currentOrdersPageID) {
                0 -> {
                    findViewById<TextView>(R.id.order_count).text = pendingDataset[position].productCount.toString()
                    findViewById<TextView>(R.id.order_price).text = pendingDataset[position].getPrice().toString()
                    findViewById<TextView>(R.id.order_date).text =
                        "${pendingDataset[position].getOrderDate()?.year}/" +
                        "${pendingDataset[position].getOrderDate()?.month}/" +
                        "${pendingDataset[position].getOrderDate()?.date}"
                    findViewById<TextView>(R.id.order_datetime).text = pendingDataset[position].getOrderTime()
                    findViewById<TextView>(R.id.order_payment_type).text = pendingDataset[position].getPaymentType()
                }
                1 -> {
                    findViewById<TextView>(R.id.order_count).text = shippedDataset[position].productCount.toString()
                    findViewById<TextView>(R.id.order_price).text = shippedDataset[position].getPrice().toString()
                    findViewById<TextView>(R.id.order_date).text =
                        "${shippedDataset[position].getOrderDate()?.year}/" +
                        "${shippedDataset[position].getOrderDate()?.month}/" +
                        "${shippedDataset[position].getOrderDate()?.date}"
                    findViewById<TextView>(R.id.order_datetime).text = shippedDataset[position].getOrderTime()
                    findViewById<TextView>(R.id.order_payment_type).text = shippedDataset[position].getPaymentType()
                }
                2 -> {
                    findViewById<TextView>(R.id.order_count).text = sentDataset[position].productCount.toString()
                    findViewById<TextView>(R.id.order_price).text = sentDataset[position].getPrice().toString()
                    findViewById<TextView>(R.id.order_date).text =
                        "${sentDataset[position].getOrderDate()?.year}/" +
                        "${sentDataset[position].getOrderDate()?.month}/" +
                        "${sentDataset[position].getOrderDate()?.date}"
                    findViewById<TextView>(R.id.order_datetime).text = sentDataset[position].getOrderTime()
                    findViewById<TextView>(R.id.order_payment_type).text = sentDataset[position].getPaymentType()
                }
                else -> {
                    findViewById<TextView>(R.id.order_count).text = ""
                    findViewById<TextView>(R.id.order_price).text = ""
                    findViewById<TextView>(R.id.order_date).text = ""
                    findViewById<TextView>(R.id.order_datetime).text = ""
                    findViewById<TextView>(R.id.order_payment_type).text = ""
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
    }

    override fun getItemCount(): Int {
        when (currentOrdersPageID) {
            0 -> return pendingDataset.size
            1 -> return shippedDataset.size
            2 -> return sentDataset.size
            else -> {
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
        return -1
    }

    fun setOrdersPageTo(page: Int) {
        if (page > -1 && page < 3)
            currentOrdersPageID = page
        else {
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

    fun addOrders(orders: MODELOrders) {
        if (!orders.pending.isNullOrEmpty())
            orders.pending.forEach {
                if (it != null)
                    pendingDataset.add(it)
            }
        if (!orders.shipped.isNullOrEmpty())
            orders.shipped.forEach {
                if (it != null)
                    shippedDataset.add(it)
            }
        if (!orders.sent.isNullOrEmpty())
            orders.sent.forEach {
                if (it != null)
                    sentDataset.add(it)
            }
        notifyDataSetChanged()
    }
}

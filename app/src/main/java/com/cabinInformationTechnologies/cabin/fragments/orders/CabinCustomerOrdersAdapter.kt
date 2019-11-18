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
                                 private val pendingDataset: MutableList<MODELOrder?>,
                                 private val shippedDataset: MutableList<MODELOrder?>,
                                 private val sentDataset: MutableList<MODELOrder?>) :
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

    @Suppress("DEPRECATION")
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        holder.itemView.apply {
            when (currentOrdersPageID) {
                PagesIDs.PENDING_PAGE -> {
                    findViewById<TextView>(R.id.order_count).text = pendingDataset[position]?.productCount.toString()
                    findViewById<TextView>(R.id.order_price).text = pendingDataset[position]?.getPrice().toString()
                    findViewById<TextView>(R.id.order_date).text =
                        "${pendingDataset[position]?.getOrderDate()?.year}/" +
                        "${pendingDataset[position]?.getOrderDate()?.month}/" +
                        "${pendingDataset[position]?.getOrderDate()?.date}"
                    findViewById<TextView>(R.id.order_datetime).text = pendingDataset[position]?.getOrderTime()
                    findViewById<TextView>(R.id.order_payment_type).text = pendingDataset[position]?.getPaymentType()
                    findViewById<ConstraintLayout>(R.id.order_background).setOnClickListener { }//TODO
                }
                PagesIDs.SHIPPING_PAGE -> {
                    findViewById<TextView>(R.id.order_count).text = shippedDataset[position]?.productCount.toString()
                    findViewById<TextView>(R.id.order_price).text = shippedDataset[position]?.getPrice().toString()
                    findViewById<TextView>(R.id.order_date).text =
                        "${shippedDataset[position]?.getOrderDate()?.year}/" +
                        "${shippedDataset[position]?.getOrderDate()?.month}/" +
                        "${shippedDataset[position]?.getOrderDate()?.date}"
                    findViewById<TextView>(R.id.order_datetime).text = shippedDataset[position]?.getOrderTime()
                    findViewById<TextView>(R.id.order_payment_type).text = shippedDataset[position]?.getPaymentType()
                    findViewById<ConstraintLayout>(R.id.order_background).setOnClickListener { }//TODO
                }
                PagesIDs.SENT_PAGE -> {
                    findViewById<TextView>(R.id.order_count).text = sentDataset[position]?.productCount.toString()
                    findViewById<TextView>(R.id.order_price).text = sentDataset[position]?.getPrice().toString()
                    findViewById<TextView>(R.id.order_date).text =
                        "${sentDataset[position]?.getOrderDate()?.year}/" +
                        "${sentDataset[position]?.getOrderDate()?.month}/" +
                        "${sentDataset[position]?.getOrderDate()?.date}"
                    findViewById<TextView>(R.id.order_datetime).text = sentDataset[position]?.getOrderTime()
                    findViewById<TextView>(R.id.order_payment_type).text = sentDataset[position]?.getPaymentType()
                    findViewById<ConstraintLayout>(R.id.order_background).setOnClickListener { }//TODO
                }
                else -> {
                    findViewById<TextView>(R.id.order_count).text = ""
                    findViewById<TextView>(R.id.order_price).text = ""
                    findViewById<TextView>(R.id.order_date).text = ""
                    findViewById<TextView>(R.id.order_datetime).text = ""
                    findViewById<TextView>(R.id.order_payment_type).text = ""
                    findViewById<ConstraintLayout>(R.id.order_background).setOnClickListener { }//TODO
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
        if (manager.currentPage == 0)
            manager.getNewPage(1, this)
        //TODO: PAGING
    }

    override fun getItemCount(): Int {
        when (currentOrdersPageID) {
            PagesIDs.PENDING_PAGE -> return pendingDataset.size
            PagesIDs.SHIPPING_PAGE -> return shippedDataset.size
            PagesIDs.SENT_PAGE -> return sentDataset.size
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
            PagesIDs.PENDING_PAGE -> pendingDataset.addAll(manager.orders.pending)
            PagesIDs.SHIPPING_PAGE -> shippedDataset.addAll(manager.orders.shipped)
            PagesIDs.SENT_PAGE -> sentDataset.addAll(manager.orders.sent)
        }
        notifyDataSetChanged()
    }
}

package com.cabinInformationTechnologies.cabin.fragments.ordersDetail.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabin.fragments.ordersDetail.CabinCustomerOrdersDetailContracts
import kotlinx.android.synthetic.main.cabin_customer_orders_detail_orderbox_view.view.*


class CabinCustomerOrdersDetailAdapter(val fragment: com.cabinInformationTechnologies.cabinCustomerBase.BaseFragment, private val myDataset: List<CabinCustomerOrdersDetailContracts.Detailbox>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    class OrdersDetailHeaderViewHolder(orderdetailheaderView: View) : RecyclerView.ViewHolder(orderdetailheaderView)
    class OrdersDetailCargoViewHolder(orderdetailcargoView: View) : RecyclerView.ViewHolder(orderdetailcargoView)
    class OrdersDetailViewHolder(orderboxView: View) : RecyclerView.ViewHolder(orderboxView)
    class OrdersDetailFooterViewHolder(orderdetailfooterView: View) : RecyclerView.ViewHolder(orderdetailfooterView)


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecyclerView.ViewHolder {
        // create a new view
        val inflater = LayoutInflater.from(parent.context)

        when (viewType) {
            OrdersListItemsTypeID.ORDERBOX_TYPE -> {
                val boxView = inflater.inflate(R.layout.cabin_customer_orders_detail_orderbox_view, parent, false)
                return OrdersDetailViewHolder(boxView)
            }
            OrdersListItemsTypeID.CARGOBOX_TYPE -> {
                val boxView = inflater.inflate(R.layout.cabin_customer_orders_detail_cargobox_view, parent, false)
                return OrdersDetailCargoViewHolder(boxView)
            }
            OrdersListItemsTypeID.HEADERBOX_TYPE -> {
                val boxView = inflater.inflate(R.layout.cabin_customer_orders_detail_headerbox_view, parent, false)
                return OrdersDetailHeaderViewHolder(boxView)
            }
            OrdersListItemsTypeID.FOOTERBOX_TYPE -> {
                val boxView = inflater.inflate(R.layout.cabin_customer_orders_detail_footerbox_view, parent, false)
                return OrdersDetailFooterViewHolder(boxView)
            }
            else -> throw IllegalStateException("unsupported item type")
        }
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        var prevType = -1
        if (position != 0) {
            prevType = getItemViewType(position - 1)
        } else {
            val param = viewHolder.itemView.layoutParams as RecyclerView.LayoutParams
            param.topMargin = 20
            viewHolder.itemView.layoutParams = param
        }

        when (viewType) {
            OrdersListItemsTypeID.ORDERBOX_TYPE -> {
                val holder = viewHolder as OrdersDetailViewHolder
                holder.itemView.orders_detail_ordercount_label.text = position.toString()
                if(prevType == -1 || prevType == OrdersListItemsTypeID.FOOTERBOX_TYPE) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        holder.itemView.orders_detail_orderbox_layout.background =
                            fragment.context?.getDrawable(R.drawable.orders_detailbox_left_top_right_borders_with_separator)
                    }
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        holder.itemView.orders_detail_orderbox_layout.background =
                            fragment.context?.getDrawable(R.drawable.orders_detailbox_left_right_borders_with_bottom_separator)
                    }
                }
                prevType = OrdersListItemsTypeID.ORDERBOX_TYPE
            }
            OrdersListItemsTypeID.CARGOBOX_TYPE -> {
                val holder = viewHolder as OrdersDetailCargoViewHolder
                prevType = OrdersListItemsTypeID.CARGOBOX_TYPE
            }
            OrdersListItemsTypeID.HEADERBOX_TYPE -> {
                val holder = viewHolder as OrdersDetailHeaderViewHolder
                prevType = OrdersListItemsTypeID.HEADERBOX_TYPE
            }
            OrdersListItemsTypeID.FOOTERBOX_TYPE -> {
                val holder = viewHolder as OrdersDetailFooterViewHolder
                prevType = OrdersListItemsTypeID.FOOTERBOX_TYPE
            }
            else -> throw IllegalStateException("unsupported item type")
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size

    override fun getItemViewType(position: Int): Int {
        return myDataset[position].getType()
    }

}
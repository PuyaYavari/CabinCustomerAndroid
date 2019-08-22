package ist.cabin.cabinCustomerProfileOptions.fragments.addressOptions.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ist.cabin.cabinCustomerProfileOptions.fragments.addressOptions.CabinCustomerAddressOptionsContracts
import ist.cabin.cabincustomer.R

class CabinCustomerAddressOptionsAdapter (val fragment: CabinCustomerAddressOptionsContracts.View,
                                          private val myDataset: List<CabinCustomerAddressOptionsContracts.Addressbox>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    class NoAddressViewHolder(noAddressView: View) : RecyclerView.ViewHolder(noAddressView)
    class AddressViewHolder(addressView: View) : RecyclerView.ViewHolder(addressView)
    class TaxInvoiceAddressViewHolder(taxinvoiceAddressView: View) : RecyclerView.ViewHolder(taxinvoiceAddressView)


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        // create a new view
        val inflater = LayoutInflater.from(parent.context)

        when (viewType) {
            AddressesListTypeID.NO_ADDRESS_TYPE -> {
                val boxView = inflater.inflate(R.layout.cabin_customer_address_options_no_address_view, parent, false)
                return NoAddressViewHolder(boxView)
            }
            AddressesListTypeID.ADDRESS_TYPE -> {
                val boxView =
                    inflater.inflate(R.layout.cabin_customer_address_options_addressbox_view, parent, false)
                return AddressViewHolder(boxView)
            }
            AddressesListTypeID.TAX_INVOICE_ADDRESS_TYPE -> {
                val boxView =
                    inflater.inflate(R.layout.cabin_customer_address_options_invoice_addressbox_view, parent, false)
                return TaxInvoiceAddressViewHolder(boxView)
            }
            else -> throw IllegalStateException("unsupported item type")
        }
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        val viewType = getItemViewType(position)

        when (viewType) {
            AddressesListTypeID.NO_ADDRESS_TYPE -> {
                val holder = viewHolder as NoAddressViewHolder
                if(myDataset[position].getAddressTypeID() == AddressTypeID.DELIVERY) {
                    holder.itemView.findViewById<TextView>(R.id.address_options_no_address_text).text =
                        fragment.getActivityContext()?.resources?.getText(R.string.no_delivery_address_text)
                    holder.itemView.findViewById<TextView>(R.id.address_options_no_address_hint).text =
                        fragment.getActivityContext()?.resources?.getText(R.string.no_delivery_address_hint)
                    holder.itemView.findViewById<Button>(R.id.address_options_no_address_add_button).text =
                        fragment.getActivityContext()?.resources?.getText(R.string.add_delivery_address)
                    holder.itemView.findViewById<Button>(R.id.address_options_no_address_add_button)
                        .setOnClickListener { fragment.addDeliveryAddressListener() }
                } else {
                    holder.itemView.findViewById<TextView>(R.id.address_options_no_address_text).text =
                        fragment.getActivityContext()?.resources?.getText(R.string.no_invoice_address_text)
                    holder.itemView.findViewById<TextView>(R.id.address_options_no_address_hint).text =
                        fragment.getActivityContext()?.resources?.getText(R.string.no_invoice_address_hint)
                    holder.itemView.findViewById<Button>(R.id.address_options_no_address_add_button).text =
                        fragment.getActivityContext()?.resources?.getText(R.string.add_invoice_address)
                    holder.itemView.findViewById<Button>(R.id.address_options_no_address_add_button)
                        .setOnClickListener { fragment.addInvoiceAddressListener() }
                }
            }
            AddressesListTypeID.ADDRESS_TYPE -> {
                val holder = viewHolder as AddressViewHolder
                if(myDataset[position].getAddressTypeID() == AddressTypeID.DELIVERY) {
                    holder.itemView.findViewById<LinearLayout>(R.id.address_options_delivery_addressbox_layout)
                        .setOnClickListener { fragment.addDeliveryAddressListener() }
                } else {
                    holder.itemView.findViewById<LinearLayout>(R.id.address_options_delivery_addressbox_layout)
                        .setOnClickListener { fragment.addInvoiceAddressListener() }
                }
            }
            AddressesListTypeID.TAX_INVOICE_ADDRESS_TYPE -> {
                val holder = viewHolder as TaxInvoiceAddressViewHolder
                holder.itemView.findViewById<LinearLayout>(R.id.address_options_invoice_addressbox_layout)
                    .setOnClickListener { fragment.addInvoiceAddressListener() }
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
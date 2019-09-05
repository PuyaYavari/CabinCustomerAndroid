package ist.cabin.cabinCustomerExtraditions.fragments.addExtraditionCurrentItemsList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ist.cabin.cabincustomer.R
import kotlinx.android.synthetic.main.cabin_customer_extraditions_current_products_list_productbox.view.*

class CabinCustomerAddExtraditionCurrentItemsListAdapter(val view: CabinCustomerAddExtraditionCurrentItemsListContracts.View,
                                                         private val myDataset: List<String>):
    RecyclerView.Adapter<CabinCustomerAddExtraditionCurrentItemsListAdapter.CurrentProductsViewHolder>(){

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class CurrentProductsViewHolder(currentProductView: View) : RecyclerView.ViewHolder(currentProductView)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): CurrentProductsViewHolder {
        // create a new view
        val currentProductView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cabin_customer_extraditions_current_products_list_productbox, parent, false) as View
        // set the view's size, margins, paddings and layout parameters

        return CurrentProductsViewHolder(
            currentProductView
        )
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: CurrentProductsViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.itemView.extraditions_current_products_list_productbox_product_price.text = myDataset[position].toString()
        holder.itemView.extraditions_current_products_list_productbox_layout.setOnClickListener {
            holder.itemView.extraditions_current_products_list_productbox_checkbox.isChecked =
                !holder.itemView.extraditions_current_products_list_productbox_checkbox.isChecked
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size

}
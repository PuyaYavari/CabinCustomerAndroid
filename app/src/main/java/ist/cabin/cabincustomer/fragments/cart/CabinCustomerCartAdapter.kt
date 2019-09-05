package ist.cabin.cabincustomer.fragments.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ist.cabin.cabincustomer.R
import kotlinx.android.synthetic.main.cabin_customer_cart_productbox.view.*

class CabinCustomerCartAdapter (val view: CabinCustomerCartContracts.View,
                                private val myDataset: List<CabinCustomerCartContracts.Product>):
    RecyclerView.Adapter<CabinCustomerCartAdapter.CartViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class CartViewHolder(productboxView: View) : RecyclerView.ViewHolder(productboxView)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): CartViewHolder {
        // create a new view
        val orderboxView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cabin_customer_cart_productbox, parent, false) as View
        // set the view's size, margins, paddings and layout parameters

        return CartViewHolder(
            orderboxView
        )
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.itemView.cart_productbox_product_count.text = myDataset[position].count.toString()
        holder.itemView.cart_productbox_add_button.setOnClickListener {
            myDataset[position].count += 1
            holder.itemView.cart_productbox_product_count.text = myDataset[position].count.toString()
        }

        holder.itemView.cart_productbox_subtract_button.setOnClickListener {
            myDataset[position].count -= 1
            holder.itemView.cart_productbox_product_count.text = myDataset[position].count.toString()
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size

}
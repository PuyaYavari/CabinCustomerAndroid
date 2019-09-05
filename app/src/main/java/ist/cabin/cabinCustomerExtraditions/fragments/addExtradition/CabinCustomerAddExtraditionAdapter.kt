package ist.cabin.cabinCustomerExtraditions.fragments.addExtradition

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ist.cabin.cabincustomer.R
import kotlinx.android.synthetic.main.cabin_customer_extraditions_add_extradition_product_view.view.*

class CabinCustomerAddExtraditionAdapter(val view: CabinCustomerAddExtraditionContracts.View,
                                         private val myDataset: List<CabinCustomerAddExtraditionContracts.Product>):
    RecyclerView.Adapter<CabinCustomerAddExtraditionAdapter.ExtraditionProductViewHolder>() {

    var previousSelected = -1
    var selected: Int = -1

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class ExtraditionProductViewHolder(extraditionProductView: View) : RecyclerView.ViewHolder(extraditionProductView)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ExtraditionProductViewHolder {
        // create a new view
        val currentProductView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cabin_customer_extraditions_add_extradition_product_view, parent, false) as View
        // set the view's size, margins, paddings and layout parameters

        return ExtraditionProductViewHolder(
            currentProductView
        )
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ExtraditionProductViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Glide.with(view.getApplicationContext()).load(view.getApplicationContext().resources
            .getDrawable(myDataset[position].getProductImage(), view.getApplicationContext().theme))
            .circleCrop().into(holder.itemView.cabin_customer_extraditions_add_extradition_product_view_image)

        if (position == selected) {
            holder.itemView.cabin_customer_extraditions_add_extradition_product_view_image.apply {
                val params = layoutParams
                params.height = (70 * context.resources.displayMetrics.density).toInt()
                params.width = (70 * context.resources.displayMetrics.density).toInt()
                layoutParams = params
            }
        } else {
            holder.itemView.cabin_customer_extraditions_add_extradition_product_view_image.apply {
                val params = layoutParams
                params.height = (60 * context.resources.displayMetrics.density).toInt()
                params.width = (60 * context.resources.displayMetrics.density).toInt()
                layoutParams = params
            }
        }

        holder.itemView.cabin_customer_extraditions_add_extradition_product_view_image.setOnClickListener {
            previousSelected = selected
            selected = position
            notifyItemChanged(previousSelected)
            notifyItemChanged(position)

            view.setupERListener(myDataset[position])
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size
}
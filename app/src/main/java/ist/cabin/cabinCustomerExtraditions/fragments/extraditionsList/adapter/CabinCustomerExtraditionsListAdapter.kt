package ist.cabin.cabinCustomerExtraditions.fragments.extraditionsList.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import ist.cabin.cabinCustomerExtraditions.fragments.extraditionsList.CabinCustomerExtraditionsListContracts
import ist.cabin.cabincustomer.R
import kotlinx.android.synthetic.main.cabin_customer_extraditions_extradition_view.view.*

class CabinCustomerExtraditionsListAdapter (val fragment: CabinCustomerExtraditionsListContracts.View,
                                            private val myDataset: List<CabinCustomerExtraditionsListContracts.ExtraditionBox>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    class NoExtraditionViewHolder(noExtraditionView: View) : RecyclerView.ViewHolder(noExtraditionView)
    class ExtraditionViewHolder(extraditionView: View) : RecyclerView.ViewHolder(extraditionView)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        // create a new view
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            ExtraditionBoxTypeIDs.NO_EXTRADITION -> {
                val boxView = inflater.inflate(R.layout.cabin_customer_extraditions_no_extradition_view, parent, false)
                NoExtraditionViewHolder(boxView)
            }
            ExtraditionBoxTypeIDs.EXTRADITION -> {
                val boxView =
                    inflater.inflate(R.layout.cabin_customer_extraditions_extradition_view, parent, false)
                ExtraditionViewHolder(boxView)
            }
            else -> throw IllegalStateException("unsupported item type")
        }
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {

        when (getItemViewType(position)) {
            ExtraditionBoxTypeIDs.NO_EXTRADITION -> {
                val holder = viewHolder as NoExtraditionViewHolder
                holder.itemView.findViewById<Button>(R.id.no_extraditions_add_extradition_button)
                    .setOnClickListener { fragment.addExtraditionListener() }
            }
            ExtraditionBoxTypeIDs.EXTRADITION -> {
                val holder = viewHolder as ExtraditionViewHolder
                when {
                    myDataset[position].getStatusID() == ExtraditionStatusIDs.DENIED -> holder.itemView.extraditionbox_layout.setOnClickListener { fragment.moveToExtraditionDetailDenied() }
                    myDataset[position].getStatusID() == ExtraditionStatusIDs.ACCEPTED -> holder.itemView.extraditionbox_layout.setOnClickListener { fragment.moveToExtraditionDetailAccepted() }
                    else -> holder.itemView.extraditionbox_layout.setOnClickListener { fragment.moveToExtraditionDetail() }
                }
            }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size

    override fun getItemViewType(position: Int): Int {
        return myDataset[position].getType()
    }
}
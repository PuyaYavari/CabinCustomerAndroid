package ist.cabin.cabincustomer.fragments.filterDetail.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Outline
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ist.cabin.cabinCustomerBase.models.local.MODELRawColor
import ist.cabin.cabincustomer.R
import ist.cabin.cabincustomer.fragments.filterDetail.CabinCustomerFilterDetailFragment

class CabinCustomerFilterColorsAdapter (val fragment: CabinCustomerFilterDetailFragment,
                                        private val myDataset: MutableList<MODELRawColor>)
    : RecyclerView.Adapter<CabinCustomerFilterColorsAdapter.FilterColorViewHolder>() {
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    class FilterColorViewHolder(colorView: View) : RecyclerView.ViewHolder(colorView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterColorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val colorView = inflater.inflate(R.layout.cabin_customer_filter_colorbox, parent, false)
        return FilterColorViewHolder(
            colorView
        )
    }

    override fun onBindViewHolder(holder: FilterColorViewHolder, position: Int) {
        holder.itemView.apply {
            findViewById<ImageView>(R.id.filter_colorbox_color_sample).apply {
                imageTintList = ColorStateList.valueOf(Color.parseColor(myDataset[position].hexCode))
                outlineProvider = object : ViewOutlineProvider() {
                    override fun getOutline(view: View?, outline: Outline?) {
                        if (view != null &&  outline != null)
                            outline.setOval(10,15,view.width-10,view.height-15)
                    }
                }
            }
            findViewById<TextView>(R.id.filter_colorbox_color_name).text = myDataset[position].getName()
        }
    }

    override fun getItemCount(): Int = myDataset.size
}
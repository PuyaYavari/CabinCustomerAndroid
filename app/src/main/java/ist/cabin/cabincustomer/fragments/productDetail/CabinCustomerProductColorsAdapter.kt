package ist.cabin.cabincustomer.fragments.productDetail

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ist.cabin.cabincustomer.R

class CabinCustomerProductColorsAdapter(val view: CabinCustomerProductDetailContracts.View,
                                        private val myDataset: List<List<String>>):
    RecyclerView.Adapter<CabinCustomerProductColorsAdapter.ProductColorViewHolder>(){

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class ProductColorViewHolder(productColorView: View) : RecyclerView.ViewHolder(productColorView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductColorViewHolder {
        // create a new view
        val productColorView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cabin_customer_product_detail_colorbox, parent, false) as View
        // set the view's size, margins, paddings and layout parameters

        return ProductColorViewHolder(
            productColorView
        )
    }

    override fun onBindViewHolder(holder: ProductColorViewHolder, position: Int) {
        holder.itemView.apply {
            findViewById<ImageView>(R.id.colorbox_color_sample).imageTintList =
                ColorStateList.valueOf(Color.parseColor(myDataset[position][0]))
            findViewById<TextView>(R.id.colorbox_color_name).text = myDataset[position][1]
        }
    }

    override fun getItemCount(): Int = myDataset.size
}
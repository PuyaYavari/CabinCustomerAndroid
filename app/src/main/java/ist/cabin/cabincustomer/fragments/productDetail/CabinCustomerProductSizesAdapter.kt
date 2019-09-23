package ist.cabin.cabincustomer.fragments.productDetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ist.cabin.cabinCustomerBase.models.local.MODELSize
import ist.cabin.cabincustomer.R

class CabinCustomerProductSizesAdapter(val view: CabinCustomerProductDetailContracts.View,
                                       private var myDataset: List<MODELSize>):
    RecyclerView.Adapter<CabinCustomerProductSizesAdapter.ProductSizeViewHolder>(),
    CabinCustomerProductDetailContracts.SizeAdapter{

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class ProductSizeViewHolder(productSizeView: View) : RecyclerView.ViewHolder(productSizeView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductSizeViewHolder {
        // create a new view
        val productSizeView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cabin_customer_product_detail_sizebox, parent, false) as View
        // set the view's size, margins, paddings and layout parameters

        return ProductSizeViewHolder(
            productSizeView
        )
    }

    override fun onBindViewHolder(holder: ProductSizeViewHolder, position: Int) {
        holder.itemView.apply {
            findViewById<TextView>(R.id.product_detail_product_sizebox_text).text = myDataset[position].name
            findViewById<CheckBox>(R.id.product_detail_product_sizebox_checkbox)
                .setOnCheckedChangeListener { _, isChecked ->
                if(isChecked)
                    view.setSelectedSize(myDataset[position])
                else
                    view.setSelectedSize(null)
            }
        }
    }

    override fun getItemCount(): Int = myDataset.size

    override fun setDataset(sizes: List<MODELSize>) {
        myDataset = sizes
        notifyDataSetChanged()
    }
}
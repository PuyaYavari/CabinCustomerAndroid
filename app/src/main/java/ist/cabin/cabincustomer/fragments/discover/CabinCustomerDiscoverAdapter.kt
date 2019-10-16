package ist.cabin.cabincustomer.fragments.discover

import android.graphics.Outline
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import ist.cabin.cabinCustomerBase.models.local.MODELProduct
import ist.cabin.cabincustomer.R

class CabinCustomerDiscoverAdapter (val fragment: CabinCustomerDiscoverContracts.View,
                                    private val myDataset: MutableList<MODELProduct>)
    : RecyclerView.Adapter<CabinCustomerDiscoverAdapter.DiscoverProductViewHolder>(){

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    class DiscoverProductViewHolder(productView: View) : RecyclerView.ViewHolder(productView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val productView = inflater.inflate(R.layout.cabin_customer_discover_productbox, parent, false)
        return DiscoverProductViewHolder(productView)
    }

    override fun onBindViewHolder(holder: DiscoverProductViewHolder, position: Int) {
        val data = myDataset[position]
        holder.itemView.apply {
            findViewById<ToggleButton>(R.id.discover_productbox_favourite_button).outlineProvider = object : ViewOutlineProvider() {
                override fun getOutline(view: View?, outline: Outline?) {
                    if (view != null &&  outline != null)
                        outline.setOval(-1,0,view.width+1,view.height+4)
                }
            }
            findViewById<ImageView>(R.id.discover_productbox_product_image).apply {
                val params = layoutParams
                params.height = params.width * 4/3
                layoutParams = params
                setOnClickListener { fragment.moveToProductDetail(myDataset[position], position) }
//                setImageDrawable() TODO
            }
            findViewById<TextView>(R.id.discover_productbox_seller_name).text = data.getSellerName()
            findViewById<TextView>(R.id.discover_productbox_product_name).text = data.getProductName()
            findViewById<TextView>(R.id.discover_productbox_product_price).text = data.getPrice().toString()

            findViewById<ToggleButton>(R.id.discover_productbox_favourite_button).isClickable = false //FIXME: ISCLICKABLE!?
            data.getColors().forEach {
                if (it.favourite)
                    findViewById<ToggleButton>(R.id.discover_productbox_favourite_button).isChecked = true //FIXME: NON FAV CAN BE INDICATED AS FAV
            }
            //TODO: Add discount
        }
    }

    override fun getItemCount() = myDataset.size

    fun updateProduct(product: MODELProduct, position: Int) {
        //TODO: REMOVE IF PRODUCT FINISHED
        myDataset[position] = product
        notifyItemChanged(position)
    }
}
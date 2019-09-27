package ist.cabin.cabincustomer.fragments.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ist.cabin.cabinCustomerBase.models.local.MODELProduct
import ist.cabin.cabincustomer.R

class CabinCustomerFavoritesAdapter (val fragment: CabinCustomerFavoritesContracts.View,
                                     private val myDataset: List<MODELProduct>)
    : RecyclerView.Adapter<CabinCustomerFavoritesAdapter.FavoritesProductViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    class FavoritesProductViewHolder(productView: View) : RecyclerView.ViewHolder(productView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val productView = inflater.inflate(R.layout.cabin_customer_favorites_productbox, parent, false)
        return FavoritesProductViewHolder(productView)
    }

    override fun onBindViewHolder(holder: FavoritesProductViewHolder, position: Int) {
        val data = myDataset[position]
        holder.itemView.apply {
            findViewById<ImageView>(R.id.favorites_productbox_image).apply {
                setOnClickListener { fragment.moveToProductDetail(myDataset[position]) }
            } //TODO:SET IMAGE
            findViewById<TextView>(R.id.favorites_productbox_seller_name).text = data.sellerName
            findViewById<TextView>(R.id.favorites_productbox_product_before_discount_price) //TODO
            findViewById<TextView>(R.id.favorites_productbox_product_before_discount_price_unit) //TODO
            findViewById<TextView>(R.id.favorites_productbox_product_price).text = data.price.toString()
            findViewById<TextView>(R.id.favorites_productbox_product_price_unit) //TODO
            findViewById<Button>(R.id.favorites_productbox_add_to_cart_button).setOnClickListener {
                //TODO:CALL VIEWS AddToCart THE CORRECT WAY
            }
        }
    }

    override fun getItemCount(): Int = myDataset.size
}
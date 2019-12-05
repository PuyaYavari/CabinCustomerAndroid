package com.cabinInformationTechnologies.cabin.fragments.favorites

import android.graphics.Outline
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.Visualizer
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSize

class CabinCustomerFavoritesAdapter (val fragment: CabinCustomerFavoritesContracts.View,
                                     private var myDataset: MutableList<MODELProduct>)
    : RecyclerView.Adapter<CabinCustomerFavoritesAdapter.FavoritesProductViewHolder>() {

    private var lastRemovedProduct: MODELProduct? = null
    private var lastRemovedProductPosition: Int? = null

    private var selectedSize : MODELSize? = null

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
        val product = myDataset[position]
        holder.itemView.apply {
            findViewById<ImageView>(R.id.favorites_productbox_image).apply {
                val params = layoutParams
                params.height = params.width * 4/3
                layoutParams = params
                setOnClickListener { fragment.moveToProductDetail(myDataset[position]) }

                val visualizer = Visualizer()
                visualizer.productImageVisualizer(holder.itemView.context,product,this)


            } //TODO:SET IMAGE
            findViewById<TextView>(R.id.favorites_productbox_seller_name).text = product.getSellerName()

            val discountedPrice =  product.getDiscountedPrice()
            if (discountedPrice == null) {
                findViewById<TextView>(R.id.favorites_productbox_product_before_discount_price).text = ""
                findViewById<TextView>(R.id.favorites_productbox_product_before_discount_price_unit).text = ""
                findViewById<LinearLayout>(R.id.favorites_productbox_product_before_discount_price_layout).visibility = View.GONE

                findViewById<TextView>(R.id.favorites_productbox_product_price).text = product.getPrice().toString()
                findViewById<TextView>(R.id.favorites_productbox_product_price_unit).text = product.getPriceUnit()
                findViewById<LinearLayout>(R.id.favorites_productbox_product_price_layout).visibility = View.VISIBLE
            } else {
                findViewById<TextView>(R.id.favorites_productbox_product_before_discount_price).text = product.getPrice().toString()
                findViewById<TextView>(R.id.favorites_productbox_product_before_discount_price_unit).text = product.getPriceUnit()
                findViewById<LinearLayout>(R.id.favorites_productbox_product_before_discount_price_layout).visibility = View.VISIBLE

                findViewById<TextView>(R.id.favorites_productbox_product_price).text = discountedPrice.toString()
                findViewById<TextView>(R.id.favorites_productbox_product_price_unit).text = product.getPriceUnit()
                findViewById<LinearLayout>(R.id.favorites_productbox_product_price_layout).visibility = View.VISIBLE
            }

            findViewById<ImageButton>(R.id.favorites_productbox_favourite_button)
                .outlineProvider = object : ViewOutlineProvider() {
                override fun getOutline(view: View?, outline: Outline?) {
                    if (view != null &&  outline != null)
                        outline.setOval(-4, 0, view.width + 4, view.height + 8)
                }
            }
            findViewById<ImageButton>(R.id.favorites_productbox_favourite_button)
                .setOnClickListener { removeProduct(position) }
            findViewById<Button>(R.id.favorites_productbox_add_to_cart_button).setOnClickListener {
                fragment.showSelectSize(
                    product,
                    product.getColors()[0],
                    object : com.cabinInformationTechnologies.cabin.MainContracts.SelectSizeCallback {
                        override fun selectSize(size: MODELSize) {
                            selectedSize = size
                        }

                        override fun confirm() {
                            val size = selectedSize
                            if (size != null) {
                                fragment.addToCart(1, product.getId(), product.getColors()[0], size)
                                selectedSize = null
                            }
                        }
                    }
                )
            }
        }
    }

    override fun getItemCount(): Int = myDataset.size

    fun setData(products: List<MODELProduct>) {
        this.myDataset = products as MutableList<MODELProduct>
        notifyDataSetChanged()
    }

    private fun removeProduct(position: Int) {
        try {
            lastRemovedProduct = myDataset[position]
            lastRemovedProductPosition = position
            fragment.removeFromFavorites(myDataset[position])
            myDataset.remove(myDataset[position])
            notifyItemRemoved(position)
        } catch (exception: Exception) {
            val context = fragment.getActivityContext()
            if (context != null)
                com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(
                    context,
                    this::class.java.name,
                    "Error while removing.",
                    exception)
            fragment.renewData()
        }
    }

    fun undoLastRemove() {
        val position = lastRemovedProductPosition
        val item = lastRemovedProduct
        if (position != null && item != null) {
            myDataset.add(position, item)
            notifyItemInserted(position)
        }
    }
}
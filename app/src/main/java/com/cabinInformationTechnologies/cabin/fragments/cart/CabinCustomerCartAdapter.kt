package com.cabinInformationTechnologies.cabin.fragments.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.Visualizer
import kotlinx.android.synthetic.main.cabin_customer_cart_productbox.view.*

class CabinCustomerCartAdapter (val view: CabinCustomerCartContracts.View,
                                private val myDataset: List<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct>):
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
        val product = myDataset[position]
        holder.itemView.apply {
            cart_productbox_product_image.apply {
                val visualizer = Visualizer()
                visualizer.productImageVisualizer(holder.itemView.context,product,this)

                setOnClickListener { view.moveToProductDetail(product, product.getColors()[0]) }
            }
            cart_productbox_seller_name.text = product.getSellerName()
            cart_productbox_product_name.text = product.getProductName()
            cart_productbox_product_id.text = product.getProductId()
            cart_productbox_size.text = product.getColors()[0].sizes[0].name
            //TODO:SHOW cart_productbox_size_caution_layout
            cart_productbox_product_color.text = product.getColors()[0].name
            cart_productbox_product_cargo_date.text = product.getCargoDuration()


            val discountedPrice = product.getDiscountedPrice()
            if (discountedPrice == null) {
                cart_productbox_product_before_discount_price.text = ""
                cart_productbox_product_before_discount_price_unit.text = ""
                cart_productbox_product_before_discount_price_layout.visibility = View.GONE

                cart_productbox_product_price.text = product.getPrice().toString()
                cart_productbox_product_price_unit.text = product.getPriceUnit()
                cart_productbox_product_price_layout.visibility = View.VISIBLE
            } else {
                cart_productbox_product_before_discount_price.text = product.getPrice().toString()
                cart_productbox_product_before_discount_price_unit.text = product.getPriceUnit()
                cart_productbox_product_before_discount_price_layout.visibility = View.VISIBLE

                cart_productbox_product_price.text = discountedPrice.toString()
                cart_productbox_product_price_unit.text = product.getPriceUnit()
                cart_productbox_product_price_layout.visibility = View.VISIBLE
            }

            cart_productbox_product_count.text = product.getAmount().toString()
            cart_productbox_add_button.setOnClickListener {
                val newProduct = myDataset[position]
                newProduct.incAmount(1)
                view.updateProduct(newProduct)
            }
            cart_productbox_subtract_button.setOnClickListener {
                val newProduct = myDataset[position]
                newProduct.decAmount(1)
                view.updateProduct(newProduct)
            }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size

}
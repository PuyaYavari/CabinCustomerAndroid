package com.cabinInformationTechnologies.cabin.fragments.discover

import android.graphics.Outline
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.Visualizer
import com.cabinInformationTechnologies.cabinCustomerBase.baseAbstracts.ImageSizes
import kotlinx.android.synthetic.main.cabin_customer_discover_productbox.view.*

class CabinCustomerDiscoverAdapter(val presenter: CabinCustomerDiscoverContracts.Presenter?) :
    RecyclerView.Adapter<CabinCustomerDiscoverAdapter.DiscoverProductViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    class DiscoverProductViewHolder(productView: View) : RecyclerView.ViewHolder(productView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val productView =
            inflater.inflate(R.layout.cabin_customer_discover_productbox, parent, false)
        return DiscoverProductViewHolder(productView)
    }

    override fun onBindViewHolder(holder: DiscoverProductViewHolder, position: Int) {
        val product = presenter?.myDataset?.get(position)
        holder.itemView.apply {
            findViewById<ToggleButton>(R.id.discover_productbox_favourite_button).outlineProvider =
                object : ViewOutlineProvider() {
                    override fun getOutline(view: View?, outline: Outline?) {
                        if (view != null && outline != null)
                            outline.setOval(-1, 0, view.width + 1, view.height + 4)
                    }
                }


            val favButton = findViewById<ToggleButton>(R.id.discover_productbox_favourite_button)

            findViewById<ImageView>(R.id.discover_productbox_product_image).apply {
                val params = layoutParams
                params.height = params.width * 4 / 3
                layoutParams = params
                setOnClickListener {
                    presenter?.moveToProductDetail(
                        presenter.myDataset[position],
                        position
                    )
                }

                val visualizer = Visualizer()
                if(product != null) {
                    visualizer.productImageVisualizer(holder.itemView.context,product,this)
                } else {
                    Logger.warn(
                        holder.itemView.context,
                        this::class.java.name,
                        "Problem in visualizing product image.",
                        null
                    )
                }
               
            }

            if (product?.getColors()?.size == 1) {
                favButton.isClickable = true
                favButton.setOnClickListener {
                    favButton.isChecked = !favButton.isChecked
                }
            } else {
                favButton.isClickable = false //FIXME: ISCLICKABLE!?
            }


            product?.getColors()?.forEach {
                if (it.favourite)
                    favButton.isChecked = true //FIXME: NON FAV CAN BE INDICATED AS FAV
            }

            findViewById<TextView>(R.id.discover_productbox_seller_name).text =
                product?.getSellerName()
            findViewById<TextView>(R.id.discover_productbox_product_name).text =
                product?.getProductName()
            findViewById<TextView>(R.id.discover_productbox_product_price).text =
                product?.getPrice().toString()


            val discountedPrice = presenter?.myDataset?.get(position)?.getDiscountedPrice()
            if (discountedPrice == null) {
                discover_productbox_product_before_discount_price.text = ""
                discover_productbox_product_before_discount_price_unit.text = ""
                discover_productbox_product_before_discount_price_layout.visibility = View.GONE

                discover_productbox_product_price.text =
                    presenter?.myDataset?.get(position)?.getPrice().toString()
                discover_productbox_product_price_unit.text =
                    presenter?.myDataset?.get(position)?.getPriceUnit()
                discover_productbox_product_price_layout.visibility = View.VISIBLE
            } else {
                discover_productbox_product_before_discount_price.text =
                    presenter?.myDataset?.get(position)?.getPrice().toString()
                discover_productbox_product_before_discount_price_unit.text =
                    presenter?.myDataset?.get(position)?.getPriceUnit()
                discover_productbox_product_before_discount_price_layout.visibility = View.VISIBLE

                discover_productbox_product_price.text = discountedPrice.toString()
                discover_productbox_product_price_unit.text =
                    presenter?.myDataset?.get(position)?.getPriceUnit()
                discover_productbox_product_price_layout.visibility = View.VISIBLE
            }
        }
    }

    override fun getItemCount() = presenter?.myDataset?.size ?: 0

    fun updateProduct(
        product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct,
        position: Int
    ) {
        //TODO: REMOVE IF PRODUCT FINISHED
        presenter?.myDataset?.set(position, product)
        notifyItemChanged(position)
    }
}
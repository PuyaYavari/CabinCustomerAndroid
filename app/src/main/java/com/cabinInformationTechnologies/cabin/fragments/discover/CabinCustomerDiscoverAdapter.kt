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
import com.cabinInformationTechnologies.cabin.R

class CabinCustomerDiscoverAdapter (val presenter: CabinCustomerDiscoverContracts.Presenter?)
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
        val data = presenter?.myDataset?.get(position)
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
                setOnClickListener { presenter?.moveToProductDetail(presenter.myDataset[position], position) }
//                setImageDrawable() TODO
            }
            findViewById<TextView>(R.id.discover_productbox_seller_name).text = data?.getSellerName()
            findViewById<TextView>(R.id.discover_productbox_product_name).text = data?.getProductName()
            findViewById<TextView>(R.id.discover_productbox_product_price).text = data?.getPrice().toString()

            findViewById<ToggleButton>(R.id.discover_productbox_favourite_button).isClickable = false //FIXME: ISCLICKABLE!?
            data?.getColors()?.forEach {
                if (it.favourite)
                    findViewById<ToggleButton>(R.id.discover_productbox_favourite_button).isChecked = true //FIXME: NON FAV CAN BE INDICATED AS FAV
            }
            //TODO: Add discount
        }
    }

    override fun getItemCount() = presenter?.myDataset?.size ?: 0

    fun updateProduct(product: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELProduct, position: Int) {
        //TODO: REMOVE IF PRODUCT FINISHED
        presenter?.myDataset?.set(position, product)
        notifyItemChanged(position)
    }
}
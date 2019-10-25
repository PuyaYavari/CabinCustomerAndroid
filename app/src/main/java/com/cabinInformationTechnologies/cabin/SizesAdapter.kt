package com.cabinInformationTechnologies.cabin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SizesAdapter(val view: com.cabinInformationTechnologies.cabin.MainContracts.View,
                   private var myDataset: List<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSize>,
                   private val callback: com.cabinInformationTechnologies.cabin.MainContracts.SelectSizeCallback):
    RecyclerView.Adapter<SizesAdapter.ProductSizeViewHolder>(){
    var selectedSize = -1

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
            if (position == selectedSize)
                findViewById<ImageView>(R.id.product_detail_product_sizebox_background).visibility = View.VISIBLE
            else
                findViewById<ImageView>(R.id.product_detail_product_sizebox_background).visibility = View.GONE
            findViewById<ImageView>(R.id.product_detail_product_sizebox).setOnClickListener {
                view.setSelectedSize(myDataset[position], callback)
                notifyItemChanged(selectedSize)
                selectedSize = position
                findViewById<ImageView>(R.id.product_detail_product_sizebox_background).visibility =
                    View.VISIBLE
            }
        }
    }

    override fun getItemCount(): Int = myDataset.size
}
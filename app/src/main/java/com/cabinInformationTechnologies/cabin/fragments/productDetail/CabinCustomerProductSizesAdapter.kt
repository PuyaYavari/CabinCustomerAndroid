package com.cabinInformationTechnologies.cabin.fragments.productDetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cabinInformationTechnologies.cabin.R

class CabinCustomerProductSizesAdapter(val view: CabinCustomerProductDetailContracts.View,
                                       private var myDataset: List<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSize>):
    RecyclerView.Adapter<CabinCustomerProductSizesAdapter.ProductSizeViewHolder>(),
    CabinCustomerProductDetailContracts.SizeAdapter{
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
                findViewById<ImageView>(R.id.product_detail_product_sizebox_background).visibility = View.INVISIBLE
            findViewById<ImageView>(R.id.product_detail_product_sizebox).setOnClickListener {
                view.setSelectedSize(myDataset[position])
                notifyItemChanged(selectedSize)
                selectedSize = position
                findViewById<ImageView>(R.id.product_detail_product_sizebox_background).visibility =
                    View.VISIBLE
            }
        }
    }

    override fun getItemCount(): Int = myDataset.size

    override fun setDataset(sizes: List<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSize>) {
        myDataset = sizes
        selectedSize = -1
        notifyDataSetChanged()
    }

    fun indicateSelectedSize(size: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSize) {
        var position = 0
        while (position < myDataset.size && myDataset[position].id != size.id)
            position++
        val oldPosition = selectedSize
        selectedSize = position
        notifyItemChanged(oldPosition)
        notifyItemChanged(selectedSize)
    }
}
package com.cabinInformationTechnologies.cabin.fragments.productDetail

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Outline
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELImage

class CabinCustomerProductColorsAdapter(val view: CabinCustomerProductDetailContracts.View,
                                        private val myDataset: List<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor>):
    RecyclerView.Adapter<CabinCustomerProductColorsAdapter.ProductColorViewHolder>(){

    private var selectedColorPosition = 0

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
        val data = myDataset.get(position)
        holder.itemView.apply {
            findViewById<ImageView>(R.id.colorbox_color_sample).apply {
                imageTintList = ColorStateList.valueOf(Color.parseColor(myDataset[position].hexCode))
                outlineProvider = object : ViewOutlineProvider() {
                    override fun getOutline(view: View?, outline: Outline?) {
                        if (view != null &&  outline != null)
                            outline.setOval(10,15,view.width-10,view.height-15)
                    }
                }
            }
            findViewById<TextView>(R.id.colorbox_color_name).text = myDataset[position].name
            if (position == selectedColorPosition)
                findViewById<ImageView>(R.id.colorbox_color_sample_tick).visibility = View.VISIBLE
            else
                findViewById<ImageView>(R.id.colorbox_color_sample_tick).visibility = View.GONE
            setOnClickListener {
                view.showSizesOfColor(myDataset[position].id)
                view.setSelectedColor(myDataset[position])
                view.setSelectedSize(null)
                view.setupImages(data.images as ArrayList<MODELImage>)
                notifyItemChanged(selectedColorPosition)
                selectedColorPosition = position
                findViewById<ImageView>(R.id.colorbox_color_sample_tick).visibility = View.VISIBLE
            }
        }
    }

    override fun getItemCount(): Int = myDataset.size

    fun setTickOnColor(color: com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELColor) {
        var found = false
        var index = 0
        while (!found && index < myDataset.size){
            if (myDataset[index].id == color.id) {
                found = true
                selectedColorPosition = index
                notifyDataSetChanged()
            }
            index++
        }
    }
}
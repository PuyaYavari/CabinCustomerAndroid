package com.cabinInformationTechnologies.cabin.fragments.filterDetail.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cabinInformationTechnologies.cabin.R
import com.cabinInformationTechnologies.cabin.fragments.filterDetail.CabinCustomerFilterDetailContracts
import com.cabinInformationTechnologies.cabin.fragments.filterDetail.CabinCustomerFilterDetailFragment
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterCategory
import com.cabinInformationTechnologies.cabinCustomerBase.threeStateSelection

class CabinCustomerFilterCategoriesSubCategoriesAdapter (val fragment: CabinCustomerFilterDetailFragment,
                                                         private val myDataset: MutableList<MODELFilterCategory>,
                                                         private val callback: CabinCustomerFilterDetailContracts.CategoryCallback?)
    : RecyclerView.Adapter<CabinCustomerFilterCategoriesSubCategoriesAdapter.FilterSubCategoryViewHolder>() {
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    class FilterSubCategoryViewHolder(upperCategoryView: View) : RecyclerView.ViewHolder(upperCategoryView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilterSubCategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        when (viewType) {
            0 -> {
                val subCategoryView =
                    inflater.inflate(
                        R.layout.cabin_customer_categorybox_leaf_categorybox,
                        parent,
                        false
                    )
                return FilterSubCategoryViewHolder(
                    subCategoryView
                )
            }
            1 -> {
                val subCategoryView =
                    inflater.inflate(
                        R.layout.cabin_customer_categorybox_upper_categorybox,
                        parent,
                        false
                    )
                return FilterSubCategoryViewHolder(
                    subCategoryView
                )
            }
            else -> throw IllegalStateException("unsupported item type")
        }
    }

    override fun onBindViewHolder(holder: FilterSubCategoryViewHolder, position: Int) {
        when (getItemViewType(position)) {
            0 -> {
                holder.itemView.apply {
                    findViewById<CheckBox>(R.id.leaf_categorybox_checkbox).text =
                        myDataset[position].getName()
                    findViewById<CheckBox>(R.id.leaf_categorybox_checkbox).apply {
                        val isSelected = myDataset[position].isSelected
                        if (isSelected != null)
                            isChecked = isSelected
                        setOnCheckedChangeListener { _, isChecked ->
                            myDataset[position].isSelected = isChecked
                            callback?.setSubCat(myDataset)
                        }
                    }
                }
            }
            1 -> {
                val subCategories = myDataset[position].getSubCategories()
                if (!subCategories.isNullOrEmpty()) {
                    holder.itemView.apply {
                        findViewById<TextView>(R.id.upper_categorybox_label).text =
                            myDataset[position].getName()

                        // Setup RecyclerView
                        if (findViewById<RecyclerView>(R.id.upper_categorybox_recyclerview).adapter == null) {
                            val viewManager = LinearLayoutManager(fragment.context)
                            val viewAdapter =
                                CabinCustomerFilterCategoriesSubCategoriesAdapter(
                                    fragment,
                                    subCategories,
                                    object : CabinCustomerFilterDetailContracts.CategoryCallback {
                                        override fun setSubCat(dataset: MutableList<MODELFilterCategory>) {
                                            myDataset[position].setSubCategories(dataset)
                                            val state = findUpperCategoryState(position)
                                            if (state != null)
                                                setUpperCategoryCheckbox(holder, state)
                                        }
                                    }
                                )
                            findViewById<RecyclerView>(R.id.upper_categorybox_recyclerview).apply {
                                setHasFixedSize(true)
                                layoutManager = viewManager
                                adapter = viewAdapter
                                visibility = View.GONE
                            }
                            findViewById<TextView>(R.id.upper_categorybox_label).setOnClickListener {
                                toggleLeafCategories(holder)
                            }
                            findViewById<ImageView>(R.id.upper_categorybox_triangle).setOnClickListener {
                                toggleLeafCategories(holder)
                            }
                        }

                        //Setup Checkbox
                        val state = findUpperCategoryState(position)
                        if (state != null) {
                            setUpperCategoryCheckbox(holder, state)
                            findViewById<FrameLayout>(R.id.upper_categorybox_checkbox_layout).setOnClickListener {
                                if (state != threeStateSelection.SELECTED)
                                    (findViewById<RecyclerView>(R.id.upper_categorybox_recyclerview).adapter as
                                            CabinCustomerFilterCategoriesSubCategoriesAdapter)
                                        .toggleAllIsSelecteds(false)
                                else {
                                    (findViewById<RecyclerView>(R.id.upper_categorybox_recyclerview).adapter as
                                            CabinCustomerFilterCategoriesSubCategoriesAdapter)
                                        .toggleAllIsSelecteds(true)
                                }
                            }
                        }
                    }
                }
            }
            else -> throw IllegalStateException("unsupported item type")
        }
    }

    override fun getItemCount(): Int = myDataset.size

    override fun getItemViewType(position: Int): Int {
        return if (myDataset[position].getSubCategories().isNullOrEmpty())
            0
        else
            1
    }

    fun setUpperCategoryCheckbox(holder: FilterSubCategoryViewHolder, state: threeStateSelection) {
        holder.itemView.apply {
            if (state == threeStateSelection.UNSELECTED) {
                findViewById<ImageView>(R.id.upper_categorybox_checkbox_background)
                    .setImageResource(R.drawable.categorybox_checkbox_ring)
                findViewById<ImageView>(R.id.upper_categorybox_checkbox_minus)
                    .visibility = View.INVISIBLE
                findViewById<ImageView>(R.id.upper_categorybox_checkbox_tick)
                    .visibility = View.INVISIBLE
            } else if (state == threeStateSelection.HALFSELECTED) {
                findViewById<ImageView>(R.id.upper_categorybox_checkbox_background)
                    .setImageResource(R.drawable.categorybox_checkbox_ring)
                findViewById<ImageView>(R.id.upper_categorybox_checkbox_minus)
                    .visibility = View.VISIBLE
                findViewById<ImageView>(R.id.upper_categorybox_checkbox_tick)
                    .visibility = View.INVISIBLE
            } else if (state == threeStateSelection.SELECTED) {
                findViewById<ImageView>(R.id.upper_categorybox_checkbox_background)
                    .setImageResource(R.drawable.categorybox_checkbox_circle)
                findViewById<ImageView>(R.id.upper_categorybox_checkbox_minus)
                    .visibility = View.INVISIBLE
                findViewById<ImageView>(R.id.upper_categorybox_checkbox_tick)
                    .visibility = View.VISIBLE
            } else {
                findViewById<ImageView>(R.id.upper_categorybox_checkbox_background)
                    .setImageResource(R.drawable.categorybox_checkbox_ring)
                findViewById<ImageView>(R.id.upper_categorybox_checkbox_minus)
                    .visibility = View.INVISIBLE
                findViewById<ImageView>(R.id.upper_categorybox_checkbox_tick)
                    .visibility = View.INVISIBLE
                val context = fragment.context
                if (context != null)
                    Logger.warn(
                        context,
                        this::class.java.name,
                        "unknown state!!",
                        null
                    )
            }
        }
    }

    fun toggleAllIsSelecteds(isSelected: Boolean) {
        myDataset.forEach {
            it.isSelected = isSelected
        }
        notifyDataSetChanged()
    }

    private fun findUpperCategoryState(position: Int): threeStateSelection? {
        if (getItemViewType(position) == 1) {
            var selectedCategoriesCount = 0
            var state = threeStateSelection.UNSELECTED
            myDataset[position].getSubCategories()?.forEach {
                if (it.isSelected == true) {
                    selectedCategoriesCount++
                    state = threeStateSelection.HALFSELECTED
                }
            }
            if (selectedCategoriesCount == myDataset[position].getSubCategories()?.size)
                state = threeStateSelection.SELECTED
            return state
        }
        return null
    }

    private fun toggleLeafCategories(holder: FilterSubCategoryViewHolder) {
        val recyclerView = holder.itemView.findViewById<RecyclerView>(R.id.upper_categorybox_recyclerview)
        if (recyclerView.visibility == View.GONE) {
            recyclerView.visibility = View.VISIBLE
            holder.itemView.findViewById<ImageView>(R.id.upper_categorybox_triangle).rotation = 0f
        } else {
            recyclerView.visibility = View.GONE
            holder.itemView.findViewById<ImageView>(R.id.upper_categorybox_triangle).rotation = 180f
        }
    }
}
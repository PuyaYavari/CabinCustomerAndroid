package com.cabinInformationTechnologies.cabinCustomerBase.models.local

import android.content.Context
import com.cabinInformationTechnologies.cabinCustomerBase.Logger
import com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONFilter

class MODELFilter: LocalDataModel {
    var filterCategories: MutableList<MODELFilterCategory>? = null
    var sexes: MutableList<MODELFilterSex>? = null
    var sellers: MutableList<MODELFilterSeller>? = null
    var colors: MutableList<MODELFilterColor>? = null
    var filterSizes: MutableList<MODELFilterSizeGroup>? = null
    var filterPrices: MutableList<MODELFilterPrice>? = null
    var amount: Int = 0

    override fun <T> mapFrom(context: Context, modelData: T): Boolean {
        return try {
            val jsonData = modelData as JSONFilter
            val categoriesData = jsonData.filterCategories
            if (!categoriesData.isNullOrEmpty()) {
                val newFilterCategories: MutableList<MODELFilterCategory> = mutableListOf()
                categoriesData.forEach {
                    val category =
                        MODELFilterCategory() //FIXME: NOT MEMORY EFFICIENT
                    if (category.mapFrom(context, it))
                        newFilterCategories.add(category)
                }
                this.filterCategories = newFilterCategories
            }
            val sexesData = jsonData.sexes
            if (!sexesData.isNullOrEmpty()) {
                val newSexes: MutableList<MODELFilterSex> = mutableListOf()
                sexesData.forEach {
                    val sex = MODELFilterSex()
                    if (sex.mapFrom(context, it))
                        newSexes.add(sex)
                }
                this.sexes = newSexes
            }
            val sellersData = jsonData.seller
            if (!sellersData.isNullOrEmpty()) {
                val newSellers: MutableList<MODELFilterSeller> = mutableListOf()
                sellersData.forEach {
                    val seller = MODELFilterSeller()
                    if (seller.mapFrom(context, it))
                        newSellers.add(seller)
                }
                this.sellers = newSellers
            }
            val colorsData = jsonData.colors
            if (!colorsData.isNullOrEmpty()) {
                val newColors: MutableList<MODELFilterColor> = mutableListOf()
                colorsData.forEach {
                    val color = MODELFilterColor()
                    if (color.mapFrom(context, it))
                        newColors.add(color)
                }
                this.colors = newColors
            }
            val sizesData = jsonData.filterSizes
            if (!sizesData.isNullOrEmpty()) {
                val newFilterSizes: MutableList<MODELFilterSizeGroup> = mutableListOf()
                sizesData.forEach {
                    val sizeNameGroup =
                        MODELFilterSizeGroup()
                    if (sizeNameGroup.mapFrom(context, it))
                        newFilterSizes.add(sizeNameGroup)
                }
                this.filterSizes = newFilterSizes
            }
            val pricesData = jsonData.filterPrices
            if (!pricesData.isNullOrEmpty()) {
                val newFilterPrices: MutableList<MODELFilterPrice> = mutableListOf()
                pricesData.forEach {
                    val priceInterval = MODELFilterPrice()
                    if (priceInterval.mapFrom(context, it))
                        newFilterPrices.add(priceInterval)
                }
                this.filterPrices = newFilterPrices
            }
            val amountData = jsonData.amount
            if (amountData != null)
                this.amount = amountData
            true
        } catch (exception: Exception) {
            Logger.error(
                context,
                this::class.java.name,
                "Failed to map Filter!",
                exception)
            false
        }
    }
}
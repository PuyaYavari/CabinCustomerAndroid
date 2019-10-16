package ist.cabin.cabinCustomerBase.models.local

import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.JSONFilter

class MODELFilter: LocalDataModel {
    var categories: MutableList<MODELCategory>? = null
    var sexes: MutableList<MODELSex>? = null
    var sellers: MutableList<MODELSellerName>? = null
    var colors: MutableList<MODELRawColor>? = null
    var sizes: MutableList<MODELSizeNameGroup>? = null
    var prices: MutableList<MODELPriceInterval>? = null

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as JSONFilter
            val categoriesData = jsonData.categories
            if (!categoriesData.isNullOrEmpty()) {
                val newCategories: MutableList<MODELCategory> = mutableListOf()
                categoriesData.forEach {
                    val category = MODELCategory() //FIXME: NOT MEMORY EFFICIENT
                    if (category.mapFrom(it))
                        newCategories.add(category)
                }
                this.categories = newCategories
            }
            val sexesData = jsonData.sexes
            if (!sexesData.isNullOrEmpty()) {
                val newSexes: MutableList<MODELSex> = mutableListOf()
                sexesData.forEach {
                    val sex = MODELSex()
                    if (sex.mapFrom(it))
                        newSexes.add(sex)
                }
                this.sexes = newSexes
            }
            val sellersData = jsonData.seller
            if (!sellersData.isNullOrEmpty()) {
                val newSellers: MutableList<MODELSellerName> = mutableListOf()
                sellersData.forEach {
                    val seller = MODELSellerName()
                    if (seller.mapFrom(it))
                        newSellers.add(seller)
                }
                this.sellers = newSellers
            }
            val colorsData = jsonData.colors
            if (!colorsData.isNullOrEmpty()) {
                val newColors: MutableList<MODELRawColor> = mutableListOf()
                colorsData.forEach {
                    val color = MODELRawColor()
                    if (color.mapFrom(it))
                        newColors.add(color)
                }
                this.colors = newColors
            }
            val sizesData = jsonData.sizes
            if (!sizesData.isNullOrEmpty()) {
                val newSizes: MutableList<MODELSizeNameGroup> = mutableListOf()
                sizesData.forEach {
                    val sizeNameGroup = MODELSizeNameGroup()
                    if (sizeNameGroup.mapFrom(it))
                        newSizes.add(sizeNameGroup)
                }
                this.sizes = newSizes
            }
            val pricesData = jsonData.prices
            if (!pricesData.isNullOrEmpty()) {
                val newPrices: MutableList<MODELPriceInterval> = mutableListOf()
                pricesData.forEach {
                    val priceInterval = MODELPriceInterval()
                    if (priceInterval.mapFrom(it))
                        newPrices.add(priceInterval)
                }
                this.prices = newPrices
            }
            true
        } catch (exception: Exception) {
            Logger.error(this::class.java.name, "Failed to map Filter!", exception)
            false
        }
    }
}
package ist.cabin.cabinCustomerBase.models.local

import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.JSONFilter

class MODELFilter: LocalDataModel {
    var filterCategories: MutableList<MODELFilterCategory>? = null
    var sexes: MutableList<MODELFilterSex>? = null
    var sellers: MutableList<MODELFilterSeller>? = null
    var colors: MutableList<MODELFilterColor>? = null
    var filterSizes: MutableList<MODELFilterSizeGroup>? = null
    var filterPrices: MutableList<MODELFilterPrice>? = null

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as JSONFilter
            val categoriesData = jsonData.filterCategories
            if (!categoriesData.isNullOrEmpty()) {
                val newFilterCategories: MutableList<MODELFilterCategory> = mutableListOf()
                categoriesData.forEach {
                    val category = MODELFilterCategory() //FIXME: NOT MEMORY EFFICIENT
                    if (category.mapFrom(it))
                        newFilterCategories.add(category)
                }
                this.filterCategories = newFilterCategories
            }
            val sexesData = jsonData.sexes
            if (!sexesData.isNullOrEmpty()) {
                val newSexes: MutableList<MODELFilterSex> = mutableListOf()
                sexesData.forEach {
                    val sex = MODELFilterSex()
                    if (sex.mapFrom(it))
                        newSexes.add(sex)
                }
                this.sexes = newSexes
            }
            val sellersData = jsonData.seller
            if (!sellersData.isNullOrEmpty()) {
                val newSellers: MutableList<MODELFilterSeller> = mutableListOf()
                sellersData.forEach {
                    val seller = MODELFilterSeller()
                    if (seller.mapFrom(it))
                        newSellers.add(seller)
                }
                this.sellers = newSellers
            }
            val colorsData = jsonData.colors
            if (!colorsData.isNullOrEmpty()) {
                val newColors: MutableList<MODELFilterColor> = mutableListOf()
                colorsData.forEach {
                    val color = MODELFilterColor()
                    if (color.mapFrom(it))
                        newColors.add(color)
                }
                this.colors = newColors
            }
            val sizesData = jsonData.filterSizes
            if (!sizesData.isNullOrEmpty()) {
                val newFilterSizes: MutableList<MODELFilterSizeGroup> = mutableListOf()
                sizesData.forEach {
                    val sizeNameGroup = MODELFilterSizeGroup()
                    if (sizeNameGroup.mapFrom(it))
                        newFilterSizes.add(sizeNameGroup)
                }
                this.filterSizes = newFilterSizes
            }
            val pricesData = jsonData.filterPrices
            if (!pricesData.isNullOrEmpty()) {
                val newFilterPrices: MutableList<MODELFilterPrice> = mutableListOf()
                pricesData.forEach {
                    val priceInterval = MODELFilterPrice()
                    if (priceInterval.mapFrom(it))
                        newFilterPrices.add(priceInterval)
                }
                this.filterPrices = newFilterPrices
            }
            true
        } catch (exception: Exception) {
            Logger.error(this::class.java.name, "Failed to map Filter!", exception)
            false
        }
    }
}
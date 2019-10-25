package com.cabinInformationTechnologies.cabinCustomerBase.models.local

class MODELFilter: com.cabinInformationTechnologies.cabinCustomerBase.models.local.LocalDataModel {
    var filterCategories: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterCategory>? = null
    var sexes: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterSex>? = null
    var sellers: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterSeller>? = null
    var colors: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterColor>? = null
    var filterSizes: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterSizeGroup>? = null
    var filterPrices: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterPrice>? = null

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONFilter
            val categoriesData = jsonData.filterCategories
            if (!categoriesData.isNullOrEmpty()) {
                val newFilterCategories: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterCategory> = mutableListOf()
                categoriesData.forEach {
                    val category =
                        com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterCategory() //FIXME: NOT MEMORY EFFICIENT
                    if (category.mapFrom(it))
                        newFilterCategories.add(category)
                }
                this.filterCategories = newFilterCategories
            }
            val sexesData = jsonData.sexes
            if (!sexesData.isNullOrEmpty()) {
                val newSexes: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterSex> = mutableListOf()
                sexesData.forEach {
                    val sex = com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterSex()
                    if (sex.mapFrom(it))
                        newSexes.add(sex)
                }
                this.sexes = newSexes
            }
            val sellersData = jsonData.seller
            if (!sellersData.isNullOrEmpty()) {
                val newSellers: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterSeller> = mutableListOf()
                sellersData.forEach {
                    val seller = com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterSeller()
                    if (seller.mapFrom(it))
                        newSellers.add(seller)
                }
                this.sellers = newSellers
            }
            val colorsData = jsonData.colors
            if (!colorsData.isNullOrEmpty()) {
                val newColors: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterColor> = mutableListOf()
                colorsData.forEach {
                    val color = com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterColor()
                    if (color.mapFrom(it))
                        newColors.add(color)
                }
                this.colors = newColors
            }
            val sizesData = jsonData.filterSizes
            if (!sizesData.isNullOrEmpty()) {
                val newFilterSizes: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterSizeGroup> = mutableListOf()
                sizesData.forEach {
                    val sizeNameGroup =
                        com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterSizeGroup()
                    if (sizeNameGroup.mapFrom(it))
                        newFilterSizes.add(sizeNameGroup)
                }
                this.filterSizes = newFilterSizes
            }
            val pricesData = jsonData.filterPrices
            if (!pricesData.isNullOrEmpty()) {
                val newFilterPrices: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterPrice> = mutableListOf()
                pricesData.forEach {
                    val priceInterval = com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELFilterPrice()
                    if (priceInterval.mapFrom(it))
                        newFilterPrices.add(priceInterval)
                }
                this.filterPrices = newFilterPrices
            }
            true
        } catch (exception: Exception) {
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(this::class.java.name, "Failed to map Filter!", exception)
            false
        }
    }
}
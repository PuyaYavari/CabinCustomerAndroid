package com.cabinInformationTechnologies.cabinCustomerBase.models.local

class MODELCart : com.cabinInformationTechnologies.cabinCustomerBase.models.local.LocalDataModel {
    private var seller: MutableList<com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSeller> = mutableListOf()
    private var shippingPrice: Double? = null
    private var subtotal: Double? = null
    private var total: Double = 0.0

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as com.cabinInformationTechnologies.cabinCustomerBase.models.backend.JSONCart
            jsonData.seller.forEach {
                val seller = com.cabinInformationTechnologies.cabinCustomerBase.models.local.MODELSeller()
                if (seller.mapFrom(it))
                    this.seller.add(seller)
            }
            this.shippingPrice = jsonData.shippingPrice
            this.subtotal = jsonData.subtotal
            val totalData = jsonData.total
            if (totalData != null)
                this.total = totalData
            true
        } catch (exception: Exception) {
            com.cabinInformationTechnologies.cabinCustomerBase.Logger.error(this::class.java.name, "Problem while mapping to model.", exception)
            false
        }
    }

    fun getSellers() = seller
    fun getShippingPrice() = shippingPrice
    fun getSubtotal() = subtotal
    fun getTotal() = total
}
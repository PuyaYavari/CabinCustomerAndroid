package ist.cabin.cabinCustomerBase.models.local

import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.JSONCart

class MODELCart : LocalDataModel {
    private var seller: MutableList<MODELSeller> = mutableListOf()
    private var shippingPrice: Int? = null
    private var subtotal: Int? = null
    private var total: Int = 0

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as JSONCart
            jsonData.seller.forEach {
                val seller = MODELSeller()
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
            Logger.error(this::class.java.name, "Problem while mapping to model.", exception)
            false
        }
    }

    fun getSellers() = seller
    fun getShippingPrice() = shippingPrice
    fun getSubtotal() = subtotal
    fun getTotal() = total
}
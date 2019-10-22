package ist.cabin.cabinCustomerBase.models.local

import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.JSONSeller

class MODELSeller : LocalDataModel {
    private var id: Int = -1
    private lateinit var name: String
    private val products: MutableList<MODELProduct?> = mutableListOf()
    private var shippingPrice: Double? = null
    private var subtotal: Double? = null
    private var total: Double = -1.0

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as JSONSeller
            this.id = jsonData.id
            this.name = jsonData.name
            jsonData.products.forEach {
                val product = MODELProduct()
                if(product.mapFrom(it))
                    this.products.add(product)
            }
            this.shippingPrice = jsonData.shippingPrice
            this.subtotal = jsonData.subtotal
            this.total = jsonData.total
            true
        } catch (exception: Exception) {
            Logger.error(this::class.java.name, "Problem while mapping to model.", exception)
            false
        }
    }

    fun getId() = id
    fun getName() = name
    fun getProducts() = products
    fun getShippingPrice() = shippingPrice
    fun getSubtotal() = subtotal
    fun getTotal() = total
}
package ist.cabin.cabinCustomerBase.models.local

import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.APIProduct

class MODELProducts: LocalDataModel {
    var products: MutableList<MODELProduct> = mutableListOf()

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonData = modelData as APIProduct
            jsonData.products.forEach {
                val product = MODELProduct()
                if (product.mapFrom(it))
                    products.add(product)
            }
            true
        } catch (exception: Exception) {
            Logger.warn(this::class.java.name, "A problem occurred while mapping Products.", exception)
            false
        }
    }
}
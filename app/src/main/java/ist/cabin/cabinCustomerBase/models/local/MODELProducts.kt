package ist.cabin.cabinCustomerBase.models.local

import android.util.Log
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
            Log.e("Products Mapper", exception.message.toString())
            false
        }
    }
}
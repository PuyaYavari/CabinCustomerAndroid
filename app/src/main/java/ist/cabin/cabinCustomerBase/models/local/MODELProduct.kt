package ist.cabin.cabinCustomerBase.models.local

import android.util.Log
import ist.cabin.cabinCustomerBase.models.backend.JSONProduct

class MODELProduct : LocalDataModel{
    lateinit var sellerName: String
    lateinit var productName: String
    lateinit var productID: String
    var price: Double? = null
    var cargoDurationID: Int? = null
    lateinit var cargoDuration: String
    var cargoTypeID: Int? = null
    lateinit var cargoType: String
    var colors: MutableList<MODELColor> = mutableListOf()

    @Throws(java.lang.Exception::class)
    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonModel = modelData as JSONProduct
            val seller = MODELSeller()
            if (seller.mapFrom(jsonModel.seller[0]))
                sellerName = seller.name
            productName = jsonModel.title
            productID = jsonModel.code
            price = jsonModel.price

            val cargoDurationData = jsonModel.shippingDuration?.get(0) ?: throw java.lang.Exception("Cargo Duration Not Mapped.")
            cargoDurationID = cargoDurationData.id
            cargoDuration = cargoDurationData.name

            val cargoTypeData = jsonModel.shippingType[0] ?: throw java.lang.Exception("Cargo Type Not Mapped.")
            cargoTypeID = cargoTypeData.id
            cargoType = cargoDurationData.name

            jsonModel.colors.forEach {
                val color = MODELColor()
                if (color.mapFrom(it))
                    colors.add(color)
            }
            true
        } catch (exception: Exception){
            Log.e("Product Mapper", exception.message.toString())
            false
        }

    }
}
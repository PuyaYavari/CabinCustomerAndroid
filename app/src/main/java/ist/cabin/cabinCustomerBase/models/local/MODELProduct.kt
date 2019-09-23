package ist.cabin.cabinCustomerBase.models.local

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.JSONProduct
import kotlinx.android.parcel.Parcelize

@Parcelize
class MODELProduct : LocalDataModel, Parcelable{

    var id: Int = -1
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
            id = jsonModel.id
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
            cargoType = cargoTypeData.name

            jsonModel.colors.forEach {
                val color = MODELColor()
                if (color.mapFrom(it))
                    colors.add(color)
            }
            true
        } catch (exception: Exception){
            Logger.warn(this::class.java.name, "A problem occurred while mapping Product.", exception)
            false
        }

    }
}
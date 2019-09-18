package ist.cabin.cabinCustomerBase.models.local

import android.util.Log
import ist.cabin.cabinCustomerBase.models.backend.JSONSeller

class MODELSeller: LocalDataModel {
    lateinit var name: String
    var id: Int? = null

    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonModel = modelData as JSONSeller
            name = jsonModel.name
            id = jsonModel.id
            true
        } catch (exception: Exception){
            Log.e("Seller Mapper", exception.message.toString())
            false
        }
    }
}
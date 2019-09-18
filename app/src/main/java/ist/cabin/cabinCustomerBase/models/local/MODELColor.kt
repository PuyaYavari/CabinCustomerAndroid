package ist.cabin.cabinCustomerBase.models.local

import android.graphics.Color
import android.os.Build
import android.util.Log
import ist.cabin.cabinCustomerBase.models.backend.JSONColor

class MODELColor: LocalDataModel {
    var id: Int = -1
    lateinit var name: String
    lateinit var color: Color
    var favourite: Boolean = false
    var images: MutableList<MODELImage> = mutableListOf()
    var sizes: MutableList<MODELSize> = mutableListOf()

    @Throws(Exception::class)
    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonModel = modelData as JSONColor
            id = jsonModel.id
            name = jsonModel.name ?: throw Exception("Color name is null!")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                color = Color.valueOf(Color.parseColor(jsonModel.hexCode))
            } else {
//                color = Color.argb() //TODO: WITH RGB
            }
            if (jsonModel.isFavorite != null)
                favourite = jsonModel.isFavorite
            jsonModel.images?.forEach {
                val image = MODELImage()
                if (image.mapFrom(it))
                    images.add(image)
            }
            jsonModel.sizes.forEach {
                val size = MODELSize()
                if (size.mapFrom(it))
                    sizes.add(size)
            } //FIXME: RETURN FALSE FOR IMPORTANT FIELDS MISSING
            true
        } catch (exception: Exception){
            Log.e("Color Mapper", exception.message.toString())
            false
        }
    }
}
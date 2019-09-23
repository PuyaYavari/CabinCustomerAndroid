package ist.cabin.cabinCustomerBase.models.local

import ist.cabin.cabinCustomerBase.Logger
import ist.cabin.cabinCustomerBase.models.backend.JSONColor

class MODELColor: LocalDataModel {
    var id: Int = -1
    var name: String = ""
    lateinit var hexCode: String
    var favourite: Boolean = false
    var images: MutableList<MODELImage> = mutableListOf()
    var sizes: MutableList<MODELSize> = mutableListOf()

    @Throws(Exception::class)
    override fun <T> mapFrom(modelData: T): Boolean {
        return try {
            val jsonModel = modelData as JSONColor
            id = jsonModel.id
            if (jsonModel.name == null)
                Logger.info(this::class.java.name, "Color name is null.", null)
            else
                name = jsonModel.name


            hexCode = jsonModel.hexCode

            if (jsonModel.isFavorite != null)
                favourite = jsonModel.isFavorite
            jsonModel.images.forEach {
                val image = MODELImage()
                if (image.mapFrom(it)) {
                    images.add(image)
                } else {
                    Logger.info(this::class.java.name, "Image not mapped.", null)
                }
            }
            jsonModel.sizes.forEach {
                val size = MODELSize()
                if (size.mapFrom(it))
                    sizes.add(size)
            } //FIXME: RETURN FALSE FOR IMPORTANT FIELDS MISSING
            true
        } catch (exception: Exception){
            Logger.warn(this::class.java.name,"A problem occurred while mapping Color.",exception)
            false
        }
    }

    fun populateWith(id: Int, name: String, hexCode: String, favourite: Boolean?, images: MutableList<MODELImage>?, sizes: MutableList<MODELSize>?) {
        this.id = id
        this.name = name
        this.hexCode = hexCode
        if (favourite != null)
            this.favourite = favourite
        if (images != null)
            this.images = images
        if (sizes != null)
            this.sizes = sizes
    }
}